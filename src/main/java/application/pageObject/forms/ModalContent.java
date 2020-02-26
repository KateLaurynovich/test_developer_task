package application.pageObject.forms;

import framework.forms.BaseForm;
import framework.elements.IFrame;
import org.openqa.selenium.By;

public class ModalContent extends BaseForm {

    private static final String IFRAME_LOCATOR = "addProjectFrame";

    public ModalContent(By by, String name) {
        super(by, name);
    }

    public void switchToIFrame() {
        new IFrame(By.id(IFRAME_LOCATOR), "application.1_iFrame field").switchToIFrame();
    }

}
