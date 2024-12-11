package io.github.unisim;

public class SingleEvent extends Event{
    private final int updateScore;
    public SingleEvent(String prompt, ScoreManager scoreManager, int updateScore) {
        super(prompt, scoreManager);
        this.updateScore = updateScore;
    }

    @Override
    public void getImpact(String buttonMessage) {
        scoreManager.updateScore(updateScore);
    }
}
