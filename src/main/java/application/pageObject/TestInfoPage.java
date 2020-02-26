package application.pageObject;

import application.models.ProjectModel;
import application.models.SessionModel;
import framework.elements.Attributes;
import framework.elements.Label;
import org.openqa.selenium.By;

public class TestInfoPage {
    private static final String INFO_LOCATOR = "//h4[contains(text(), '%s')]/../p";
    private static final String INFO_ID_LOCATOR = "//a[contains(text(), '%s')]";
    private static final String IMG_LOCATOR = "//a[@href]/img/..";

    public String getInfo(InfoCategory name) {
        return new Label(By.xpath(String.format(INFO_LOCATOR, name.getTitle())), name.getTitle()).getText();
    }

    public String getProjectId(ProjectModel projectModel) {
        return new Label(By.xpath(String.format(INFO_ID_LOCATOR, projectModel.getName())), projectModel.getName())
                .getAttribute(Attributes.HREF);
    }

    public String getSessionId(SessionModel session) {
        return new Label(By.xpath(String.format(INFO_ID_LOCATOR, session.getCreatedTime())), session.getCreatedTime())
                .getAttribute(Attributes.HREF);
    }

    public String getHrefImg() {
        return new Label(By.xpath(IMG_LOCATOR), "img")
                .getAttribute(Attributes.HREF);
    }
}
