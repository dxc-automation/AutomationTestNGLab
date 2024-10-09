package com.demo.test_scripts.ui;

import com.demo.config.BasicTestConfig;
import com.demo.page_objects.LoginPage;
import com.demo.properties.Endpoints;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.utilities.ElementScreenshot.*;


public class UserLogin  extends BasicTestConfig {

    private LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);



    private void openLoginPage() {
        driver.get("https://" + Endpoints.host);
        String expectedTitle = "Contact List App";
        String actualTitle   = driver.getTitle();
        BasicTestConfig.check.assertEquals(actualTitle, expectedTitle, "Page title is incorrect");
    }


    public void secureLoginWeb(String email, String password) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        openLoginPage();
    }
}
