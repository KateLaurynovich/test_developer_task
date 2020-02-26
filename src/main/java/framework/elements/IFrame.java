package framework.elements;

import framework.browsers.BrowserActions;
import org.openqa.selenium.By;

public class IFrame extends BaseElement {
    public IFrame(By by, String name) {
        super(by, name);
    }

    public void switchToIFrame() {
        BrowserActions.switchToFrame(webElement);
    }
}
