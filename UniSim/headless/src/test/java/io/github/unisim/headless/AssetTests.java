package io.github.unisim.headless;

import com.badlogic.gdx.Gdx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AssetTests extends AbstractHeadlessGdxTest {
  @Test
  public void testBuildingAssetExists() {
    // Restaurant
    assertTrue(Gdx.files.internal("buildings/restaurant.png").exists(), "The asset for restaurant should be available. If available, should be named correctly.");

    // Library
    assertTrue(Gdx.files.internal("buildings/library.png").exists(), "The asset for library should be available. If available, should be named correctly.");

    // Basketball Court
    assertTrue(Gdx.files.internal("buildings/basketballCourt.png").exists(), "The asset for basketball court should be available. If available, should be named correctly.");

    // Student Housing
    assertTrue(Gdx.files.internal("buildings/studentHousing.png").exists(), "The asset for student housing should be available. If available, should be named correctly.");

    // Bakery
    assertTrue(Gdx.files.internal("buildings/bakery.png").exists(), "The asset for bakery should be available. If available, should be named correctly.");
  }

}
