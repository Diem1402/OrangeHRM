package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserManagementPageObject extends BasePage {
    private WebDriver driver;
    public UserManagementPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
