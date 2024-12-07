package io.github.unisim;

import com.badlogic.gdx.Game;
import io.github.unisim.building.Building;
import io.github.unisim.building.BuildingManager;
import io.github.unisim.building.BuildingType;
import io.github.unisim.world.World;

import java.util.ArrayList;

public class ScoreManager {
    public static int score;
    public float timeElapsed;
    public float decreaseInterval;
    private World world;

    public ScoreManager() {
        score = 0;
        timeElapsed = 0;
        decreaseInterval = 2000;

    }

    /**
     * Runs everytime building placed to change score depending on where
     * building is
     *
     */
    public void UpdateScore(Building placed, ArrayList<Building> buildings, World world) {
        BuildingType type = placed.type;
        int recreational = world.getBuildingCount(BuildingType.RECREATION);
        int learning = world.getBuildingCount(BuildingType.LEARNING);
        int eating = world.getBuildingCount(BuildingType.EATING);
        int sleeping =world.getBuildingCount(BuildingType.SLEEPING);

        int minCount = Math.min(Math.min(recreational, eating), Math.min(sleeping, learning));
        int maxCount = Math.max(Math.max(recreational, eating), Math.max(sleeping, learning));
        int imbalance = maxCount - minCount;
        int baseScore = calculateBaseScore(placed, buildings);
        int scoreIncrease = imbalance * 2;

        if((type.equals(BuildingType.RECREATION) && recreational == minCount) || (type.equals(BuildingType.LEARNING) && learning == minCount)
            || (type.equals(BuildingType.SLEEPING) && sleeping == minCount) || (type.equals(BuildingType.EATING) && eating == minCount)) {
            score += baseScore + scoreIncrease;
        } else if ((type.equals(BuildingType.RECREATION) && recreational == maxCount) || (type.equals(BuildingType.LEARNING) && learning == maxCount)
            || (type.equals(BuildingType.SLEEPING) && sleeping == maxCount) || (type.equals(BuildingType.EATING) && eating == maxCount)) {
            score += baseScore - scoreIncrease;
        } else{
            score += baseScore;
        }
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
            if (timeElapsed >= decreaseInterval) {
                if (score > 0) {
                    score -= 1;
                    timeElapsed = 0;
                }
            }
    }

    /**
     * Sets score when called to 0
     */
    public void setScore() {

    }

    /**
     * Returns score
     *
     * @return
     */
    public int getScore() {
        return 0;
    }


}


