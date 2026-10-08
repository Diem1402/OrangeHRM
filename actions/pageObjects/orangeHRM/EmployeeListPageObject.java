package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;
import pageUIs.EmployeeListPageUI;

public class EmployeeListPageObject extends BasePage {
    private WebDriver driver;
    public EmployeeListPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isTableDisplayInfo(String id, String firstName, String lastName,String jobTitle, String empolymentStatus, String subUnit, String supervisor) {
        waitForElementVisible(driver, BasePageUI.TABLE_DISPLAY_INFO, id, firstName, lastName,jobTitle, empolymentStatus, subUnit, supervisor );
        return isElementUndisplayed(driver, BasePageUI.TABLE_DISPLAY_INFO, id, firstName, lastName,jobTitle, empolymentStatus, subUnit, supervisor);
    }
}
