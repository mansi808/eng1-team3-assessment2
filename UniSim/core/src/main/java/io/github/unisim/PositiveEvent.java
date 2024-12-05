package io.github.unisim;

public class PositiveEvent extends Event{

    public PositiveEvent(String message, ScoreManager scoreManager) {
        super(message, scoreManager);
    }

    public void getImpact() {
        scoreManager.score += 10;
    }

}
