package io.github.unisim;

import com.badlogic.gdx.Game;
import io.github.unisim.building.Building;
import io.github.unisim.building.BuildingManager;
import io.github.unisim.building.BuildingType;
import io.github.unisim.world.World;

import java.util.ArrayList;
import java.util.Map;

/**
 * The Score manager.
 */
public class ScoreManager {
    /**
     * The Score
     */
    public static int score;
    /**
     * The Time elapsed.
     */
    public float timeElapsed;
    private final float DECREASE_INTERVAL = 2000;

    /**
     * Instantiates a new Score manager.
     */
    public ScoreManager() {
        score = 0;
        timeElapsed = 0;
    }

    /**
     * Runs everytime building placed to change score depending on where
     * building is
     *
     * @param placed         the placed
     * @param buildings      the buildings
     * @param buildingCounts the building counts
     */
    public void updateScore(Building placed, ArrayList<Building> buildings, Map<BuildingType,Integer> buildingCounts) {
        BuildingType type = placed.type;

        // 1. Initialize counters

        int recreationalCount = buildingCounts.get(BuildingType.RECREATION);
        int learningCount = buildingCounts.get(BuildingType.LEARNING);
        int sleepingCount = buildingCounts.get(BuildingType.SLEEPING);
        int eatingCount = buildingCounts.get(BuildingType.EATING);

        int placedCount = buildingCounts.get(type);

        // 3. Find the smallest and largest count among the 4 categories
        int minCount = Math.min(Math.min(recreationalCount, eatingCount), Math.min(sleepingCount, learningCount));
        int maxCount = Math.max(Math.max(recreationalCount, eatingCount), Math.max(sleepingCount, learningCount));

        // TODO 4. For now just returns baseScore of 10
        int baseScoreChange = calculateBaseScore(placed, buildings);

        // 5. The bigger imbalance grows, the smaller will score Change
        final int SCORE_MULTIPLIER = 2;

        int imbalanceScoreChange = (maxCount - minCount) * SCORE_MULTIPLIER;

        // 6. Handle cases when there are too few or too many buildings of the same type
        int totalScoreChange = baseScoreChange;

        totalScoreChange = placedCount == minCount ? totalScoreChange + imbalanceScoreChange : placedCount == maxCount ? totalScoreChange - imbalanceScoreChange : totalScoreChange;

        int newScore = score + totalScoreChange;
        
        setScore(checkScoreBounds(newScore));
    }

    /**
     * Check score bounds int.
     *
     * @param score the score
     * @return the int
     */
    public int checkScoreBounds(int score){
        return Math.max(score, 0);
    }

    /**
     * Calculate base score int.
     *
     * @param placed    the placed
     * @param buildings the buildings
     * @return the int
     */
    public int calculateBaseScore(Building placed, ArrayList<Building> buildings) {
        Building closest = null;
        double distance = 0;
        int baseScore = 10;
        for (Building building : buildings) {
            double distanceTemp = getBuildingDistance(building, placed);
            if(closest == null || distanceTemp < distance) {
                closest = building;
            }
        }
        if(placed.type == BuildingType.RECREATION) {
            /* Not sure how we want to deal with this */
        }
        return baseScore;
    }

    /**
     * Takes Building 1 and 2 and calculates distance
     * d = sqroot( sq(x2 - x1) + sq(y2 -y1))
     *
     * @return distance between buildings
     */
    private double getBuildingDistance(Building building, Building placed) {
        return Math.pow((Math.pow((building.location.x - placed.location.x),2) + Math.pow((building.location.y - placed.location.y),2)),0.5);
    }

    /**
     * Decrement score with time.
     *
     * @param deltaTime the delta time
     */
    public void decrementScoreWithTime(float deltaTime) {
            timeElapsed += deltaTime;
            if (timeElapsed >= DECREASE_INTERVAL) {
                if (score > 0) {
                    setScore(--score);
                    timeElapsed = 0;
                }
            }
    }


    /**
     * Sets score to 0 when called
     */
    public void resetScore() {
        score = 0;
    }

    /**
     * Sets user score to the parameter newValue
     *
     * @param newValue the new value
     */
    public void setScore(int newValue) {
        score = newValue;
    }

    /**
     * Returns user score of the current session
     *
     * @return current score (int)
     */
    public int getScore() {
        return score;
    }

    /**
     * Update score.
     *
     * @param updateValue the update value
     */
    public void updateScore(int updateValue){
        int newScore = score + updateValue;
        setScore(checkScoreBounds(newScore));
    }


}


