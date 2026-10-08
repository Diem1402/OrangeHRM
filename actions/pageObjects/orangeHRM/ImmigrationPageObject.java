package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;


public class ImmigrationPageObject extends BasePage {
    private WebDriver driver;
    public ImmigrationPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public void clickToCheckboxByDocNumEx(String doc,String num,String expiry) {
        waitForElementVisible(driver, BasePageUI.CHECKBOX_BY_DOC_NUM_EXPIRY,doc ,num ,expiry);
        clickToElementByJS(driver, BasePageUI.CHECKBOX_BY_DOC_NUM_EXPIRY,doc ,num ,expiry);
    }

}
