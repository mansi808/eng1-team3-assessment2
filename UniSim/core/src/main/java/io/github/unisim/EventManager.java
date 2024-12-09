package io.github.unisim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.unisim.ui.EventMenu;

import java.util.ArrayList;
import java.util.Random;
public class EventManager {
    private ArrayList<Event> events = new ArrayList<>();
    private EventMenu eventMenu;
    private Timer timer;
    private float timeElapsed;
    private float interval = 90f;
    private Stage stage;
    private ScoreManager scoreManager;

    public EventManager(Timer timer, EventMenu eventMenu, ScoreManager scoreManager) {
        this.timer = timer;
        this.eventMenu = eventMenu;
        this.scoreManager = scoreManager;


        // Add different types of events
        events.add(new singleEvent("Good thing happens", scoreManager, 10));
        events.add( new ChoiceEvent("Good or Bad?", scoreManager, "Good", "Bad"));
    }

    public EventMenu setEvents() {
        // Create a new event menu and set the current event
        Event currentEvent = getRandomEvent();
        eventMenu.setCurrentEvent(currentEvent);
        eventMenu.update();
        return eventMenu;
    }

    public EventMenu showEvent() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        timeElapsed += deltaTime;
        if (!GameState.paused && timeElapsed >= interval) {
            GameState.paused = true;
            setEvents();
            timeElapsed = 0;
            return eventMenu;
        }
        return null;
    }

    public Event getRandomEvent() {
        Random rand = new Random();
        int i = rand.nextInt(events.size());
        return events.get(i);
    }
}
