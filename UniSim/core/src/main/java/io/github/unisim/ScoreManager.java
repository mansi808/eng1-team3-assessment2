package io.github.unisim;

import com.badlogic.gdx.Game;
import io.github.unisim.building.Building;
import io.github.unisim.building.BuildingManager;
import io.github.unisim.building.BuildingType;
import io.github.unisim.building.data.types.BuildingTuple;
import io.github.unisim.world.World;

import java.util.ArrayList;

public class ScoreManager {
    public static int score;
    public float timeElapsed;
    private final float DECREASE_INTERVAL = 2000;

    public ScoreManager() {
        score = 0;
        timeElapsed = 0;
    }

    /**
     * Runs everytime building placed to change score depending on where
     * building is
     *
     */
    public void updateScore(Building placed, ArrayList<Building> buildings, ArrayList<BuildingTuple> buildingTuples) {
        BuildingType type = placed.type;

        // 1. Initialize counters

        int recreationalCount = 0;
        int learningCount = 0;
        int sleepingCount = 0;
        int eatingCount = 0;

        int placedCount = 0;


        // 2. Get total number of each category of buildings

        for (BuildingTuple eachTuple : buildingTuples) {

            // Get counts from the array
            if (eachTuple.type == type) placedCount = eachTuple.count;

            switch (eachTuple.type) {

                case RECREATION:
                    recreationalCount = eachTuple.count;

                case LEARNING:
                    learningCount = eachTuple.count;

                case SLEEPING:
                    sleepingCount = eachTuple.count;

                case EATING:
                    eatingCount = eachTuple.count;

                default:
                    break;
            }

        };

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

        setScore(score + totalScoreChange);
    }

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

    public void updateScore(int updateValue){
        score += updateValue;
    }


}


