package application.pageObject.forms;

import framework.elements.Label;
import framework.logger.MyLogger;
import framework.utils.RegEx;
import org.openqa.selenium.By;

public class Footer {
    private static final MyLogger LOGGER = new MyLogger();
    private static final String FOOTER_LOCATOR = "//footer";
    private static final String VERSION_PATTERN = "\\d";
    private static Label footerLbl = new Label(By.xpath(FOOTER_LOCATOR), "footer");

    public String getText() {
        return footerLbl.getText();
    }

    public String getVersion() {
        String version = RegEx.findMatcher(VERSION_PATTERN, getText());
        LOGGER.info("version = " + version);
        return version;
    }
}
