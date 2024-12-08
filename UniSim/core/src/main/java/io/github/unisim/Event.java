package io.github.unisim;

public abstract class Event {


    /**
     * If true, player can respond to event, if negative, mitigate the situation
     */
    public boolean respond;
    private String message;
    ScoreManager scoreManager;


    public Event(String message, ScoreManager scoreManager) {
        this.message = message;
        this.scoreManager = scoreManager;

        this.respond = false;
    }

    public String getMessage() {
        return message;
    }

    public abstract void getImpact();

}
