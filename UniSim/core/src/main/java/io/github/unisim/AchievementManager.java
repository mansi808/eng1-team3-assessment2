package io.github.unisim;

import io.github.unisim.building.BuildingManager;
import io.github.unisim.building.BuildingType;
import io.github.unisim.ui.AchievementMenu;

import java.util.ArrayList;
import java.util.Map;

/**
 * Manages methods for calculating and initialising achievements earned by the player
 */

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


    public boolean isScore500() {
        return scoreManager.getScore() > 500;
    }


    public boolean areBuildingsFifty() {
        return buildingManager.getBuildingCount() > 50;
    }

    public boolean areEqualBuildingType() {

        Map<BuildingType, Integer> buildingCounts = buildingManager.getBuildingCounts();

        int prev = -1;

        for (int count : buildingCounts.values()) {
            if (count == -1) {
                count = prev;
            } else if (count != prev && prev != 0) {
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

    /**
     * Updates <code>achievements</code> and initialises each achievement if the methods return true.
     */
    public void calculateAchievements() {
        if (areEqualBuildingType()) achievements.add(new Achievement("Equaliser","You're the architect of balance, making sure every type of building get" +
            "s its fair share of the spotlight!", scoreManager, false));
        if (haveZeroScore()) achievements.add(new Achievement("Minimalist", "Zero? Well, looks like there is still a lot of space for improvement ",scoreManager, false));
        if (areBuildingsFifty()) achievements.add(new Achievement("Maximiser","50 Sites! We might need another campus to keep up with you" , scoreManager, true));
        if (isScore500()) achievements.add(new Achievement("Champion","500? Gosh! we might need to bring up the heat" , scoreManager, true));
        achievementMenu.setAchievements(achievements);
    }

    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }


}
