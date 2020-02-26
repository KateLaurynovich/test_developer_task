package framework.utils;

import framework.browsers.BrowserActions;
import org.openqa.selenium.JavascriptExecutor;

public class JsExecutor {

    private static JavascriptExecutor jsExecutor = (JavascriptExecutor) BrowserActions.getBrowser();

    public static void closePopUp(String methodName) {
        jsExecutor.executeScript(methodName);
    }
}
