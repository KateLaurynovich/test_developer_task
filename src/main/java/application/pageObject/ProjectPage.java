package application.pageObject;

import application.models.TestModel;
import application.pageObject.forms.TestField;
import framework.elements.Label;
import org.openqa.selenium.By;


public class ProjectPage {

    private static final String TEST_LOCATOR = "//a[@href = 'testInfo?testId=%s']";

    public TestField getTestField() {
        return new TestField();
    }

    public void clickTest(TestModel testModel) {
        new Label(By.xpath(String.format(TEST_LOCATOR, testModel.getId())), testModel.getTestName()).clickElement();
    }
}
