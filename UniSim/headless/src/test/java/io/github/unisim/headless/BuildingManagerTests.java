package io.github.unisim.headless;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import io.github.unisim.Point;
import io.github.unisim.building.Building;
import io.github.unisim.building.BuildingManager;
import io.github.unisim.building.BuildingType;

public class BuildingManagerTests extends AbstractHeadlessGdxTest {

  private Matrix4 isoTransform;
  private Matrix4 invIsoTransform;

    /**
   * Calculates the matrices needed to transform a point into and outof isometric
   * world space.
   */
  private void initIsometricTransform() {
    // create the isometric transform
    isoTransform = new Matrix4();
    isoTransform.idt();

    // isoTransform.translate(0, 32, 0);
    isoTransform.scale((float) (Math.sqrt(2.0) / 2.0), (float) (Math.sqrt(2.0) / 4.0), 1.0f);
    isoTransform.rotate(0.0f, 0.0f, 1.0f, -45);

    // ... and the inverse matrix
    invIsoTransform = new Matrix4(isoTransform);
    invIsoTransform.inv();
  }
  
  @Test

  public void testGetBuildings() {
    initIsometricTransform();
    BuildingManager TestBuildingManager = new BuildingManager();

    ArrayList<Building> buildings = TestBuildingManager.getBuildings();

    assertEquals(0, buildings.size());

  }
  @Test

  public void testPlaceBuilding() {
    initIsometricTransform();
    BuildingManager TestBuildingManager = new BuildingManager();

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

    TestBuildingManager.placeBuilding(TestBuilding);

    ArrayList<Building> buildings = TestBuildingManager.getBuildings();

    assertEquals(1, buildings.size());
  }

  @Test
  public void testPlaceLearningBuilding() {
    initIsometricTransform();
    BuildingManager TestBuildingManager = new BuildingManager();

    Building learningBuilding = new Building(
            new Texture(Gdx.files.internal("buildings/library.png")),
            0.0075f,
            new Vector2(3.0f, -2.0f),
            new Point(),
            new Point(15, 10),
            false,
            BuildingType.LEARNING,
            "Library"
    );

    TestBuildingManager.placeBuilding(learningBuilding);

    Building building = TestBuildingManager.getBuildings().getFirst();

    assertEquals(BuildingType.LEARNING, building.type);
  }



  @Test
  public void testPlaceRecreationBuilding() {
    initIsometricTransform();
    BuildingManager TestBuildingManager = new BuildingManager();

    Building recreationBuilding = new Building(
            new Texture(Gdx.files.internal("buildings/basketballCourt.png")),
            0.0025f,
            new Vector2(1f, -2.4f),
            new Point(),
            new Point(6, 9),
            false,
            BuildingType.RECREATION,
            "Basketball Court"
    );

    TestBuildingManager.placeBuilding(recreationBuilding);

    Building building = TestBuildingManager.getBuildings().getFirst();

    assertEquals(BuildingType.RECREATION, building.type);
  }


  @Test
  public void testPlaceSleepingBuilding() {
    initIsometricTransform();
    BuildingManager TestBuildingManager = new BuildingManager();

    Building sleepingBuilding = new Building(
            new Texture(Gdx.files.internal("buildings/studentHousing.png")),
            0.108f,
            new Vector2(1.4f, -2.8f),
            new Point(),
            new Point(11, 11),
            false,
            BuildingType.SLEEPING,
            "Student Accomodation"
    );

    TestBuildingManager.placeBuilding(sleepingBuilding);

    Building building = TestBuildingManager.getBuildings().getFirst();

    assertEquals(BuildingType.SLEEPING, building.type);
  }

  @Test
  public void testPlaceEatingBuilding() {
    initIsometricTransform();
    BuildingManager TestBuildingManager = new BuildingManager();

    Building eatingBuilding = new Building(
            new Texture(Gdx.files.internal("buildings/restaurant.png")),
            0.0075f,
            new Vector2(5.0f, -5.0f),
            new Point(),
            new Point(14, 10),
            false,
            BuildingType.EATING,
            "Restaurant"
    );

    TestBuildingManager.placeBuilding(eatingBuilding);

    Building building = TestBuildingManager.getBuildings().getFirst();

    assertEquals(BuildingType.EATING, building.type);
  }

  @Test

  public void testGetBuildingCount() {
    initIsometricTransform();
    BuildingManager TestBuildingManager = new BuildingManager();

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

    TestBuildingManager.placeBuilding(TestBuilding);
    TestBuildingManager.placeBuilding(TestBuilding);
    TestBuildingManager.placeBuilding(TestBuilding);

    Map<BuildingType, Integer> hashMap = TestBuildingManager.getBuildingCounts();

    assertEquals(3, hashMap.get(BuildingType.LEARNING));
  }


}
