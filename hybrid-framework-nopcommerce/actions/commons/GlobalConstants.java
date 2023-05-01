package commons;

import java.io.File;

public class GlobalConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");

    //trỏ vào 1 thư mục mặc định của User
    //Window: Downloads
    public static final String UPLOAD_FILE = PROJECT_PATH + File.separator +"uploadFiles" + File.separator;
    public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator +"downloadFiles";
    public static final String BROWSER_LOG= PROJECT_PATH + File.separator +"browserLogs";
    public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator +"dragDropHTML5";
    public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator +"autoIT";
    public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator +"ReportNGImages" + File.separator;
    public static final String EXTENT_PATH = PROJECT_PATH + "/htmlExtent/";
    public static final String ALLURE_PATH = PROJECT_PATH + "/htmlAllure/";

    //Xử lý Authentication alert
    //Upload = autoIT
    //popup lên mới upload
    //headless browser
    //Jenkins service (file cài đặt) - Headless
    //Jenkins (.war) - Bật browser

    //Database account/ User/ Pass /Port
    public static final String DB_DEV_URL = "192.168.1.15.9:1234";
    public static final String DB_DEV_USER = "user";
    public static final String DB_DEV_PASSWORD = "1234";

    public static final long LONG_TIMEOUT = 30;
    public static final long SHORT_TIMEOUT = 5;
    public static final long RETRY_TEST_FAIL = 3;

    //DEV
    public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com";

    //STAGING
    public static final String STG_PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String STG_ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com";

    //PRODUCTION
    public static final String PROD_PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String PROD_ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com";
}
