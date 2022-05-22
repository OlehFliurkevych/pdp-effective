package com.fliurkevych.task1;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PdpEffectiveTest
  extends TestCase {

  /**
   * Create the test kcase
   *
   * @param testName name of the test case
   */
  public PdpEffectiveTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(PdpEffectiveTest.class);
  }

  /**
   * Rigourous Test :-)
   */
  public void testApp() {
    assertTrue(true);
  }
}
