package io.github.unisim;

import java.awt.*;

/**
 * Choice Event
 */
public class ChoiceEvent extends Event {
    private String positiveMessage;
    private String negativeMessage;
    private int positiveImpact;
    private int negativeImpact;

    /**
     * Instantiates a new Choice event.
     *
     * @param prompt          the choice prompt
     * @param scoreManager    the score manager
     * @param positiveMessage the positive message
     * @param positiveImpact  the positive impact
     * @param negativeMessage the negative message
     * @param negativeImpact  the negative impact
     */
    public ChoiceEvent(String prompt, ScoreManager scoreManager, String positiveMessage, int positiveImpact, String negativeMessage, int negativeImpact) {
        super(prompt, scoreManager);
        this.positiveMessage = positiveMessage;
        this.negativeMessage = negativeMessage;
        this.positiveImpact = positiveImpact;
        this.negativeImpact = negativeImpact;
    }


    /**
     * Gets positive message.
     *
     * @return the positive message
     */
    public String getPositiveMessage() {return positiveMessage;}

    /**
     * Gets negative message.
     *
     * @return the negative message
     */
    public String getNegativeMessage() {return negativeMessage;}
    @Override
    public void getImpact(String buttonMessage) {
        if (buttonMessage.equals(positiveMessage)){
            scoreManager.updateScore(positiveImpact);
        } else if (buttonMessage.equals(negativeMessage)) {
            scoreManager.updateScore(negativeImpact);

        }


    }

}
