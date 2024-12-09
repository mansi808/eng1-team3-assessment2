package io.github.unisim;

public abstract class Event {


    /**
     * If true, player can respond to event, if negative, mitigate the situation
     */
    public boolean respond;
    private String prompt;

    ScoreManager scoreManager;


    public Event(String prompt, ScoreManager scoreManager) {
        this.prompt = prompt;
        this.scoreManager = scoreManager;
        this.respond = false;
    }

    public String getMessage() {
        return prompt;
    }

    public abstract void getImpact(String buttonMessage);

}
