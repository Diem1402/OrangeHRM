package commons;

import java.io.File;

public class GlobalConstants {
    // System Infor
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    // App Infor User
    public static final String DEV_USER_URL="";

    // Wait Infor
    public static final int SHORT_TIMEOUT= 10;
    public static final int LONG_TIMEOUT= 30;

    // Download/ Upload file
    public static final String UPLOAD_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
    public static final String DOWNLOAD_PATH = PROJECT_PATH + File.separator + "downloadFiles" + File.separator;

    // Retry Case Failed
    public static final int RETRY_NUMBER = 3;

    // Browser Logs/ Extension
    public static final String BROWSER_LOG_PATH = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + File.separator + "browserExtensions" + File.separator;

    // HTML Report Folder
    public static final String REPORTNG_PATH = PROJECT_PATH + File.separator + "htmlReportNG" + File.separator;
    public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "htmlExtent" + File.separator;
    public static final String ALLURE_PATH = PROJECT_PATH + File.separator + "htmlAllure" + File.separator;

    // Data Test/ Environment
    public static final String DATA_TEST_PATH = PROJECT_PATH + File.separator + "dataTest" + File.separator;
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + File.separator + "environmentConfig" + File.separator;
    public static final String JIRA_SITE_URL = "https://automation-fc-2025.atlassian.net/";
    public static final String JIRA_USERNAME = "automationfc.com@gmail.com";
    public static final String JIRA_API_KEY = "ATATT3xFfGFOKCBJV2PJh9rkD4pzs9NP6hNvYEPC6AWYu252pwSt8rBR7BiXI069Xi7v0L1B2JV16...";
    public static final String JIRA_PROJECT_KEY = "FRAMEWORK";

    public static final String  ADMINUSERNAME = "diemautomation";
    public static final String  ADMINPASSWORD = "Auto222$$$";

}
