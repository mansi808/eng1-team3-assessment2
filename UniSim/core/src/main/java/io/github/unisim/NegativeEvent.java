package io.github.unisim;

public class NegativeEvent extends Event{

    public NegativeEvent(String message, ScoreManager scoreManager) {
        super(message, scoreManager);
    }

    public void getImpact() {
        scoreManager.score -= 10;
    }
}
