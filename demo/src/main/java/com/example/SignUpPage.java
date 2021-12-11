package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignUpPage extends LayoutPage {

  public SignUpPage(WebDriver driver) {
    super(driver);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa-id='login']")));
  }

  /** Would be expanded on if testing fully */

}
