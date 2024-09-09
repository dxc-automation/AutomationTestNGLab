package com.demo.config;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.properties.FilePaths;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.demo.config.BasicTestConfig.driver;
import static com.demo.properties.FilePaths.report_json_folder;
import static com.demo.properties.FilePaths.screenshots_failed_folder;

public class TestNGListener extends ReporterConfig implements ITestListener {


    static final Logger LOG = LogManager.getLogger(BasicTestConfig.class);

    private String description;
    private String methodName;

    @Override
    public void onStart(ITestContext arg0) { System.out.println("\nSUITE STARTED: [ " + arg0.getName().toUpperCase() + " ]"); }


    @Override
    public void onTestStart(ITestResult arg0) {
        System.out.println("\nTEST STARTED: [ "+arg0.getName().toUpperCase() + " ]");
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        description = result.getMethod().getDescription();
        methodName = String.format("%s", result.getMethod().getRealClass().getSimpleName());

        LOG.log(Level.ALL, "| PASSED | " + description + "_" + methodName);

        if (description.equalsIgnoreCase("API")) {

            //  Print into HTML generateReport file
            test.pass("<pre>"
                    + "<br/>"
                    + "<center><b>* * * * * * * *    R E S P O N S E    * * * * * * * *</b></center>"
                    + "<br/>"
                    + "<br/>"
                    + "Response Code    : " + HttpHelper.getResponseCode()
                    + "<br/>"
                    + "Response Message : " + HttpHelper.getResponseMsg()
                    + "<br/>"
                    + "<br/>"
                    + HttpHelper.getResponseHeaders()
                    + "<br/>"
                    + "<br/>"
                    + "<br/>"
                    + HttpHelper.getResponseBody()
                    + "<br/>"
                    + "<br/>"
                    + "</pre>");

        } else if (description.equalsIgnoreCase("WEB")) {
            // TO DO
        }
    }


    @Override
    public void onTestFailure(ITestResult result) {
        description = result.getMethod().getDescription();
        methodName = String.format("%s", result.getMethod().getRealClass().getSimpleName());
        Throwable throwable = result.getThrowable();

        LOG.log(Level.ALL,"| FAILED | " + description + "_" + methodName);

        if (description.equalsIgnoreCase("API")) {

            // Print into HTML generateReport file
            test.fail("<pre>"
                    + "<br/>"
                    + "<center><b>* * * * * * * *    R E Q U E S T    * * * * * * * *</b></center>"
                    + "<br/>"
                    + "<center><b>* * * * * * * *    R E S P O N S E    * * * * * * * *</b></center>"
                    + "<br />"
                    + "<br />"
                    + "Response Code  : " + HttpHelper.getResponseCode()
                    + "<br />"
                    + "Error Message  : " + HttpHelper.getResponseMsg()
                    + "<br />"
                    + "<br />"
                    + HttpHelper.getResponseHeaders()
                    + "<br />"
                    + "<br />"
                    + "<br />"
                    + "<center><b>********  E X C E P T I O N  ********</b></center>"
                    + "<br />"
                    + throwable
                    + "<br />"
                    + "<br />"
                    + HttpHelper.getResponseBody()
                    + "<br />"
                    + "</pre>");

        } else if (description.equalsIgnoreCase("WEB")) {
            File fileFail;
            fileFail = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(fileFail, new File(FilePaths.screenshots_failed_folder + methodName + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                test.fail("<pre><b>FAILED ON SCREEN</b><br>", MediaEntityBuilder.createScreenCaptureFromPath(FilePaths.screenshots_failed_folder + methodName + ".png", "<br>" + throwable).build());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
            test.fail("<pre>" + throwable.toString() + "</pre>");
            System.out.println("\n" + throwable);
        }
    }


    @Override
    public void onTestSkipped(ITestResult arg0) {
        System.out.println("\nTEST SKIPPED: [ " +arg0.getName().toUpperCase() + " ]");
    }


    @Override
    public void onFinish(ITestContext arg0) {
        System.out.println("\nTEST FINISH: [ " +arg0.getName().toUpperCase() + " ]");
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }



}