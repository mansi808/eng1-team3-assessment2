package io.github.unisim;

import io.github.unisim.ui.EventMenu;

import java.util.ArrayList;
import java.util.Random;

public class EventManager {
    private ArrayList<Event> events = new ArrayList<>();


    public EventManager(Timer timer, EventMenu eventMenu, ScoreManager scoreManager) {
        events.add(new PositiveEvent("This is a positive event.", scoreManager));
        events.add(new NegativeEvent("This is a negative event.", scoreManager));
        events.add(new NeutralEvent("This is a neutral event.", scoreManager));
        Event currentEvent = getRandomEvent();
        eventMenu.setCurrentEvent(currentEvent);
        eventMenu.update();
    }




    public Event getRandomEvent() {
        Random rand = new Random();
        int i = rand.nextInt(0,events.size());
        return events.get(i);
    }
}
