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
        events.add(new SingleEvent("A philanthropist donates £1 million for scholarships!", scoreManager, 20));
        events.add(new SingleEvent("A water pipe bursts, flooding a lecture hall.", scoreManager, -15));
        events.add(new SingleEvent("The university ranks in the top 10 nationally this year!", scoreManager, 15));
        events.add(new SingleEvent("An academic scandal damages the university's reputation.", scoreManager, -25));
        events.add(new ChoiceEvent(
                "A viral social media post accuses the university of unfair grading policies.",
                scoreManager,
                "Respond (address concerns but draw attention to the issue)", 5,
                "Ignore (avoid publicity but risk eroding trust)", -10
        ));

        events.add(new ChoiceEvent(
                "An alumnus offers to endow a scholarship program. Accept or set conditions?",
                scoreManager,
                "Accept (gain funds but cede control over criteria)", 20,
                "Set conditions (retain control but risk losing the donation)", 10
        ));

        events.add(new ChoiceEvent(
                "A student-led protest disrupts classes.",
                scoreManager,
                "Support (uphold free speech but alienate some stakeholders)", 10,
                "Condemn (appease critics but anger students)", -10
        ));

        events.add(new ChoiceEvent(
                "An overseas university invites collaboration on a cultural exchange program.",
                scoreManager,
                "Join (enhance diversity but incur costs)", 15,
                "Decline (save money but miss a global opportunity)", -5
        ));

    }

    public void setEvents() {
        // Create a new event menu and set the current event
        if (events.isEmpty()){
            return;
        }
        Event currentEvent = getRandomEvent();
        eventMenu.setCurrentEvent(currentEvent);
        eventMenu.update();
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
        Event selectedEvent = events.get(i);
        events.remove(i);

        return selectedEvent;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
