package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends LayoutPage {

  public HomePage(WebDriver driver) {
    super(driver);
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa-id='webnav-globalnav-explore']")));
  }

  @FindBy(className = "hui-globaluseritem")
  private WebElement lblUser;

  @FindBy(css = "[data-qa-id='webnav-usermenu-logout']")
  private WebElement lnkLogout;

  /**
   * Logs the user out of the current page.
   * 
   * @return an instance of the {@link HudlHomePage}.
   */
  public HudlHomePage logout() {
    new Actions(driver).moveToElement(lblUser).moveToElement(lnkLogout).click().build().perform();
    return new HudlHomePage(driver);
  }

}
