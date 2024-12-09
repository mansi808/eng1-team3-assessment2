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
    private float interval = 100f;
    private Stage stage;
    private ScoreManager scoreManager;

    public EventManager(Timer timer, Stage stage, ScoreManager scoreManager) {
        this.timer = timer;
        this.stage = stage;
        this.scoreManager = scoreManager;


        // Add different types of events
        events.add(new PositiveEvent("This is a positive event.", scoreManager));
        events.add(new NegativeEvent("This is a negative event.", scoreManager));
        events.add(new NeutralEvent("This is a neutral event.", scoreManager));
    }

    public EventMenu setEvents() {
        // Create a new event menu and set the current event
        eventMenu = new EventMenu(stage, scoreManager);
        System.out.println(stage);
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
