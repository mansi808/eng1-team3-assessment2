package io.github.unisim;

import io.github.unisim.building.BuildingManager;
import io.github.unisim.building.BuildingType;

public class Achievement {

    private String description = "";
    private String title;
    public ScoreManager  scoreManager;

    public Achievement(String title, String description, ScoreManager scoreManager, boolean positive) {
        setDescription(description);
        this.title = title;
        this.scoreManager = scoreManager;
        if (positive) setPositiveImpact();
        else setNegativeImpact();
    }

    public void setPositiveImpact() {
        scoreManager.updateScore(5);
    }

    public void setNegativeImpact() {
        scoreManager.setScore(-5);
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
