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

public class Level_04_UpdateEmployeeInfo extends BaseTest {
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
        employeeFirstNamme = "Bay";
        employeeLastName = "By";
        employeeUserName = "Ba.By" + generateRandomNumber();
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
        // PHẦN 3: CẬP NHẬT THÔNG TIN CHI TIẾT EMPLOYEE & CÁC SUB-MODULES
        // =========================================================================

        @Test
        public void Employee_17_Job() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.enterToTextboxByLabel(driver, "Employee Id", "958398");
            employeeListPage.clickToButtonByTextOrange(driver, "Search");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToActionById(driver, "958398", "pencil");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Job");
            jobPageObject = PageGenerator.getPage(JobPageObject.class, driver);
            verifyTrue(jobPageObject.isLoadingSpinnerDisappear(driver));
            jobPageObject.clickToCheckboxByLabel(driver, "Include Employment Contract Details");
            jobPageObject.uploadSingleFileByLabel(driver, "Contract Details", "Test data 1.txt");
            verifyTrue(jobPageObject.isLoadingSpinnerDisappear(driver));

            jobPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(jobPageObject.isToastMessageDisplayed(driver, "Successfully Updated"));
            verifyTrue(jobPageObject.isLoadingSpinnerDisappear(driver));
            jobPageObject.sleepInSecond(2);

            jobPageObject.clickToButtonByTextOrange(driver, "Terminate Employment");
            verifyTrue(jobPageObject.isPopupTitleDisplayed(driver, "Terminate Employment"));
            jobPageObject.enterToDropdownByLabel(driver, "Termination Date", "2026-10-05");
            jobPageObject.selectDropdownByLabel(driver, "Termination Reason", "Laid-off");
            jobPageObject.clickToSaveButtonPopUp(driver, "Save");
            verifyTrue(jobPageObject.isLoadingSpinnerDisappear(driver));
            jobPageObject.sleepInSecond(2);

            verifyEquals(jobPageObject.getTerminateMessage(driver, "Employee Termination / Activiation"), "Terminated on: 2026-10-05");
        }

        @Test
        public void Employee_18_Job_Activate_Terminated_Employee() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.selectDropdownByLabel(driver, "Include", "Past Employees Only");
            employeeListPage.clickToButtonByTextOrange(driver, " Search ");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToActionById(driver, "958398", "pencil");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Job");
            jobPageObject = PageGenerator.getPage(JobPageObject.class, driver);
            verifyTrue(jobPageObject.isLoadingSpinnerDisappear(driver));

            jobPageObject.clickToButtonByTextOrange(driver, "Activate Employment");
            verifyTrue(jobPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_19_Salary() {
            jobPageObject.clickToModuleByTextInMenuNavigation(driver, "Salary");
            salaryPageObject = PageGenerator.getPage(SalaryPageObject.class, driver);
            verifyTrue(salaryPageObject.isLoadingSpinnerDisappear(driver));

            salaryPageObject.clickToAddButtonByTextOrange(driver, "Assigned Salary Components");
            salaryPageObject.enterToTextboxByLabel(driver, "Salary Component", "Basic Salary");
            salaryPageObject.selectDropdownByLabel(driver, "Pay Grade", "Junior");
            verifyTrue(salaryPageObject.isLoadingSpinnerDisappear(driver));
            salaryPageObject.selectDropdownByLabel(driver, "Pay Frequency", "Monthly");
            salaryPageObject.selectDropdownByLabel(driver, "Currency", "United States Dollar");
            salaryPageObject.enterToTextboxByLabel(driver, "Amount", "1600");
            salaryPageObject.enterToTextAreaByLabel(driver, "Comments", "abc");
            salaryPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(salaryPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(salaryPageObject.isLoadingSpinnerDisappear(driver));

            salaryPageObject.clickToAddButtonByTextOrange(driver, "Attachments");
            salaryPageObject.uploadSingleFileByLabel(driver, "Select File", "Test data 1.txt");
            salaryPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(salaryPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(salaryPageObject.isLoadingSpinnerDisappear(driver));
            salaryPageObject.sleepInSecond(3);

            salaryPageObject.clickToActionByName(driver, "Test data 1.txt", "download");
            salaryPageObject.sleepInSecond(3);
        }

        @Test
        public void Employee_20_Report() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToActionById(driver,"00184","pencil");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Report-to");
            reportPageObject=PageGenerator.getPage(ReportPageObject.class, driver);
            verifyTrue(reportPageObject.isLoadingSpinnerDisappear(driver));

            reportPageObject.clickToAddButtonByTextOrange(driver,"Assigned Supervisors");
            reportPageObject.selectItemInAutocompleteByLabel(driver,"Name","cho","cho cha");
            reportPageObject.selectDropdownByLabel(driver,"Reporting Method","Direct");
            reportPageObject.clickToButtonByTextOrange(driver,"Save");
            verifyTrue(reportPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(reportPageObject.isLoadingSpinnerDisappear(driver));

            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToActionById(driver,"0113","pencil");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Report-to");
            reportPageObject = PageGenerator.getPage(ReportPageObject.class, driver);
            verifyTrue(reportPageObject.isLoadingSpinnerDisappear(driver));
            verifyTrue(reportPageObject.isSubordinateInfoDisplayed(driver, "abc d", "Direct"));
        }

        @Test
        public void Employee_21_ReportTo_Assign_Subordinate() {
            reportPageObject.clickToAddButtonByTextOrange(driver, "Assigned Subordinates");
            reportPageObject.selectItemInAutocompleteByLabel(driver,"Name","Bay","Bay By");
            reportPageObject.sleepInSecond(1);
            reportPageObject.selectDropdownByLabel(driver, "Reporting Method", "Direct");
            reportPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(reportPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(reportPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_22_ReportTo_Delete_Supervisor() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToActionById(driver,"00184","pencil");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Report-to");
            reportPageObject=PageGenerator.getPage(ReportPageObject.class, driver);
            verifyTrue(reportPageObject.isLoadingSpinnerDisappear(driver));

            reportPageObject.clickToActionByName(driver, "cho cha", "trash");
            verifyTrue(reportPageObject.isPopupTitleDisplayed(driver, "Are you Sure?"));
            reportPageObject.clickToButtonByTextOrange(driver, " Yes, Delete ");

            verifyTrue(reportPageObject.isToastMessageDisplayed(driver, "Successfully Deleted"));
            verifyTrue(reportPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_23_Memberships_Add_Membership() {
            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Memberships");
            membershipsPageObject = PageGenerator.getPage(MembershipsPageObject.class, driver);
            verifyTrue(membershipsPageObject.isLoadingSpinnerDisappear(driver));

            membershipsPageObject.clickToAddButtonByTextOrange(driver, "Assigned Memberships");
            membershipsPageObject.selectDropdownByLabel(driver, "Membership", "IEEE");
            membershipsPageObject.selectDropdownByLabel(driver, "Subscription Paid By", "Company");
            membershipsPageObject.enterToTextboxByLabel(driver, "Subscription Amount", "500");
            membershipsPageObject.selectDropdownByLabel(driver, "Currency", "United States Dollar");
            membershipsPageObject.enterToDropdownByLabel(driver, "Subscription Commence Date", "2025-01-01");
            membershipsPageObject.enterToDropdownByLabel(driver, "Subscription Commence Date", "2026-02-01");
            membershipsPageObject.clickToButtonByTextOrange(driver, "Save");

            verifyTrue(membershipsPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(membershipsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_24_Memberships_Delete_Membership() {
            membershipsPageObject.clickToActionByName(driver, "IEEE", "trash");
            verifyTrue(membershipsPageObject.isPopupTitleDisplayed(driver, "Are you Sure?"));
            membershipsPageObject.clickToButtonByTextOrange(driver, " Yes, Delete ");
            verifyTrue(membershipsPageObject.isToastMessageDisplayed(driver, "Successfully Deleted"));
            verifyTrue(membershipsPageObject.isLoadingSpinnerDisappear(driver));
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

