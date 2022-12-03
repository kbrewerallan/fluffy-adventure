package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends LayoutPage {

  /**
   * Page factory object for the Hudl given login page.
   * 
   * @param driver the {@link WebDriver} instance used to initialise the page.
   */
  public LoginPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(id = "email")
  private WebElement txtEmail;

  @FindBy(id = "password")
  private WebElement txtPassword;

  @FindBy(id = "logIn")
  private WebElement btnLogin;

  @FindBy(id = "remember-me")
  private WebElement chkRememberMe;

  @FindBy(css = "[for='remember-me']")
  private WebElement chkRememberMeInteractable;

  @FindBy(css = "[data-qa-id='need-help-link']")
  private WebElement lnkNeedHelp;

  @FindBy(css = "[data-qa-id='log-in-with-organization-btn']")
  private WebElement lnkLoginWithOrganisation;

  @FindBy(css = "[href='/register/signup']")
  private WebElement lnkSignUp;

  @FindBy(className = "login-container")
  private WebElement loginContainer;

  @FindBy(css = "[data-qa-id='lets-reset-password-headline']")
  private WebElement resetContainer;

  @FindBy(css = "[data-qa-id='hudl-logo']")
  private WebElement imgHudl;

  @FindBy(className = "login-error-container")
  private WebElement loginErrorContainer;

  @FindBy(className = "login-error-container-code")
  private WebElement organisationLoginError;

  @FindBy(id = "forgot-email")
  private WebElement txtForgotEmail;

  @FindBy(id = "resetBtn")
  private WebElement btnSendPasswordReset;

  @FindBy(className = "reset-error-container")
  private WebElement resetErrorContainer;

  @FindBy(id = "back-to-login")
  private WebElement lnkBackToLogin;

  private WebElement btnBackToHudl() {
    return driver.findElement(By.className("back-to-hudl")).findElement(By.className("icon-back"));
  }

  /**
   * Sets the email input with the given value.
   * 
   * @param email the value being set.
   */
  public void setEmail(String email) {
    wait.until(ExpectedConditions.visibilityOf(txtEmail));
    txtEmail.clear();
    txtEmail.sendKeys(email);
  }

  /**
   * sets the password input with the given value.
   * 
   * @param password the password value being set.
   */
  public void setPassword(String password) {
    wait.until(ExpectedConditions.visibilityOf(txtPassword));
    txtPassword.clear();
    txtPassword.sendKeys(password);
  }

  /**
   * Interacts with the login button.
   */
  public void clickLogin() {
    wait.until(ExpectedConditions.visibilityOf(btnLogin));
    btnLogin.click();
  }

  /**
   * Attempts to log the user in by setting an email and password before clicking
   * the login button.
   * 
   * @param email    the email value being set.
   * @param password the password value being set.
   */
  public void login(String email, String password) {
    setEmail(email);
    setPassword(password);
    clickLogin();
  }

  /**
   * Checks the remember me checkbox.
   */
  public void selectRememberMe() {
    if (!isRememberMeSelected()) {
      chkRememberMeInteractable.click();
    }
  }

  /**
   * un-checks the remember me checkbox.
   */
  public void unselectRememberMe() {
    if (isRememberMeSelected()) {
      chkRememberMeInteractable.click();
    }
  }

  /**
   * Returns a boolean value depending on the selected state of the remember me
   * checkbox.
   * 
   * @return the selected state boolean value.
   */
  public boolean isRememberMeSelected() {
    wait.until(ExpectedConditions.visibilityOf(chkRememberMe));
    return chkRememberMe.isSelected();
  }

  /**
   * Returns a boolean depending on the displayed state of an error.
   * 
   * @return the displayed state of the error.
   */
  public boolean isErrorPresent() {
    return loginErrorContainer.isDisplayed();
  }

  /**
   * Returns the value of the login error message.
   * 
   * @return the value present in the error.
   */
  public String getLoginErrorMessage() {
    wait.until(ExpectedConditions.visibilityOf(loginErrorContainer));
    return loginErrorContainer.getText();
  }

  /**
   * Returns the value of the organisation login error message.
   * 
   * @return the value present in the error.
   */
  public String getOrganisationLoginErrorMessage() {
    wait.until(ExpectedConditions.visibilityOf(organisationLoginError));
    return organisationLoginError.getText();
  }

  /**
   * Interacts with the need help ? link.
   */
  public void clickNeedHelp() {
    wait.until(ExpectedConditions.visibilityOf(lnkNeedHelp));
    wait.until(ExpectedConditions.elementToBeClickable(lnkNeedHelp));
    
    // Buggy here clicking the need help link quickly after navigating back.
    try {
      lnkNeedHelp.click();
      wait.until(ExpectedConditions.visibilityOf(resetContainer));
    } catch (TimeoutException e) {
      lnkNeedHelp.click();
      wait.until(ExpectedConditions.visibilityOf(resetContainer));
    }
  }

  /**
   * Returns a boolean value dependant on the reset password form being present.
   * 
   * @return the displayed state of the reset container.
   */
  public boolean isResetFormPresent() {
    return resetContainer.isDisplayed();
  }

  /**
   * Returns a boolean value dependant on if the reset password button is enabled.
   * 
   * @return the enabled state of the reset button.
   */
  public boolean isResetPasswordEnabled() {
    return btnSendPasswordReset.isEnabled();
  }

  /**
   * Sets the reset password email value.
   * 
   * @param email the email for the password being reset.
   */
  public void setResetEmail(String email) {
    txtForgotEmail.clear();
    txtForgotEmail.sendKeys(email);
  }

  /**
   * Gets the email input value on the reset password container.
   * 
   * @return the currently inputted email value.
   */
  public String getResetEmail() {
    return txtForgotEmail.getAttribute("value");
  }

  /**
   * Interacts with the back to login link.
   */
  public void clickBackToLogin() {
    lnkBackToLogin.click();
  }

  /**
   * Interacts with the login with organisation link.
   * 
   * @return an instance of the {@link OrganisationLoginPage}.
   */
  public OrganisationLoginPage clickLoginWithOrganisation() {
    wait.until(ExpectedConditions.visibilityOf(lnkLoginWithOrganisation));
    lnkLoginWithOrganisation.click();
    return new OrganisationLoginPage(driver);
  }

  /**
   * Interacts with the sign up link.
   * 
   * @return an instance of the {@link SignUpPage}.
   */
  public SignUpPage clickSignUpLink() {
    wait.until(ExpectedConditions.visibilityOf(lnkSignUp));
    lnkSignUp.click();
    return new SignUpPage(driver);
  }

  /**
   * Interacts with the Hudl logo.
   * 
   * @return an instance of the {@link HudlHomePage}.
   */
  public HudlHomePage clickHudlLogo() {
    wait.until(ExpectedConditions.visibilityOf(imgHudl));
    imgHudl.click();
    return new HudlHomePage(driver);
  }

  /**
   * Interacts with the back to Hudl link.
   * 
   * @return an instance of the {@link HomePage}.
   */
  public HomePage clickBackToHudl() {
    wait.until(ExpectedConditions.visibilityOf(btnBackToHudl()));
    btnBackToHudl().click();
    return new HomePage(driver);
  }

}
