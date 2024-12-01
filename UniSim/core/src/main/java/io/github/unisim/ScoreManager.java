package io.github.unisim;

import com.badlogic.gdx.Game;
import io.github.unisim.building.Building;

public class ScoreManager {
    public static int score;
    public float timeElapsed;
    public float decreaseInterval;

    public ScoreManager(){
        score = 0;
        timeElapsed = 0;
        decreaseInterval = 2000;
    }

    /**
     * Runs everytime building placed to change score depending on where
     * building is
     */
    public void UpdateScore() {
        score += 10;
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
     * Resets score when called to 0
     */
    public void resetScore() {
        score = 0;
    }

    /**
     * Returns score
     *
     * @return
     */
    public int getScore() {
        return score;
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


