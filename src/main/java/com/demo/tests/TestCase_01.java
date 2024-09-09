package com.demo.tests;

import com.demo.config.BasicTestConfig;
import com.demo.scripts.api.Login;
import com.demo.utilities.VideoRecord;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import static com.demo.scripts.ui.UserLogin.secureLoginWeb;

public class TestCase_01 extends BasicTestConfig {
    private static VideoRecord videoReord = new VideoRecord();

    @Test(description = "WEB", enabled = false)
    public static void web_login() throws Exception {
        videoReord.startRecording();
        secureLoginWeb();
        videoReord.stopRecording();
    }

    @Test(description = "API", enabled = true)
    public static void post_login() throws Exception {
        Login.login("sandboxqa11@gmail.com", "automation");
    }
}
