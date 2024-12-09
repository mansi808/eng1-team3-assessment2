package io.github.unisim.headless;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import io.github.unisim.Point;
import io.github.unisim.ScoreManager;
import io.github.unisim.building.Building;
import io.github.unisim.building.BuildingType;
import io.github.unisim.building.data.types.BuildingTuple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class ScoreManagerTests extends AbstractHeadlessGdxTest {
  
  @Test

  public void testUpdateScoreWithBuildingCount() {
    
    // Initialize a Score Manager
    ScoreManager TestScoreManager = new ScoreManager();

    // Create an array of buildings
    ArrayList<Building> buildings = new ArrayList<Building>();
    
    // Create an array of building counts
    ArrayList<BuildingTuple> buildingCounts = new ArrayList<BuildingTuple>();

    // Create a Building to test with
    Building TestBuilding = new Building(
      new Texture(Gdx.files.internal("buildings/library.png")),
      0.0075f,
      new Vector2(1.8f, -4.6f),
      new Point(),
      new Point(20, 12),
      false,
      BuildingType.LEARNING,
      "Library"
  );

    // Update score based on the new building when none
    TestScoreManager.updateScore(TestBuilding, buildings, buildingCounts);

    assertEquals(TestScoreManager.getScore(), 10);    


    // Update score based on the new building when there is already this type of building

    buildingCounts.add(new BuildingTuple(1, BuildingType.LEARNING));

    TestScoreManager.updateScore(TestBuilding, buildings, buildingCounts);

    assertEquals(TestScoreManager.getScore(), 18);   

  }

  @Test

  public void testDecrementScoreWithTime() {

    // Initialize Score Manager
    ScoreManager TestScoreManager = new ScoreManager();

    TestScoreManager.setScore(100);

    // No change in score in 1 seconds
    TestScoreManager.decrementScoreWithTime(1000);

    assertEquals(TestScoreManager.getScore(), 100);

    // Score should decrement only every 2 seconds
    TestScoreManager.decrementScoreWithTime(1000);

    assertEquals(TestScoreManager.getScore(), 99);
  }

}
