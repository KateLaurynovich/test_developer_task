package framework.forms;

import framework.browsers.BrowserActions;
import framework.logger.MyLogger;
import framework.wait.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BaseForm {

    private static MyLogger LOGGER = new MyLogger();

    private String name;
    private By by;
    private WebElement webElement;

    public BaseForm(By by, String name) {
        this.name = name;
        this.by = by;
        LOGGER.info("Create element ", this.name);
        webElement = BrowserActions.getBrowser().findElement(this.by);
        Waiter.waitElementVisible(this.by);
    }

    public boolean isDisplayed() {
        LOGGER.info(name + " - element is displayed");
        return Boolean.valueOf(webElement.getAttribute("aria-hidden"));
    }

    public void clickElement() {
        LOGGER.info("Click on form ", name);
        Waiter.waitElementClickable(webElement);
        webElement.click();
    }
}
