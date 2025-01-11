package io.github.unisim;

public class Achievement {

    private String description = "";
    private String title;
    private ScoreManager  scoreManager;
    /**
     * If it is a positive impact, score increases
     */
    private boolean isPositiveImpact;

    public Achievement(String title, String description, ScoreManager scoreManager, boolean isPositiveImpact) {
        setDescription(description);
        this.title = title;
        this.scoreManager = scoreManager;
        this.isPositiveImpact = isPositiveImpact;
        if (isPositiveImpact) setPositiveImpact();
        else setNegativeImpact();
    }

    public void setPositiveImpact() {
        scoreManager.updateScore(5);
    }

    public void setNegativeImpact() {
        scoreManager.setScore(-5);
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String message) {
        this.description = message;
    }

    public boolean isPositiveImpact() {
        return isPositiveImpact;
    }


}
