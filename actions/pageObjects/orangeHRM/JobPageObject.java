package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class JobPageObject extends BasePage {
    private WebDriver driver;
    public JobPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
