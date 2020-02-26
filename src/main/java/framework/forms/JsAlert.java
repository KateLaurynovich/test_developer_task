package framework.forms;

import framework.browsers.BrowserActions;
import org.openqa.selenium.Alert;


public class JsAlert {
    private Alert alert;

    public JsAlert () {
        alert = BrowserActions.getBrowser().switchTo().alert();
    }

    public String getText() {
        return alert.getText();
    }

    public void accept() {
        alert.accept();
    }

    public void sendKeys(String text) {
        alert.sendKeys(text);
        alert.accept();
    }
}
