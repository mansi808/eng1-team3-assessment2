package io.github.unisim;

import io.github.unisim.building.Building;

public class ScoreManager {
    public static int score = 69;

    public ScoreManager(){

    }

    /**
     * Runs everytime building placed to change score depending on where
     * building is
     */
    public void UpdateScore() {
        score += 10;
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
    private int getBuildingDistance(Building building1, Building building2) {

        return 0;
    }
}


