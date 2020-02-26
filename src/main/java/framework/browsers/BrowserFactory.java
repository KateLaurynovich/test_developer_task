package framework.browsers;

import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

public class BrowserFactory {
    private static WebDriver webDriver;
    private static MyLogger LOGGER = new MyLogger();

    private static WebDriver getLocalBrowser() {
        switch (PropertiesManager.getProperties("browser")) {
            case ("chrome"):
                WebDriverManager.getInstance(CHROME).setup();
                webDriver = new ChromeDriver(chromeOptions());
                LOGGER.info("browser is CHROME");
                break;
            case ("firefox"):
                WebDriverManager.getInstance(FIREFOX).setup();
                webDriver = new FirefoxDriver(fireFoxOptions());
                LOGGER.info("browser is FIREFOX");
                break;
            default:
                LOGGER.info("driver should be Firefox or Chrome");
                throw new IllegalArgumentException("driver should be Firefox or Chrome");
        }
        return webDriver;
    }

    private static WebDriver getRemoteBrowser() {
        if (webDriver == null) {
            LOGGER.info("Initialization remote Driver");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            try {
                switch (PropertiesManager.getProperties("browser")) {
                    case "chrome":
                        capabilities.setBrowserName("chrome");
                        webDriver = new RemoteWebDriver(URI.create(PropertiesManager.getProperties("selenoidLink")).toURL(), capabilities);
                        LOGGER.info("Chrome is ready");
                        break;
                    case "firefox":
                        capabilities.setBrowserName("firefox");
                        webDriver = new RemoteWebDriver(URI.create(PropertiesManager.getProperties("selenoidLink")).toURL(), capabilities);
                        LOGGER.info("Firefox is ready");
                        break;
                    default:
                        LOGGER.info("Invalid browser, you have to choose FireFox or Chrome");
                        throw new IllegalArgumentException("driver should be Firefox or Chrome");
                }
            } catch (MalformedURLException e) {
                LOGGER.error("MalformedURLException", e);
            }
        }
        return webDriver;
    }

    private static ChromeOptions chromeOptions() {
        String pathForDownload = PropertiesManager.getProperties("path");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("intl.accept_languages", PropertiesManager.getProperties("locale"));
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "/" + pathForDownload);
        chromePrefs.put("safebrowsing.enabled", "false");
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        if (Boolean.valueOf(PropertiesManager.getProperties("headless"))) {
            options.addArguments("--headless");
        }
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriverManager.chromedriver().setup();
        return options;
    }

    private static FirefoxOptions fireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        String pathToDownload = PropertiesManager.getProperties("pathDownloads");
        options.addPreference("intl.accept_languages", PropertiesManager.getProperties("locale"));
        options.addPreference("browser.download.dir", System.getProperty("user.dir") + "/" + pathToDownload);
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream, application/x-debian-package");
        if (Boolean.valueOf(PropertiesManager.getProperties("headless"))) {
            options.addArguments("--headless");
        }
        System.setProperty("webdriver.gecko.driver", "src/main/resources/lib/geckodriver");
        WebDriverManager.firefoxdriver().setup();
        return options;
    }

    public static WebDriver getDriver() {
        switch (PropertiesManager.getProperties("remote")) {
            case "true":
                LOGGER.info("Remote driver is ready");
                return getRemoteBrowser();
            case "false":
                LOGGER.info("Local driver is ready");
                return getLocalBrowser();
            default:
                LOGGER.info("Input in config true or false");
                throw new IllegalArgumentException("driver should be Firefox or Chrome");
        }
    }
}
