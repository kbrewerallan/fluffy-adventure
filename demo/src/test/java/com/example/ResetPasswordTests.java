package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Simple test of the reset password page and functions.
 */
public class ResetPasswordTests extends TestBase {

  @BeforeEach
  void pullUpResetForm() {
    login.clickNeedHelp();
  }

  @ParameterizedTest
  @ValueSource(strings = { "a", "AvC", "", "333400", "£&*()_+_((((" })
  public void testEmailValueIsConsistent(String emailValue) {
    login.clickBackToLogin();
    login.setEmail(emailValue);
    login.clickNeedHelp();
    assertEquals(emailValue, login.getResetEmail(), "Email value was not consistent for the passed example.");
  }

  @Test
  public void testResetButtonState() {
    assertFalse(login.isResetPasswordEnabled(), "Reset button was not disabled by default.");
    login.setResetEmail("test@test.com");
    assertTrue(login.isResetPasswordEnabled(), "Rest button was not enabled with value in the email input.");
  }

  /**
   * With a valid way of receiving the help information and not having it hard
   * coded, checking that it is
   * correct and is in the expected format would be good to add here.
   */

  /*
   * A full test of resetting the password would be useful at this point but isn't
   * possible with the demonstration.
   */
}