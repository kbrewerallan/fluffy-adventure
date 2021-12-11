package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Simple test of the login pages many navigational links.
 */
public class LoginNavigationTests extends TestBase {

  @Test
  public void testSignUpLink() {
    SignUpPage signUp = login.clickSignUpLink();
    assertEquals(Constants.TITLE_SIGN_UP, signUp.getTitle(), "Sign up page title did not match expected.");
  }

  @Test
  public void testLoginWithOrganisationLink() {
    OrganisationLoginPage organisationLoginPage = login.clickLoginWithOrganisation();
    assertEquals(Constants.TITLE_ORGANIZATION_LOGIN, organisationLoginPage.getTitle(),
        "Organization login page title did not match expected.");
  }

  @Test
  public void testHudlLogoLink() {
    HudlHomePage home = login.clickHudlLogo();
    assertEquals(Constants.TITLE_HUDL_HOME, home.getTitle(), "Hudl home page title did not match expected.");
  }

  @Test
  public void testNeedHelpLink() {
    login.clickNeedHelp();
    assertTrue(login.isResetFormPresent(), "Reset password form was not present.");
  }
}