package com.example;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class TestBase {

  public WebDriver driver;
  public LoginPage login;

  @BeforeAll
  void setup() {
    // Cleans out the temporary directory used by chrome.
    try {
      FileUtils
          .cleanDirectory(new File(String.format("%s/target/generated-test-sources/", System.getProperty("user.dir"))));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    driver = new DriverFactory().getDriver(Constants.CHROME);
  }

  @BeforeEach
  void navigate() {
    driver.navigate().to(Constants.LOGIN_URL);
    login = new LoginPage(driver);
  }

  @AfterAll
  void cleanUp() {
    driver.close();
    driver.quit();
  }

}
