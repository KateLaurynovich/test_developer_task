package application.pageObject.forms;

import framework.elements.Button;
import framework.elements.Input;
import framework.elements.Label;
import org.openqa.selenium.By;

public class Frame {

    private static final String SAVE_BTN_LOCATOR = "//button[contains(@class, 'btn-primary')]";
    private static final String PROJECT_INPUT_LOCATOR = "//input[@type = 'text']";
    private static final String SUCCESS_LOCATOR = "//div[contains(@class, 'alert-success')]";
    private static final String SUCCESS_MSG = "Project %s saved";

    public void clickSaveBtn() {
        new Button(By.xpath(SAVE_BTN_LOCATOR), "saveBtn").clickElement();
    }

    public void submitProjectInput(String data) {
        new Input(By.xpath(PROJECT_INPUT_LOCATOR), "projectInput").submitInput(data);
    }

    public boolean isSuccessMsg(String projectName) {
        return new Label(By.xpath(SUCCESS_LOCATOR), "successMsg").getText().equals(String.format(SUCCESS_MSG, projectName));
    }
}
