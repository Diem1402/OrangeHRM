package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;
    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }
}
