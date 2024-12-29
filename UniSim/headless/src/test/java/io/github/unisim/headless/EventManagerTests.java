package io.github.unisim.headless;

import org.junit.jupiter.api.Test;

import io.github.unisim.Event;
import io.github.unisim.EventManager;

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
}
