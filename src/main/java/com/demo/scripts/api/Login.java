package com.demo.scripts.api;

import com.demo.config.BasicTestConfig;
import com.demo.config.HttpHelper;
import com.demo.properties.Constants;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.utilities.FileUtility.createLogFile;
import static com.demo.utilities.FileUtility.getFormattedJson;

public class Login {



    static final Logger LOG = LogManager.getLogger(Login.class);

    private static void report() throws Exception {
        String testName        = "<b>[POST] Account Login</b>";
        String testCategory    = "API";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected"              +
                "<br><br><b>*****   D E S C R I P T I O N   *****</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void login(String email, String password) throws Exception {
        report();

        HttpHelper.setUrl(Constants.host, Constants.user_login);

        HttpHelper.requestBody.put("email", email);
        HttpHelper.requestBody.put("password", password);

        StringEntity entity = new StringEntity(HttpHelper.requestBody.toJSONString());

        HttpHelper.client = HttpHelper.setHttpClient();

        HttpPost post = new HttpPost(HttpHelper.getUrl());
        post.setHeader("Content-Type", "application/json");
        post.setEntity(entity);

        HttpHelper.response = (CloseableHttpResponse) HttpHelper.client.execute(post);
        HttpHelper.getClosableHttpClientResponseDetails(HttpHelper.response);




        Assert.assertTrue(HttpHelper.getResponseCode() > 199 && HttpHelper.getResponseCode() < 300);
    }

}
