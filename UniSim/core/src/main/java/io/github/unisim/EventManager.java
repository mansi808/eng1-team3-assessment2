package io.github.unisim;

import io.github.unisim.ui.EventMenu;

import java.util.ArrayList;
import java.util.Random;

public class EventManager {
    private ArrayList<Event> events = new ArrayList<>();
    private EventMenu eventMenu;
    private Timer timer;
    private float timeElapsed;
    private float interval = 5000;


    public EventManager(Timer timer, EventMenu eventMenu, ScoreManager scoreManager) {
        this.timer = timer;
        this.eventMenu = eventMenu;
        events.add(new PositiveEvent("This is a positive event.", scoreManager));
        events.add(new NegativeEvent("This is a negative event.", scoreManager));
        events.add(new NeutralEvent("This is a neutral event.", scoreManager));
        }

    public void setEvents() {
        Event currentEvent = getRandomEvent();
        eventMenu.setCurrentEvent(currentEvent);
        eventMenu.update();
    }

    public void showEvent(float deltaTime) {
        timeElapsed += deltaTime;
        if (!GameState.paused && timeElapsed >= interval) {
            setEvents();
            timeElapsed = 0;
        }
    }

    public Event getRandomEvent() {
        Random rand = new Random();
        int i = rand.nextInt(0,events.size());
        return events.get(i);
    }
}
