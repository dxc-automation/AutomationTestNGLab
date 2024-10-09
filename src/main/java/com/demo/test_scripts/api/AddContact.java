package com.demo.test_scripts.api;

import com.demo.config.HttpHelper;
import com.demo.properties.Constants;
import com.demo.properties.Endpoints;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;

public class AddContact {

    static final Logger LOG = LogManager.getLogger(AddContact.class);

    HttpHelper helper = new HttpHelper();


    private void report() throws Exception {
        String testName        = "<b>[POST] Account Login</b>";
        String testCategory    = "API";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected"              +
                "<br><br><b>*****   D E S C R I P T I O N   *****</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }




    public String userAddContact(String token) throws Exception {
        report();


        return token;
    }

}
