package application.pageObject.forms;

import framework.browsers.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestField {
    private static final String TABLE_LOCATOR = "//table[@class = 'table']//tbody/tr/td/..";
    private static final String DESCRIPTION_LOCATOR = "descendant::td[%d]";
    private static final String TITLES = "//tr/th";

    private List<WebElement> getListTests() {
        return BrowserActions.getBrowser().findElements(By.xpath(TABLE_LOCATOR));
    }

    private List<WebElement> getListTitles() {
        return BrowserActions.getBrowser().findElements(By.xpath(TITLES));
    }

    public ArrayList<HashMap> getTests() {
        ArrayList<HashMap> tests = new ArrayList<>();
        for (WebElement element : getListTests()) {
            HashMap<String, String> test = new HashMap<>();

            for (WebElement title: getListTitles()) {
                String text = element.findElement(By.xpath(String.format(DESCRIPTION_LOCATOR, getListTitles().indexOf(title)+1))).getText();
                test.put(title.getText(), text);
            }
            tests.add(test);
        }
        return tests;
    }
}
