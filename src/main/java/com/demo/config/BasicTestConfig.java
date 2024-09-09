package com.demo.config;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.properties.FilePaths;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.demo.config.ConsoleRunner.xmlFile;
import static com.demo.config.ReporterConfig.*;
import static com.demo.utilities.FileUtility.*;

import static org.apache.commons.io.FileUtils.cleanDirectory;


/**
 *                          This class contains all methods for taking screenshots,
 *                          browser initialization and generateReport generation.
 *          List:
 *   [1]    takeScreenshot  Capture screenshot and save the file with PNG extension.
 *                          Example:            takeScreenshot(driver, "FileName");
 *   [2]    browserConfig           Launch web browser. Value must be setted in testng.xml
 *   [3]    generateReport          Describes the result of a scripts and print result values.
 *   [4]    finishReport    Writes scripts information from the started reporters to
 *                          their output view.
 *   [5]    tearDown        Stop web driver and close the browser.
 */


public class BasicTestConfig {

    public static WebDriver     driver;
    public static WebDriverWait wait;
    public static File          screenshotFile;


    static final Logger LOG = LogManager.getLogger(BasicTestConfig.class);

    /**
     * Used for screenshot generating
     * @param driver, name
     * @throws Exception
     */
    public File takeScreenshot (WebDriver driver, String imageName) throws IOException {
        TakesScreenshot takesScreenshot = ((TakesScreenshot)driver);
        File imageFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(FilePaths.screenshots_actual_folder + imageName + ".png");
        FileUtils.copyFile(imageFile, destination);
        return destination;
    }


    private String getScreenShot(WebDriver driver) throws IOException {
        String dateName = new SimpleDateFormat("MM-dd-hh_mm_ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = FilePaths.screenshots_failed_folder + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }




        /**
        * Used for browser configuration
        * @param browser
        * @throws Exception
        */

        @Parameters("browser")
        @BeforeSuite
        public static void browserConfig(String browser) throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();

        if (browser.equalsIgnoreCase("chrome")) {
            // Install Chrome
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("scripts-type");
            options.addArguments("start-maximized");
            options.addArguments("--disable-search-geolocation-disclosure");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--incognito");

            driver = new ChromeDriver();
            LOG.info("| Chrome browser launched successfully |");

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
            LOG.info("| Firefox browser launched successfully |");
        }
    }
    
    /**
     * Delete all previous executed XML files
     */
        @AfterSuite
        public void clearXmlFiles() {
            extent.flush();
            driver.close();
            driver.quit();
        }

}

