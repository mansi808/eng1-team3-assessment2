package io.github.unisim;

import io.github.unisim.building.BuildingManager;
import io.github.unisim.building.BuildingType;
import io.github.unisim.ui.AchievementMenu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Manages methods for calculating and initialising achievements earned by the player
 * Has a list of methods to calculate achievements
 */

public class AchievementManager {

    private ScoreManager scoreManager;
    private BuildingManager buildingManager;
    private AchievementMenu achievementMenu;
    /**
     * list of achievements the player makes in a game
     */
    private ArrayList<Achievement> achievements = new ArrayList<>(3);

    public AchievementManager(ScoreManager scoreManager, BuildingManager buildingManager, AchievementMenu achievementMenu) {
        this.scoreManager = scoreManager;
        this.buildingManager = buildingManager;
        this.achievementMenu = achievementMenu;
    }

    /**
     * @return true if score is 200 or over
     */
    public boolean isScore200() {
        return scoreManager.getScore() >= 200;
    }

    /**
     * @return true if score is 400 or over
     */
    public boolean isScore400() {
        return scoreManager.getScore() >= 400;
    }

    /**
     * @return true if there are 50 or more than 50 buildings build by the player
     */
    public boolean areBuildingsFifty() {
        return buildingManager.getBuildingCount() > 50;
    }

    /**
     * @return true if the number of a certain type of buildings (eg. Recreational, Accomodation) are the the same
     */
    public boolean areEqualBuildingType() {
        Map<BuildingType, Integer> buildingCounts = buildingManager.getBuildingCounts();

        // Check if buildingCounts is empty
        if (buildingCounts.isEmpty()) {
            return true;  // Consider an empty map as "equal" (i.e., no buildings, no issue)
        }

        // Get the first value (for comparison)
        Integer firstCount = buildingCounts.values().iterator().next();

        // Iterate over all building counts and check if they match the first count
        for (int count : buildingCounts.values()) {
            if (count != firstCount) {
                return false; // Return false if any count doesn't match the first count
            }

        }

        return true; // All counts are equal, return true
    }

    /**
     * @return true if score is zero
     */
    public boolean haveZeroScore() {
        return scoreManager.getScore() == 0;
    }


    /**
     * displays the UI for the achievements
     */
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
        if (areEqualBuildingType() && buildingManager.getBuildingCount() != 0) achievements.add(new Achievement("Equaliser","You're the architect of balance, making sure every type of building get" +
            "s its fair share of the spotlight!", scoreManager, false));
        if (haveZeroScore()) achievements.add(new Achievement("Minimalist", "Zero? Well, looks like there is still a lot of space for improvement ",scoreManager, false));
        if (areBuildingsFifty()) achievements.add(new Achievement("Maximiser","50 Sites! We might need another campus to keep up with you" , scoreManager, true));
        if (isScore200()) {
            achievements.add(new Achievement("Champion","200? Gosh! we might need to bring up the heat for you" , scoreManager, true));
        } else if (isScore400()) {
            achievements.add(new Achievement("Champion","400? Gosh! we might need to bring up the heat for you" , scoreManager, true));
        }
    }

    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }


}
