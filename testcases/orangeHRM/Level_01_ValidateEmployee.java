package orangeHRM;

import commons.BaseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.*;

public class Level_01_ValidateEmployee extends BaseTest {
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
        // PHẦN 1: VALIDATION KHI TẠO MỚI EMPLOYEE (NEGATIVE CASES)
        // =========================================================================

        @Test
        public void Employee_01_Add_Employee_Empty_Required_Fields() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToButtonByTextOrange(driver, "Add");
            addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
            verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

            addEmployeePage.clickToButtonByTextOrange(driver, "Save");
            verifyEquals(addEmployeePage.getErrorMessageAtTextbox(driver), "Required");
        }

        @Test
        public void Employee_02_Add_Employee_Duplicate_ID() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToButtonByTextOrange(driver, "Add");
            addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
            verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

            addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", "0057");
            verifyEquals(addEmployeePage.getErrorMessageAtTextbox(driver), "Employee Id already exists");
        }

        @Test
        public void Employee_03_Add_Employee_Duplicate_Username() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToButtonByTextOrange(driver, "Add");
            addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
            verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

            addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
            addEmployeePage.enterToTextboxByLabel(driver, "Username", "abc d d");
            verifyEquals(addEmployeePage.getErrorMessageAtTextbox(driver), "Username already exists");
        }

        @Test
        public void Employee_04_Add_Employee_Weak_Password() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToButtonByTextOrange(driver, "Add");
            addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
            verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

            addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
            addEmployeePage.enterToTextboxByLabel(driver, "Username", "abc d");
            addEmployeePage.enterToTextboxByLabel(driver, "Password", "123");
            verifyEquals(addEmployeePage.getErrorMessageAtTextbox(driver), "Should have at least 8 characters");
        }

        @Test
        public void Employee_05_Add_Employee_Password_Mismatch() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToButtonByTextOrange(driver, "Add");
            addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
            verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

            addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
            addEmployeePage.enterToTextboxByLabel(driver, "Username", "abc d");
            addEmployeePage.enterToTextboxByLabel(driver, "Password", "Automation123@");
            addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", "Automation124@");
            verifyEquals(addEmployeePage.getErrorMessageAtTextbox(driver), "Passwords do not match");
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

