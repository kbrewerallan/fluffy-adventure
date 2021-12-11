package com.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrganisationLoginPage extends LayoutPage {
  /**
   * Page factory object for the Hudl Organisation login page.
   * 
   * @param driver the {@link WebDriver} instance used to initialise the page.
   */
  public OrganisationLoginPage(WebDriver driver) {
    super(driver);
    wait.until(ExpectedConditions.titleIs("Log In with Organization - Hudl"));
  }

  @FindBy(name = "username")
  private WebElement txtEmail;

  @FindBy(css = "[data-qa-id='log-in-with-sso']")
  private WebElement btnLoginWithOrganization;

  @FindBy(css = "[data-qa-id='log-in-with-email-and-password']")
  private WebElement btnBackToLogin;

  /**
   * Sets a given value in the email input.
   * 
   * @param email the value being set.
   */
  public void setEmail(String email) {
    wait.until(ExpectedConditions.visibilityOf(txtEmail));
    txtEmail.sendKeys(email);
  }

  /**
   * Interacts with the login button.
   */
  public void clickLogin() {
    wait.until(ExpectedConditions.visibilityOf(btnLoginWithOrganization));
    btnLoginWithOrganization.click();
  }

  /**
   * Returns the enabled state of the login button.
   * 
   * @return a boolean value dependant on the enabled state.
   */
  public boolean isLoginEnabled() {
    wait.until(ExpectedConditions.visibilityOf(btnLoginWithOrganization));
    return btnLoginWithOrganization.isEnabled();
  }

  /**
   * Interacts with the back to login button.
   * 
   * @return an instance of the {@link LoginPage}.
   */
  public LoginPage clickBackToLogin() {
    wait.until(ExpectedConditions.visibilityOf(btnBackToLogin));
    btnBackToLogin.click();
    return new LoginPage(driver);
  }

  /**
   * Attempts to fully log the user in by entering an email address and hitting
   * the login button.
   * 
   * @param email the value being set in the email input.
   */
  public void login(String email) {
    setEmail(email);
    clickLogin();
  }

  /**
   * Returns the email address format error message if present.
   * 
   * @return the message text.
   */
  public String getInvalidEmailMessage() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", txtEmail);
    System.out.println(validationMessage);
    return validationMessage;
  }
}
