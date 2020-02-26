package application.pageObject.listGroup;

import framework.elements.Attributes;
import framework.elements.Button;
import framework.utils.RegEx;
import org.openqa.selenium.By;

public class ListGroup {

    private static final String CATEGORY_LOCATOR_PATTERN = "//div[@class = 'list-group']//a[text() = '%s']";
    private static final String CATEGORY_ID_PATTERN = "\\d{1,4}$";

    private Button getCategory(String category) {
        return new Button(By.xpath(String.format(CATEGORY_LOCATOR_PATTERN, category)), category);
    }

    public void clickCategory(String category) {
        getCategory(category).clickElement();
    }

    public boolean isCategory(String category) {
        return getCategory(category).isDisplayed();
    }

    public String getCategoryId(String category) {
        return RegEx.findMatcher(CATEGORY_ID_PATTERN, getCategory(category).getAttribute(Attributes.HREF));
    }
}
