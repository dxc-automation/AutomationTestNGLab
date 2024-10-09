package com.demo.config;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.properties.Constants;
import com.demo.properties.FilePaths;
import com.demo.properties.TestData;
import com.demo.utilities.FileUtility;
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
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


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

    public static SoftAssert check = new SoftAssert();
    public static Constants constants = new Constants();
    public static TestData testData   = new TestData();
    public static WebDriver driver;

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


    @BeforeSuite
    public void getTestData() {
        testData.getTestData();
    }


    @BeforeTest
    @Parameters({"browser"})
    public void initWebBrowser(String browser) throws Exception {
        Drivers drivers = new Drivers();
        driver = drivers.browserConfig(browser);
    }


    /**
     * Delete all previous executed XML files
     */
    @AfterSuite
    public void clearXmlFiles() throws IOException {
        ReporterConfig.extent.flush();

        try {
            driver.close();
            driver.quit();
        } catch (Exception e) {
            System.out.println("Web browser was not closed");
        }

        String folder = FilePaths.report_folder;
        String file   = FilePaths.report_archive_folder + FileUtility.getDate() + ".zip";
        FileUtility.zip(folder, file);
        LOG.info("Report archive successfully created\nFile {}", file);

        FileUtils.forceMkdir(new File(FilePaths.report_folder));
        LOG.info("Old report folder successfully deleted");
    }

}

