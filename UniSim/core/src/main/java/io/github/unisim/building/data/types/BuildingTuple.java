package io.github.unisim.building.data.types;

import io.github.unisim.building.BuildingType;

public class BuildingTuple {
  public int count;
  public BuildingType type;

  public BuildingTuple(int count, BuildingType type) {
    this.count = count;
    this.type = type;
  }
}
