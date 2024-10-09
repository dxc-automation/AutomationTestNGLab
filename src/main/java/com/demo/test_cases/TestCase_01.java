package com.demo.test_cases;

import com.demo.config.BasicTestConfig;
import com.demo.config.Drivers;
import com.demo.config.ReporterConfig;
import com.demo.properties.TestData;
import com.demo.test_scripts.api.Login;
import com.demo.test_scripts.ui.UserLogin;
import com.demo.utilities.VideoRecord;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.demo.config.Drivers.*;
import static com.demo.config.ReporterConfig.startTestReport;

public class TestCase_01 extends BasicTestConfig {

    private final VideoRecord videoReord = new VideoRecord();
    private String token;




    @Test(description = "WEB", enabled = true, priority = 0)
    public void web_login() throws Exception {
        String testName        = "<b>User login with valid credentials</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected."              +
                "<br><br><br>* * *  STEPS DESCRIPTION  * * *</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);

        videoReord.startRecording();

        UserLogin userLogin    = new UserLogin();
        userLogin.secureLoginWeb(testData.getEmail(), testData.getPassword());

        videoReord.stopRecording();
    }


    @Test(description = "API", enabled = true, priority = 1)
    public void post_login() throws Exception {
        String testName        = "<b>[POST] Account Login</b>";
        String testCategory    = "API";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected"              +
                "<br><br><b>*****   D E S C R I P T I O N   *****</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
        
        Login login = new Login();
        token = login.userLogin(testData.getEmail(), testData.getPassword());
    }

}
