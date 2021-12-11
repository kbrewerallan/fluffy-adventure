package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LayoutPage {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public LayoutPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    wait = new WebDriverWait(this.driver, 5);
  }

  /**
   * Returns the page title value.
   * 
   * @return the title string.
   */
  public String getTitle() {
    return driver.getTitle();
  }
}
