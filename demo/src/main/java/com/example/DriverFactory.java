package com.example;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

  public DriverFactory() {
    WebDriverManager.chromedriver().setup();
    WebDriverManager.edgedriver().setup();
  }

  /**
   * Returns a driver of the specified type.
   * 
   * @param type the type of driver wanted.
   * @return an instance of a {@link WebDriver} for the type specified.
   */
  public WebDriver getDriver(String type) {
    switch (type) {
      case "edge":
        WebDriver eDriver = new EdgeDriver();
        eDriver.manage().window().maximize();
        eDriver.manage().timeouts().setScriptTimeout(30000, TimeUnit.MILLISECONDS);
        return eDriver;
      case "chrome":
        ChromeOptions options = new ChromeOptions();
        String localTemp = String.format("%s/target/generated-test-sources/", System.getProperty("user.dir"));
        options.addArguments(String.format("user-data-dir=%s", localTemp));
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver cDriver = new ChromeDriver(options);
        // cDriver.manage().window().maximize();
        cDriver.manage().timeouts().setScriptTimeout(30000, TimeUnit.MILLISECONDS);
        return cDriver;
      default:
        break;
    }
    return null;
  }

}
