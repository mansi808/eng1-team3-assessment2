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
        int baseScore = 5;
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

    public void decrementScore(float deltaTime) {
        if (!GameState.paused && !GameState.gameOver) {
            timeElapsed += deltaTime;
            if (timeElapsed >= decreaseInterval) {
                if (score > 0) {
                    score -= 1;
                    timeElapsed = 0;
                }
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

    /**
     * Takes Building 1 and 2 and calculates distance
     * d = sqroot( sq(x2 - x1) + sq(y2 -y1))
     *
     * @return distance between buildings
     */
    private int getBuildingDistance(Building placedBuilding) {
        return 0;
    }

}


