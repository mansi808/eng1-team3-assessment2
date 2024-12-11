package io.github.unisim;

import java.awt.*;

public class ChoiceEvent extends Event {
    private String positiveMessage;
    private String negativeMessage;
    private int positiveImpact;
    private int negativeImpact;

    public ChoiceEvent(String prompt, ScoreManager scoreManager, String positiveMessage, int positiveImpact, String negativeMessage, int negativeImpact) {
        super(prompt, scoreManager);
        this.positiveMessage = positiveMessage;
        this.negativeMessage = negativeMessage;
        this.positiveImpact = positiveImpact;
        this.negativeImpact = negativeImpact;
    }


    public String getPositiveMessage() {return positiveMessage;}
    public String getNegativeMessage() {return negativeMessage;}
    @Override
    public void getImpact(String buttonMessage) {
        if (buttonMessage.equals(positiveMessage)){
            scoreManager.updateScore(positiveImpact);
            return;
        } else if (buttonMessage.equals(negativeMessage)) {
            scoreManager.updateScore(negativeImpact);
            return;

        }


    }

}
