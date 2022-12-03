package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Disabled("Fixes Needed")
public class LoginWithOrganisationTests extends TestBase {

  private static final String ORGANISATION_ERROR = "This account can't log in with an organization yet. Please log in using your email and password.";
  OrganisationLoginPage oLoginPage;

  @BeforeEach
  void goToPage() {
    oLoginPage = login.clickLoginWithOrganisation();
  }

  @Test
  public void testLoginDefaultState() {
    assertFalse(oLoginPage.isLoginEnabled(), "Login button default state was not disabled.");
  }

  @Test
  public void testBackNavigation() {
    login = oLoginPage.clickBackToLogin();
    assertEquals(Constants.TITLE_LOGIN, login.getTitle(), "Did not navigate back to login page.");
  }

  private Stream<Arguments> provideFormats() {
    return Stream.of(
        Arguments.of("aa", false, false, ""),
        Arguments.of("aaa", true, true, "Please include an '@' in the email address. 'aaa' is missing an '@'."),
        Arguments.of("aaa@", true, true, "Please enter a part following '@'. 'aaa@' is incomplete."),
        Arguments.of("aaa@.", true, true, "'.' is used at a wrong position in '.'."));
  }

  @ParameterizedTest
  @MethodSource("provideFormats")
  public void testInvalidFormats(String email, boolean loginEnabled, boolean messagePresent, String message) {
    oLoginPage.setEmail(email);
    assertEquals(loginEnabled, oLoginPage.isLoginEnabled(), "Login enabled state did not match expected.");
    if (messagePresent) {
      oLoginPage.clickLogin();
      assertEquals(message, oLoginPage.getInvalidEmailMessage(), "Email validation message did not match expected.");
    }
  }

  @Test
  public void testInvalidLogin() {
    oLoginPage.login("test@test.com");
    login = new LoginPage(driver);
    assertEquals(ORGANISATION_ERROR,
        login.getOrganisationLoginErrorMessage(), "Error message for organisation did not match the expected value.");
  }

  /*
   * Would also add a test for a valid account if one was provided in this
   * scenario.
   */
}