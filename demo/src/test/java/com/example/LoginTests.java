package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Simple test of the login / logout functionality of the hudl login page with
 * checks for invalid formats.
 */
public class LoginTests extends TestBase {

  private static final String INVALID_LOGIN = "We didn't recognize that email and/or password. Need help?";

  @Test
  public void testValidLogin() {
    login.login(email, password);
    HomePage home = new HomePage(driver);
    assertEquals(Constants.TITLE_HOME, home.getTitle(), "Homepage title did not match expected.");
  }

  @Test
  public void testLogout() {
    login.login(email, password);
    HomePage home = new HomePage(driver);
    HudlHomePage hudlHome = home.logout();
    assertEquals(Constants.TITLE_HUDL_HOME, hudlHome.getTitle(), "Hudl homepage title did not match expected.");
  }

  @Test
  public void testRememberMe() {
    assertFalse(login.isRememberMeSelected(), "Remember me checkbox was selected by default.");
    login.selectRememberMe();
    login.login(email, password);
    HomePage home = new HomePage(driver);
    assertEquals(Constants.TITLE_HOME, home.getTitle(), "Homepage title was not expected value.");

    driver.close();
    driver = new DriverFactory().getDriver(Constants.CHROME);
    driver.get(Constants.HOME_URL);

    home = new HomePage(driver);
    assertEquals(Constants.TITLE_HOME, home.getTitle(), "Homepage title did not match after navigating back to URL.");
  }

  private Stream<Arguments> provideEmailAndPasswords() {
    return Stream.of(
        Arguments.of("", ""),
        Arguments.of("aaa", ""),
        Arguments.of("aaa", "aaa"),
        Arguments.of("aaa@", ""),
        Arguments.of("aaa@test.com", "aaa"),
        Arguments.of(email, ""),
        Arguments.of(email, "NotMyPassword"),
        Arguments.of(email, "@!'''''--"));
  }

  @ParameterizedTest
  @MethodSource("provideEmailAndPasswords")
  public void testInvalidLogins(String givenEmail, String givenPassword) {
    login.login(givenEmail, givenPassword);
    assertEquals(INVALID_LOGIN, login.getLoginErrorMessage(), "Invalid login error message was not present.");
  }

  /* Tests for locked accounts could added at this point also. */
}