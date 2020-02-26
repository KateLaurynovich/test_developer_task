package framework.cookies;

import framework.browsers.BrowserActions;
import framework.logger.MyLogger;
import org.openqa.selenium.Cookie;

public class CookieManager {
    private static MyLogger LOGGER = new MyLogger();

    public static void addCookie(Cookie cookie) {
        LOGGER.info(String.format("add cookie - %s -> %s", cookie.getName(), cookie.getValue()));
        BrowserActions.getBrowser().manage().addCookie(cookie);
    }

    public static void deleteCookie(String cookie) {
        LOGGER.info(String.format("delete cookie - %s", cookie));
        BrowserActions.getBrowser().manage().deleteCookieNamed(cookie);
    }

    public static Cookie getCookie(String cookie) {
        LOGGER.info(String.format("get cookie - %s", cookie));
        return BrowserActions.getBrowser().manage().getCookieNamed(cookie);
    }
    public static void deleteAllCookie() {
        LOGGER.info("delete all cookie");
        BrowserActions.getBrowser().manage().deleteAllCookies();
    }
}
