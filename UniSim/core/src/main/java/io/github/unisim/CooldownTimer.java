package io.github.unisim;


import io.github.unisim.building.Building;
import io.github.unisim.building.BuildingType;

public class CooldownTimer {
    private float cooldownDuration;
    private float elapsedTime;

    public CooldownTimer(float cooldownDuration) {
        this.cooldownDuration = cooldownDuration;
        this.elapsedTime = 10;

    }

    public void update(float deltaTimer) {
        if(elapsedTime < cooldownDuration){
            elapsedTime += deltaTimer;
        }
    }

    public void startCooldown(BuildingType selectedBuildingType) {

        switch(selectedBuildingType) {

            case RECREATION:
                cooldownDuration = 5;

            case LEARNING:
                cooldownDuration = 10;

            case SLEEPING:
                cooldownDuration = 10;

            case EATING:
                cooldownDuration = 5;
        }

        elapsedTime = 0;
    }

    public boolean isCooldownComplete() {
        return elapsedTime >= cooldownDuration;
    }

    public int getCooldownDuration() {
        float time = cooldownDuration - elapsedTime;
        return (int) time;
    }
}
