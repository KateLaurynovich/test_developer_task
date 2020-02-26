package application.pageObject;

import application.pageObject.forms.Footer;
import application.pageObject.forms.Frame;
import application.pageObject.forms.ModalContent;
import application.pageObject.listGroup.ListGroup;
import framework.utils.JsExecutor;
import framework.elements.Button;
import framework.utils.PropertiesManager;
import org.openqa.selenium.By;

public class MainPage {

    private static final String ADD_BTN_LOCATOR = "//button[contains(@class, 'btn-xs')]";
    private static final String MODAL_CONTENT_LOCATOR = "//div[@id = 'addProject']";

    public Footer getFooter() {
        return new Footer();
    }

    public ListGroup getListGroup() {
        return new ListGroup();
    }

    public void clickAddBtn() {
        new Button(By.xpath(ADD_BTN_LOCATOR), "addBtn").clickElement();
    }

    private ModalContent getModalContent() {
        return new ModalContent(By.xpath(MODAL_CONTENT_LOCATOR), "modalContent");
    }

    public void switchToIFrame() {
        getModalContent().switchToIFrame();
    }

    public boolean isModalContent() {
        return getModalContent().isDisplayed();
    }

    public Frame getFrame() {
        return new Frame();
    }

    public void closePopUp() {
        JsExecutor.closePopUp(PropertiesManager.getProperties("closePopUp"));
    }
}
