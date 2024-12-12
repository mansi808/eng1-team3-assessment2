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

    public void startCooldown(Building selectedBuilding) {
        if (selectedBuilding.type == BuildingType.RECREATION){
            cooldownDuration = 5;
        } else if (selectedBuilding.type == BuildingType.LEARNING){
            cooldownDuration = 10;
        }else if (selectedBuilding.type == BuildingType.SLEEPING){
            cooldownDuration = 10;
        }else if (selectedBuilding.type == BuildingType.EATING){
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
