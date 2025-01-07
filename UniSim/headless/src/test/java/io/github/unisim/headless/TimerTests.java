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

    // Ticking 1 second should decrease remaining time by 1 sec

    TestTimer.tick( 1 * NUM_MILLISEC_IN_SEC);

    assertEquals( initialTime - ( 1 * NUM_MILLISEC_IN_SEC ), TestTimer.getRemainingTime() );

    initialTime -= 1 * NUM_MILLISEC_IN_SEC;

    // Ticking by more than the Timer's remaining time, tick should set remaining time to 0

    TestTimer.tick(numMin * NUM_SEC_IN_MIN * NUM_MILLISEC_IN_SEC);

    assertEquals(0, TestTimer.getRemainingTime());

  }

  @Test

  public void testGetRemainingTimeToString() {

    // Returning remaining time as string should return correct format 

    float initialTime = numMin * NUM_SEC_IN_MIN * NUM_MILLISEC_IN_SEC;

    Timer TestTimer = new Timer(initialTime);

    assertEquals("05:00", TestTimer.getRemainingTimeToString());

    TestTimer.tick(1000);

    assertEquals("04:59", TestTimer.getRemainingTimeToString());

  }

  @Test 

  public void testIsRunning() {

    float initialTime = numMin * NUM_SEC_IN_MIN * NUM_MILLISEC_IN_SEC;

    Timer TestTimer = new Timer(initialTime);

    // Timer should be running in the beginning

    assertEquals(TestTimer.isRunning(), true);

    TestTimer.tick(initialTime);

    // When timer finished, it should stop running

    assertEquals(TestTimer.isRunning(), false);

  }
  
}
