package io.github.unisim.headless;

import io.github.unisim.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventManagerTests extends AbstractHeadlessGdxTest {
  @Test

  // this method must return a random event back and remove it from the events array
  public void testGetRandomEvent() {
    EventManager TestEventManager = new EventManager(null, null, null);
    int initialArraySize = TestEventManager.getEvents().size();

    Event TestEvent1 = TestEventManager.getRandomEvent();

    assertEquals(Event.class,TestEvent1.getClass().getSuperclass());
    assertEquals(initialArraySize - 1, TestEventManager.getEvents().size());

    Event TestEvent2 = TestEventManager.getRandomEvent();

    assertEquals(Event.class,TestEvent2.getClass().getSuperclass());
    assertEquals(initialArraySize - 2, TestEventManager.getEvents().size());

  }

  @Test
  public void testSingleEventBehaviour() {
    ScoreManager scoreManager = new ScoreManager();
    SingleEvent singleEvent = new SingleEvent("Test single event", scoreManager, 10);

    // Trigger the effect of the event
    singleEvent.getImpact("Test single event");

    // Check if the score manager was updated correctly
    assertEquals(10, scoreManager.getScore(), "Score should increase by 10 after triggering the SingleEvent.");
  }

  @Test
  public void testChoiceEventBehaviourOption1() {
    ScoreManager scoreManager = new ScoreManager();
    ChoiceEvent choiceEvent = new ChoiceEvent(
            "Test choice event",
            scoreManager,
            "Option 1", 15,
            "Option 2", -10
    );

    // Trigger the first option of the ChoiceEvent
    choiceEvent.getImpact("Option 1");

    // Check if the score manager was updated correctly
    assertEquals(15, scoreManager.getScore(), "Score should increase by 15 after selecting Option 1.");
  }
  @Test
  public void testChoiceEventBehaviourOption2() {
    ScoreManager scoreManager = new ScoreManager();
    ChoiceEvent choiceEvent = new ChoiceEvent(
            "Test choice event",
            scoreManager,
            "Option 1", 15,
            "Option 2", -10
    );
    scoreManager.setScore(20);
    // Trigger the first option of the ChoiceEvent
    choiceEvent.getImpact("Option 2");


    // Check if the score manager was updated correctly
    assertEquals(10, scoreManager.getScore(), "Score should increase by 15 after selecting Option 1.");
  }

}
