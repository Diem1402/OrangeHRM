package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;
import pageUIs.ReportPageUI;

public class ReportPageObject extends BasePage {
    private WebDriver driver;
    public ReportPageObject(WebDriver driver)  { this.driver = driver;
}
    public String getRowTextBySubordinateName(WebDriver driver, String employeeName) {
        waitForElementVisible(driver, ReportPageUI.ROW_DATA_BY_SUBORDINATE_NAME, employeeName);
        return getTextElement(driver, ReportPageUI.ROW_DATA_BY_SUBORDINATE_NAME, employeeName);
    }

    public boolean isSubordinateInfoDisplayed(WebDriver driver, String employeeName, String reportingMethod) {
        // 1. Lấy text cả dòng bên trong Page Object
        String rowText = getRowTextBySubordinateName(driver, employeeName);

        // 2. Kiểm tra chứa cả Name và Reporting Method
        return rowText.contains(employeeName) && rowText.contains(reportingMethod);
    }
}
