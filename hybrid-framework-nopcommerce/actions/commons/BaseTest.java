package commons;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.logging.LogEntries;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    private WebDriver driver;

    //Init Log
    protected final Log log;

    @BeforeSuite
    public void initBeforeSuite(){
        deleteAllureReport();
    }

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

            // Disable Browser driver log in console
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + "\\browserLogs\\FirefoxLog.log");

            //Hande SSL Firefox
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true)
//            driver = new FirefoxDriver (capabilities);

//            // Add extension to Firefox
//            FirefoxProfile profile = new FirefoxProfile();
//            File translate = new File(GlobalConstants.PROJECT_PATH + "\\browserExtensions\\google_translate.xpi");
//            profile.addExtension (translate);
//            //Set Certificate cho Firefox
//            profile.setAcceptUntrustedCertificates(true);
//            profile.setAssumeUntrustedCertificateIssuer(false);
//            FirefoxOptions options = new FirefoxOptions();
//            options.setProfile(profile);
//            driver = new FirefoxDriver(options);

            //Set ngôn ngữ tiếng việt
//            FirefoxOptions options = new FirefoxOptions();
//            options.addPreference("intl.accept_languages","vi-vn, vi, en-us, en");
            //Auto download
//            options.addPreference("browser.download.folderList", 2);
//            options.addPreference("browser.download.dir", GlobalConstants.PROJECT_PATH + "\\downloadFiles");
//            options.addPreference("browser.download.useDownloadDir", true);
//            options.addPreference("browser.helperApps.neverAsk.saveToDisk", "multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,text/csv,image/png,image/jpeg, application/pdf, text/html, text/plain, application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel, application/octet-stream");
//            options.addPreference("pdfjs.disabled", true);
            //Mở tab ẩn danh
//            options.addArguments("-private");
//            driver = new FirefoxDriver(options);

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

            // Disable Browser driver log in console
            System.setProperty("webdriver.chrome.args", "--disable-logging");
            System.setProperty("webdriver.chrome.silentOutput", "true");

            // Add extension to Chrome
//            File file = new File(GlobalConstants.PROJECT_PATH + "\\browserExtensions\\google_translate.crx");
//            ChromeOptions options = new ChromeOptions();
//            options.addExtensions(file);
//            driver = new ChromeDriver(options);

            //Set ngôn ngữ tiếng việt
//            ChromeOptions options = new ChromeOptions();
            //Auto save password
//            Map<String, Object> prefs = new HashMap<String, Object>();
//            prefs.put("credentials_enable_service", false);
//            prefs.put("profile.password_manager_enabled", false);
//            options.setExperimentalOption("prefs", prefs);
            //Auto download
//            Map<String, Object> prefs = new HashMap<String, Object>();
//            prefs.put("profile.default_content_settings.popups", 0);
//            prefs.put("download.default_directory", GlobalConstants.PROJECT_PATH + "\\downloadFiles");
//            options.setExperimentalOption("prefs", prefs);
            //Infor bar
//            options.setExperimentalOption("useAutomationExtension", false);
//            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//            options.addArguments("--lang=vi");
            //Disable notification
//            options.addArguments("--disable-notifications");
            //Disable thông báo cho truy cập vị trí
//            options.addArguments("--disable-geolocation");
            //Mở tab ẩn danh
//            options.addArguments("--incognito");
//            driver = new ChromeDriver(options);

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
        } else if(browserList == BrowserList.SAFARI) {
//            WebDriverManager.safaridriver().setup(); //k cần thằng này, chỉ cần enable safaridriver ở máy Mac lên là dc
            driver = new SafariDriver();
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
        }else if(browserList == BrowserList.SAFARI) {
//            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        } else if (browserList == BrowserList.COCCOC) {
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

    public void deleteAllureReport() {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println(listOfFiles[i].getName());
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    protected void closeBrowserAndDriver() {
        String cmd = "";
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);
            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + osName);

            if (driverInstanceName.contains("chrome")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI\"IMAGENAME eq chromedriver*\"";
                } else {
                    cmd = "pkill chromedriver";
                }
            }else if (driverInstanceName.contains("internetexplorer")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI\"IMAGENAME eq IEDriverServer*\"";
            }
            } else if (driverInstanceName.contains("firefox")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI\"IMAGENAME eq geckodriver*\"";
                } else {
                    cmd = "pkill geckodriver";
                }
            } else if (driverInstanceName.contains("edge")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI\"IMAGENAME eq msedgedriver*\"";
                } else {
                    cmd = "pkill msedgedriver";
                }
            } else if (driverInstanceName.contains("opera")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI\"IMAGENAME eq operadriver*\"";
                } else {
                    cmd = "pkill operadriver";
                }
            } else if (driverInstanceName.contains("opera")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI\"IMAGENAME eq operadriver*\"";
                } else {
                    cmd = "pkill operadriver";
                }
            } else if (driverInstanceName.contains("safari")) {
                if (osName.contains("mac")) {
                    cmd = "pkill safaridriver";
                }
            }
            if (driver != null) {
                //IE
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Hàm capture tab console log trên web
    protected void showBrowserConsoleLogs (WebDriver driver) {
        if (driver.toString().contains("chrome") || driver.toString().contains("firefox")) {
            LogEntries logs = driver.manage().logs().get("browser");
            List<LogEntry> logList = logs.getAll();
            for (LogEntry logging: logList) {
                if (logging.getLevel().toString().toLowerCase().contains("error")) {
                    log.info("--------" + logging.getLevel().toString() + "------- \n" + logging.getMessage());
                }
            }
        }
    }

}