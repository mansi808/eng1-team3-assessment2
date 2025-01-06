package io.github.unisim;

/**
 * The Single event type.
 */
public class SingleEvent extends Event{
    private final int updateScore;

    /**
     * Instantiates a new Single event.
     *
     * @param prompt       the prompt
     * @param scoreManager the score manager
     * @param updateScore  the update score
     */
    public SingleEvent(String prompt, ScoreManager scoreManager, int updateScore) {
        super(prompt, scoreManager);
        this.updateScore = updateScore;
    }


    @Override
    public void getImpact(String buttonMessage) {
        scoreManager.updateScore(updateScore);
    }
}
