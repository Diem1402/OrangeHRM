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

public class Level_06_SearchEmployee extends BaseTest {
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
        // PHẦN 5: CHỨC NĂNG TÌM KIẾM (SEARCH & FILTERS)
        // =========================================================================

        @Test
        public void Employee_38_Search_By_Name() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
            employeeListPage.selectItemInAutocompleteByLabel(driver, "Employee Name", "Diem", "Diem Nguyen");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
            verifyTrue(employeeListPage.isTableDisplayInfo("0057", "Diem", "Nguyen", "Developer", "Full-Time Contract", "HBS unit", ""));
        }

        @Test
        public void Employee_39_Search_By_Employee_Id() {
            employeeListPage.clickToButtonByTextOrange(driver, " Reset ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.enterToTextboxByLabel(driver, "Employee Name", "");
            employeeListPage.enterToTextboxByLabel(driver, "Employee Id", "0001");
            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            verifyTrue(employeeListPage.isTableDisplayInfo("0001", "Automation", "FC", "", "", "", ""));
        }

        @Test
        public void Employee_40_Search_By_Employee_Status() {
            employeeListPage.clickToButtonByTextOrange(driver, " Reset ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.selectDropdownByLabel(driver, "Employment Status", "Full-Time Contract");
            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isTableDisplayInfo("0057", "Diem", "Nguyen", "Developer", "Full-Time Contract", "HBS unit", ""));
        }

        @Test
        public void Employee_41_Search_By_Include() {
            employeeListPage.clickToButtonByTextOrange(driver, " Reset ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.selectDropdownByLabel(driver, "Include", "Past Employees Only");
            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isTableDisplayInfo("0031", "lululu", "lu (Past Employee)", "", "", "", ""));
        }

        @Test
        public void Employee_42_Search_By_Supervisor_Name() {
            employeeListPage.clickToButtonByTextOrange(driver, " Reset ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.selectItemInAutocompleteByLabel(driver, "Supervisor Name", "Diem", "Diem Nguyen");
            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            verifyTrue(employeeListPage.isTableDisplayInfo("00184", "abc d", "d", "Developer", "", "", "Diem Nguyen"));
        }

        @Test
        public void Employee_43_Search_By_Job_Title() {
            employeeListPage.clickToButtonByTextOrange(driver, " Reset ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.selectDropdownByLabel(driver, "Job Title", "Developer");
            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isTableDisplayInfo("0057", "Diem", "Nguyen", "Developer", "Full-Time Contract", "HBS unit", ""));
        }

        @Test
        public void Employee_44_Search_By_Sub_Unit() {
            employeeListPage.clickToButtonByTextOrange(driver, " Reset ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.selectDropdownByLabels(driver, "Sub Unit", "HBS unit");
            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
            verifyTrue(employeeListPage.isTableDisplayInfo("0057", "Diem", "Nguyen", "Developer", "Full-Time Contract", "HBS unit", ""));
        }

        @Test
        public void Employee_45_Search_By_Combination_Filters() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToButtonByTextOrange(driver, " Reset ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.selectDropdownByLabel(driver, "Job Title", "Developer");
            employeeListPage.selectDropdownByLabel(driver, "Employment Status", "Full-Time Contract");
            employeeListPage.selectDropdownByLabels(driver, "Sub Unit", "HBS unit");
            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            verifyTrue(employeeListPage.isTableDisplayInfo("0057", "Diem", "Nguyen", "Developer", "Full-Time Contract", "HBS unit", ""));
        }

        @Test
        public void Employee_46_Search_Not_Found_Record_Toast_Message() {
            employeeListPage.clickToButtonByTextOrange(driver, " Reset ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.enterToTextboxByLabel(driver, "Employee Id", "0032");
            employeeListPage.selectDropdownByLabel(driver, "Include", "Past Employees Only");

            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isToastMessageDisplayed(driver, "No Records Found"));
        }

        @Test
        public void Employee_47_Search_With_No_Records_Count() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.enterToTextboxByLabel(driver, "Employee Id", "0032");
            employeeListPage.selectDropdownByLabel(driver, "Include", "Past Employees Only");

            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isRecordCountDisplayed(driver, "No Records Found"));
        }

        @Test
        public void Employee_48_Search_With_Single_Records_Count() {
            employeeListPage.clickToButtonByTextOrange(driver, " Reset ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.enterToTextboxByLabel(driver, "Employee Id", "0031");
            employeeListPage.selectDropdownByLabel(driver, "Include", "Past Employees Only");

            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isRecordCountDisplayed(driver, "(1) Record Found"));
        }

        @Test
        public void Employee_49_Search_With_Multiple_Records_Count() {
            employeeListPage.clickToButtonByTextOrange(driver, " Reset ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.selectDropdownByLabel(driver, "Job Title", "Developer");
            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isRecordCountDisplayed(driver, "(3) Records Found"));
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

