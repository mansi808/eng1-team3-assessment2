package io.github.unisim;

import java.awt.*;

public class ChoiceEvent extends Event {
    private String positiveMessage;
    private String negativeMessage;

    public ChoiceEvent(String prompt, ScoreManager scoreManager, String positiveMessage, String negativeMessage) {
        super(prompt, scoreManager);
        this.positiveMessage = positiveMessage;
        this.negativeMessage = negativeMessage;
    }


    public String getPositiveMessage() {return positiveMessage;}
    public String getNegativeMessage() {return negativeMessage;}
    @Override
    public void getImpact(String buttonMessage) {
        if (buttonMessage.equals(positiveMessage)){
            scoreManager.updateScore(10);
            return;
        } else if (buttonMessage.equals(negativeMessage)) {
            scoreManager.updateScore(-10);
            return;

        }


    }

}
