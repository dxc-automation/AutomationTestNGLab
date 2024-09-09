package com.demo.properties;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePaths {

    //  Get project main dir
    public static Path getRootDir() {
        Path root = Paths.get(new File(System.getProperty("user.dir")).getParent());
        return root;
    }






    //  * * * *    F I L E   P A T H S
    public final static String report_json_folder          = getRootDir() + "/report/JSON/";
    public final static String report_folder               = getRootDir() + "/report/";
    public final static String report_html_file            = getRootDir() + "/report/TestReport.html";
    public final static String report_config_xml_file      = getRootDir() + "/src/main/resources/extent-config.xml";
    public final static String xml_files_folder            = getRootDir() + "/src/main/resources/xml_files/";

    public final static String screenshots_failed_folder   = getRootDir() + "/report/Screenshots/Failed/";
    public final static String screenshots_actual_folder   = getRootDir() + "/report/Screenshots/Actual/";
    public final static String screenshots_buffer_folder   = getRootDir() + "/report/Screenshots/Buffer/";
    public final static String screenshots_expected_folder = getRootDir() + "/report/Screenshots/Expected/";
    public final static String video_files                 = getRootDir() + "/report/video/";

    public final static String config_properties_file      = getRootDir() + "/src/main/resources/config.properties";
    public final static String chrome_driver_file          = getRootDir() + "/src/main/resources/drivers/chromedriver.exe";
    public final static String firefox_driver_file         = getRootDir() + "/src/main/resources/drivers/geckodriver.exe";

    public final static String cookies_file                = getRootDir() + "/report/cookies/";


}


