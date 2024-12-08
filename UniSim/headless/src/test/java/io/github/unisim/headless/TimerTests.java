package io.github.unisim.headless;

import io.github.unisim.Timer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimerTests extends AbstractHeadlessGdxTest {

  int numMin = 5;

  final int NUM_SEC_IN_MIN = 60;
  final int NUM_MILLISEC_IN_SEC = 1000;

  @Test

  public void testTick() {

    float initialTime = numMin * NUM_SEC_IN_MIN * NUM_MILLISEC_IN_SEC;

    Timer TestTimer = new Timer(initialTime);

    TestTimer.tick( 1 * NUM_MILLISEC_IN_SEC);

    assertEquals(TestTimer.getRemainingTime(), initialTime - ( 1 * NUM_MILLISEC_IN_SEC ));

    initialTime -= 1 * NUM_MILLISEC_IN_SEC;
    TestTimer.tick(5 * NUM_MILLISEC_IN_SEC);

    assertEquals(TestTimer.getRemainingTime(), initialTime - ( 5 * NUM_MILLISEC_IN_SEC ));

  }

  @Test

  public void testGetRemainingTimeToString() {

    float initialTime = numMin * NUM_SEC_IN_MIN * NUM_MILLISEC_IN_SEC;

    Timer TestTimer = new Timer(initialTime);

    assertEquals(TestTimer.getRemainingTimeToString(), "05:00");

  }

  @Test 

  public void testIsRunning() {

    float initialTime = numMin * NUM_SEC_IN_MIN * NUM_MILLISEC_IN_SEC;

    Timer TestTimer = new Timer(initialTime);

    assertEquals(TestTimer.isRunning(), true);

    TestTimer.tick(initialTime);

    assertEquals(TestTimer.isRunning(), false);

  }
  
}
