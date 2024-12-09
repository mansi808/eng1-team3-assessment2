package io.github.unisim;

public class singleEvent extends Event{
    private final int updateScore;
    public singleEvent(String prompt, ScoreManager scoreManager, int updateScore) {
        super(prompt, scoreManager);
        this.updateScore = updateScore;
    }

    @Override
    public void getImpact(String buttonMessage) {
        scoreManager.updateScore(updateScore);
    }
}
