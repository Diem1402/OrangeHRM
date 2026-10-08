package orangeHRM;

import commons.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.*;

public class Level_07_DeleteOperations extends BaseTest {
    WebDriver driver;
    private JavascriptExecutor jsExecutor;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private UserManagementPageObject userManagementPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private ContactDetailPageObject contactDetailPage;
    private EmergencyContactsPageObject emergencyContactsPageObject;
    private DependentsPageObject dependentsPageObject;
    private ImmigrationPageObject immigrationPageObject;
    private JobPageObject jobPageObject;
    private SalaryPageObject salaryPageObject;
    private ReportPageObject reportPageObject;
    private QualificationsPageObject qualificationsPageObject;
    private MembershipsPageObject membershipsPageObject;
    private String employeeID, adminUsername, adminPassword, employeeFirstNamme, employeeLastName;
    private String employeeUserName, employeePassword,phoneNumber;

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browser) {
        driver = getBrowserDriver(appUrl, browser);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUsername = "diemautomation";
        adminPassword = "Auto222$$$";
        employeeFirstNamme = "Hoàng";
        employeeLastName = "Lịch";
        employeeUserName = "Hoàng.Lịch" + generateRandomNumber();
        employeePassword = "Auto111$$$";
        employeeID = String.valueOf(generateRandomNumber());
        phoneNumber = String.valueOf(generateRandomPhoneNumber());

        loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByTextOrange(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Admin"));

    }

        // =========================================================================
        // PHẦN 6: DỌN DẸP DỮ LIỆU & XÓA EMPLOYEE (DELETE OPERATIONS)
        // =========================================================================

        @Test
        public void Employee_50_Delete_Single_Employee() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.enterToTextboxByLabel(driver, "Employee Id", "62712");
            employeeListPage.clickToButtonByTextOrange(driver, "Search");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToActionById(driver, "62712", "trash");
            verifyTrue(employeeListPage.isPopupTitleDisplayed(driver, "Are you Sure?"));
            employeeListPage.clickToButtonByTextOrange(driver, " Yes, Delete ");
            verifyTrue(employeeListPage.isToastMessageDisplayed(driver, "Successfully Deleted"));

            employeeListPage.clickToButtonByTextOrange(driver, " Reset ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.selectDropdownByLabel(driver, "Job Title", "Developer");
            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isRecordCountDisplayed(driver, "(3) Records Found"));
        }

        @Test
        public void Employee_51_Search_Delete_Multiple_Employees_Bulk() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToCheckboxByEmployeeIdWithPagination(driver, "001131146");
            employeeListPage.clickToCheckboxByEmployeeIdWithPagination(driver, "001584841");
            verifyTrue(employeeListPage.isDeleteButtonDisplayed(driver, "Delete Selected"));

            employeeListPage.clickToButtonByTextOrange(driver, "Delete Selected");
            verifyTrue(employeeListPage.isPopupTitleDisplayed(driver, "Are you Sure?"));
            employeeListPage.clickToButtonByTextOrange(driver, " Yes, Delete ");

            verifyTrue(employeeListPage.isToastMessageDisplayed(driver, "Successfully Deleted"));
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
        }


        @AfterClass
        public void afterClass() {
            closeBrowser();
        }

    }







// Hotkeys
// Ctrl D: copy
// Ctrl + shift + F: reformat code
// Alt + Enter: Import/remove thư viện
// Ctrl + shift + /: comment 1 đoạn dài
// Ctrl + space : suggest code
// Alt + shift + R: rename
// Ctrl + shift + X: duplicate code
// Alt + down/up : move code
// Ctrl + shift + A: gom code
// Alt + shift + A: mở rông code
//CRUD: CREATE/READ/UPDATE/DELETE

