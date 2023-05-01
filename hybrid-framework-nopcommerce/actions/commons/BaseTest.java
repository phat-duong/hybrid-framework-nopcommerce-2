package commons;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;

public class BaseTest {
    private WebDriver driver;

    //Init Log
    protected final Log log;

    // nếu xài webdrivermanager thì k cần xài biến projectPath nữa
    private String projectPath = System.getProperty("user.dir");

    //Level 4: multiple browser
//    protected WebDriver getBrowserDriver(String browserName){
//        if(browserName.equals("chrome")){
//            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//            driver = new ChromeDriver();
//        } else if (browserName.equals("firefox")) {
//            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//            driver = new FirefoxDriver();
//        } else {
//            throw new RuntimeException("Browser name is invalid");
//        }
//        return driver;
//    }

    //Headless browser
    protected WebDriver getBrowserDriverAndHeadless(String browserName){
        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("h_chrome")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("windows-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equals("h_firefox")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

            //Browser Option: Selenium 3.xx
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("windows-size=1920x1080");
            driver = new FirefoxDriver(options);
        } else {
            throw new RuntimeException("Browser name is invalid");
        }
        return driver;
    }

    //Constructor for Log
    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    // Xài WebDriverManager để tự động update bản browser driver mới nhất về
    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        if(browserList == BrowserList.FIREFOX) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(browserList == BrowserList.H_FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("windows-size=1920x1080");
            driver = new FirefoxDriver(options);
        }else if(browserList == BrowserList.CHROME) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(browserList == BrowserList.H_CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("windows-size=1920x1080");
            driver = new ChromeDriver(options);
        }else if(browserList == BrowserList.EDGE){
//			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserList == BrowserList.COCCOC) {
            //COCCOC trừ đi 5 - 6 version ra chromedriver
            WebDriverManager.chromedriver().driverVersion("93.0.4577.63").setup();
            ChromeOptions options = new ChromeOptions();
            if(GlobalConstants.OS_NAME.startsWith("Window")){
                options.setBinary("C:\\Program Files (x86)\\Coccoc\\Browser\\Application\\browser.exe");
            }else {
                options.setBinary("...");
            }
            driver = new ChromeDriver(options);
        } else {
            throw new RuntimeException("Browser name is invalid");
        }

        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.get(GlobalConstants.PORTAL_PAGE_URL);

        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String pageUrl) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        if(browserList == BrowserList.FIREFOX) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(browserList == BrowserList.H_FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("windows-size=1920x1080");
            driver = new FirefoxDriver(options);
        }else if(browserList == BrowserList.CHROME) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(browserList == BrowserList.H_CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("windows-size=1920x1080");
            driver = new ChromeDriver(options);
        }else if(browserList == BrowserList.EDGE){
//			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }else if (browserList == BrowserList.COCCOC) {
            //COCCOC trừ đi 5 - 6 version ra chromedriver
            WebDriverManager.chromedriver().driverVersion("93.0.4577.63").setup();
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files (x86)\\Coccoc\\Browser\\Application\\browser.exe");
            driver = new ChromeDriver(options);
        } else {
            throw new RuntimeException("Browser name is invalid");
        }

        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(pageUrl);

        return driver;
    }

    private String getEnvironmentUrl (String environmentName) {
        String url = null;
        switch (environmentName) {
            case "DEV":
                url = GlobalConstants.PORTAL_PAGE_URL;
                break;
            case "STAGING":
                url = GlobalConstants.STG_PORTAL_PAGE_URL;
                break;
            case "PRODUCTION":
                url = GlobalConstants.PROD_PORTAL_PAGE_URL;
                break;
            default:
                System.out.println("Server name is not valid");
                break;
        }
        return url;
    }

    public WebDriver getDriverInstance() {
        return this.driver;
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
//            System.out.println("-------------------------- PASSED -------------------------- ");
            log.info("-------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
//            System.out.println("-------------------------- FAILED -------------------------- ");
            log.info("-------------------------- FAILED -------------------------- ");
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
//            System.out.println("-------------------------- PASSED -------------------------- ");
            log.info("-------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
//            System.out.println("-------------------------- FAILED -------------------------- ");
            log.info("-------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
//            System.out.println("-------------------------- PASSED -------------------------- ");
            log.info("-------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
//            System.out.println("-------------------------- FAILED -------------------------- ");
            log.info("-------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

}