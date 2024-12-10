package io.github.unisim;

import io.github.unisim.building.BuildingManager;
import io.github.unisim.building.BuildingType;

public class Achievement {

    private String description = "";
    private String title;

    public Achievement(String title, String description) {
        setDescription(description);
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String message) {
        this.description = message;
    }


}
