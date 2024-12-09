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
import io.github.unisim.world.World;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class ScoreManagerTests extends AbstractHeadlessGdxTest {
  
  @Test

  public void testUpdateScoreWithBuildingCount() {
    
    ScoreManager TestScoreManager = new ScoreManager();

    ArrayList<Building> buildings = new ArrayList<Building>();
    
    ArrayList<BuildingTuple> buildingCounts = new ArrayList<BuildingTuple>();

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


    TestScoreManager.updateScore(TestBuilding, buildings, buildingCounts);

    assertEquals(TestScoreManager.getScore(), 10);    

    buildingCounts.add(new BuildingTuple(1, BuildingType.LEARNING));

    TestScoreManager.updateScore(TestBuilding, buildings, buildingCounts);

    assertEquals(TestScoreManager.getScore(), 18);   

  }

  public void testDecrementScoreWithTime() {
    
  }

}
