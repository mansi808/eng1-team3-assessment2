package io.github.unisim;

import io.github.unisim.building.BuildingManager;
import io.github.unisim.building.BuildingType;
import io.github.unisim.ui.AchievementMenu;

import java.util.ArrayList;

public class AchievementManager {

    private ScoreManager scoreManager;
    private BuildingManager buildingManager;
    private AchievementMenu achievementMenu;
    private ArrayList<Achievement> achievements = new ArrayList<>(3);

    public AchievementManager(ScoreManager scoreManager, BuildingManager buildingManager, AchievementMenu achievementMenu) {
        this.scoreManager = scoreManager;
        this.buildingManager = buildingManager;
        this.achievementMenu = achievementMenu;
    }

    public boolean isScoreAchievement() {
        if (scoreManager.getScore() > 100 && scoreManager.getScore() <= 110) {
            return true;
        }
//        if (buildingManager.getBuildingType) {
//            return true;
//        }
         return false;
    }

//    public boolean isLessThanTen() {
//        return buildingManager.getBuildingsCount() > 0 && buildingManager.getBuildingsCount() < 10);
//    }
//
//    public boolean isMoreThanThirty() {
//        return buildingManager.getBuildingsCount() > 30;
//    }

    public boolean areEqualBuildingType() {
        int i = -1;
        for (BuildingType type: BuildingType.values()) {
            if (i==-1) {
                i = buildingManager.getBuildingCount(type);
            } if (i != buildingManager.getBuildingCount(type)) {
                return false;
            }
        }
        return true;
    }

    public boolean haveZeroScore() {
        return scoreManager.getScore() == 0;
    }


    public void showAchievement() {
        if (!GameState.paused) {
            GameState.paused = true;
            achievementMenu.setAchievements(achievements);
            achievementMenu.update();
        }
    }

    public void calculateAchievements() {
        if (areEqualBuildingType()) achievements.add(new Achievement("Equaliser","You're the architect of balance, making sure every type of building get" +
            "s its fair share of the spotlight!", scoreManager, false));
        if (haveZeroScore()) achievements.add(new Achievement("Minimalist", "Zero? Well, looks like there is still a lot of space for improvement ",scoreManager, false));
        achievementMenu.setAchievements(achievements);
    }

    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }
}
