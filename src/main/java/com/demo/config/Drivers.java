package com.demo.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class Drivers {

    private WebDriver driver;


    /**
     * Used for browser configuration
     * @param browser
     * @throws Exception
     */

    public WebDriver browserConfig(String browser) throws Exception {

        if (browser.equalsIgnoreCase("chrome")) {
            // Install Chrome
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");

            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            // Install Firefox
            WebDriverManager.firefoxdriver().setup();

            FirefoxProfile profile = new FirefoxProfile();
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(true);

            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(profile);
            options.setLogLevel(FirefoxDriverLogLevel.TRACE);

            driver = new FirefoxDriver(options);
        }
        Thread.sleep(5000);
        return driver;
    }
}
