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

public class Level_02_AddNewEmployee extends BaseTest {
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
        // PHẦN 2: TẠO MỚI EMPLOYEE & KIỂM TRA ĐĂNG NHẬP
        // =========================================================================

        @Test
        public void Employee_06_NewEmployee_Enabled() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToButtonByTextOrange(driver, "Add");
            addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
            verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
            addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstNamme);
            addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastName);
            addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", employeeID);

            addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
            addEmployeePage.enterToTextboxByLabel(driver, "Username", employeeUserName);
            addEmployeePage.enterToTextboxByLabel(driver, "Password", employeePassword);
            addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", employeePassword);

            addEmployeePage.clickToButtonByTextOrange(driver, "Save");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

            verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
            personalDetailPage.sleepInSecond(2);

            verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstNamme);
            verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeLastName);
            verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

            // Logout
            loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);

            // Login bằng quyền Employee vừa tạo ra
            loginPage.enterToTextboxByLabel(driver, "Username", employeeUserName);
            loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
            loginPage.clickToButtonByTextOrange(driver, "Login");
            dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

            verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
            dashboardPage.sleepInSecond(2);

            verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "My Info"));
            dashboardPage.clickToModuleByTextInMenuItem(driver, "My Info");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

            verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstNamme);
            verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeLastName);
            verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

            // Logout Employee
            loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);

            // Login lại bằng Admin
            loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
            loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
            loginPage.clickToButtonByTextOrange(driver, "Login");
            dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

            verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
            dashboardPage.sleepInSecond(2);
        }

        @Test
        public void Employee_07_NewEmployee_Disabled() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
            employeeListPage.clickToButtonByTextOrange(driver, "Add");
            addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
            verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
            addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstNamme);
            addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastName);
            addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", "1325"); // Change new after test
            addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
            addEmployeePage.enterToTextboxByLabel(driver, "Username", "VietHuong2"); // Change new after test
            addEmployeePage.clickToRadioButtonByLabel(driver, "Disabled");
            addEmployeePage.enterToTextboxByLabel(driver, "Password", employeePassword);
            addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", employeePassword);
            addEmployeePage.clickToButtonByTextOrange(driver, "Save");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

            verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
            personalDetailPage.sleepInSecond(2);

            verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstNamme);
            verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeLastName);
            verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), "1325"); // Change new after test

            loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);
            loginPage.enterToTextboxByLabel(driver, "Username", "VietHuong2"); // Change new after test
            loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
            loginPage.clickToButtonByTextOrange(driver, "Login");
            verifyTrue(loginPage.isAlertMessageDisplayed(driver, "Account disabled"));

            // Login back Admin
            loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
            loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
            loginPage.clickToButtonByTextOrange(driver, "Login");
            dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);
        }

        @Test
        public void Employee_08_NewEmployee_NoLoginInfo() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToButtonByTextOrange(driver, "Add");
            addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
            verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
            addEmployeePage.enterToTextboxByName(driver, "firstName", "Hohoang"); // Change new after test
            addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastName);
            addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", "67665"); // Change new after test

            addEmployeePage.clickToButtonByTextOrange(driver, "Save");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

            verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));
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

