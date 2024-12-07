package io.github.unisim.headless;

import io.github.unisim.Timer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimerTests extends AbstractHeadlessGdxTest {

  @Test

  public void testTick() {
    float initialTime = 5 * 60 * 1000;

    Timer TestTimer = new Timer(initialTime);

    TestTimer.tick(1000);

    assertEquals(TestTimer.getRemainingTime(), initialTime - 1000);

  }
  
}
