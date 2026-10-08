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

public class Level_05_EmployeeQualifications extends BaseTest {
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
        // PHẦN 4: SUB-MODULE QUALIFICATIONS (ADD, VALIDATE, DELETE)
        // =========================================================================

        @Test
        public void Employee_25_Qualification_Add_Work_Experience() { //
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToActionById(driver, "00184", "pencil");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Qualifications");
            qualificationsPageObject = PageGenerator.getPage(QualificationsPageObject.class, driver);
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));

            qualificationsPageObject.clickToAddButtonByTextOrange(driver, "Work Experience");
            qualificationsPageObject.enterToTextboxByLabel(driver, "Company", "Ave");
            qualificationsPageObject.enterToTextboxByLabel(driver, "Job Title", "QC");
            qualificationsPageObject.enterToDropdownByLabel(driver, "From", "2024-02-10");
            qualificationsPageObject.enterToDropdownByLabel(driver, "To", "2025-02-10");
            qualificationsPageObject.enterToTextAreaByLabel(driver, "Comment", "abc xyz");
            qualificationsPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));

            qualificationsPageObject.clickToAddButtonByTextOrange(driver, "Education");
            qualificationsPageObject.enterToTextboxByLabel(driver, "Year", "Ave");
            verifyEquals(qualificationsPageObject.getErrorMessageAtTextbox(driver), "Should be a number");
            qualificationsPageObject.clickToButtonByTextOrange(driver, "Cancel");
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));

            qualificationsPageObject.clickToAddButtonByTextOrange(driver, "Work Experience");
            qualificationsPageObject.enterToTextboxByLabel(driver, "Company", "Ave");
            qualificationsPageObject.enterToTextboxByLabel(driver, "Job Title", "QC");
            qualificationsPageObject.enterToDropdownByLabel(driver, "From", "2024-02-10");
            qualificationsPageObject.enterToDropdownByLabel(driver, "To", "2025-02-10");
            qualificationsPageObject.enterToTextAreaByLabel(driver, "Comment", "abc xyz");
            qualificationsPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_26_Qualification_Work_Experience_Delete_Single_Record() { //
            qualificationsPageObject.selectSingleRecordsBySectionIndex(driver, "Work Experience", "1");
            verifyTrue(qualificationsPageObject.isPopupTitleDisplayed(driver, "Are you Sure?"));
            qualificationsPageObject.clickToButtonByTextOrange(driver, " Yes, Delete ");
            verifyTrue(qualificationsPageObject.isToastMessageDisplayed(driver, "Successfully Deleted"));
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_27_Qualifications_Add_Education() {
            qualificationsPageObject.clickToAddButtonByTextOrange(driver, "Education");
            qualificationsPageObject.selectDropdownByLabel(driver, "Level", "Professional Certificate");
            qualificationsPageObject.enterToTextboxByLabel(driver, "Institute", "Economic Institute");
            qualificationsPageObject.enterToTextboxByLabel(driver, "Major/Specialization", "Commercial Economic");
            qualificationsPageObject.enterToTextboxByLabel(driver, "Year", "4");
            qualificationsPageObject.enterToTextboxByLabel(driver, "GPA/Score", "3.8");
            qualificationsPageObject.enterToDropdownByLabel(driver, "Start Date", "2025-10-04");
            qualificationsPageObject.enterToDropdownByLabel(driver, "End Date", "2026-10-04");
            qualificationsPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(qualificationsPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_28_Qualifications_Add_Skill() {
            qualificationsPageObject.clickToAddButtonByTextOrange(driver, "Skills");
            qualificationsPageObject.selectDropdownByLabel(driver, "Skill", "Selenium");
            qualificationsPageObject.enterToTextboxByLabel(driver, "Years of Experience", "2");
            qualificationsPageObject.enterToTextAreaByLabel(driver, "Comments", "should be update");
            qualificationsPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(qualificationsPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_29_Qualifications_Add_Language() {
            qualificationsPageObject.clickToAddButtonByTextOrange(driver, "Languages");
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
            qualificationsPageObject.selectDropdownByLabel(driver, "Language", "English");
            qualificationsPageObject.selectDropdownByLabel(driver, "Fluency", "Speaking");
            qualificationsPageObject.selectDropdownByLabel(driver, "Competency", "Good");
            qualificationsPageObject.enterToTextAreaByLabel(driver, "Comments", "should be update");
            qualificationsPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(qualificationsPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_30_Qualifications_Add_License() {
            qualificationsPageObject.clickToAddButtonByTextOrange(driver, "License");
            qualificationsPageObject.selectDropdownByLabel(driver, "License Type", "ISTQB");
            qualificationsPageObject.enterToTextboxByLabel(driver, "License Number", "222-22");
            qualificationsPageObject.enterToDropdownByLabel(driver, "Issued Date", "2024-02-10");
            qualificationsPageObject.enterToDropdownByLabel(driver, "Expiry Date", "2024-03-10");
            qualificationsPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(qualificationsPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_31_Qualifications_Add_License_Same_Date() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToActionById(driver, "00184", "pencil");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Qualifications");
            qualificationsPageObject = PageGenerator.getPage(QualificationsPageObject.class, driver);
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));

            qualificationsPageObject.clickToAddButtonByTextOrange(driver, "License");
            qualificationsPageObject.selectDropdownByLabel(driver, "License Type", "Scrum Master");
            qualificationsPageObject.enterToTextboxByLabel(driver, "License Number", "222-22");
            qualificationsPageObject.enterToDropdownByLabel(driver, "Issued Date", "2026-10-04");
            qualificationsPageObject.enterToDropdownByLabel(driver, "Expiry Date", "2026-10-03");
            qualificationsPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyEquals(qualificationsPageObject.getErrorMessageAtTextbox(driver), "Expiry date should be after issued date");
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
            qualificationsPageObject.clickToButtonByTextOrange(driver, "Cancel");
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_32_Qualifications_Add_Attachment() {
            qualificationsPageObject.clickToAddButtonByTextOrange(driver, "Attachments");
            qualificationsPageObject.uploadSingleFileByLabel(driver, "Select File", "Test data 1.txt");
            qualificationsPageObject.enterToTextAreaByLabel(driver, "Comment", "Degree Certificate attachment");
            qualificationsPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(qualificationsPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_33_Qualification_Work_Experience_Delete_All_Records() {
            qualificationsPageObject.selectAllRecordsBySection(driver, "Work Experience");
            qualificationsPageObject.clickToButtonByTextOrange(driver, "Delete Selected");
            verifyTrue(qualificationsPageObject.isPopupTitleDisplayed(driver, "Are you Sure?"));
            qualificationsPageObject.clickToButtonByTextOrange(driver, " Yes, Delete ");
            verifyTrue(qualificationsPageObject.isToastMessageDisplayed(driver, "Successfully Deleted"));
        }

        @Test
        public void Employee_34_Qualifications_Delete_Skill() {
            qualificationsPageObject.clickToActionByName(driver, "Selenium", "trash");
            verifyTrue(qualificationsPageObject.isPopupTitleDisplayed(driver, "Are you Sure?"));
            qualificationsPageObject.clickToButtonByTextOrange(driver, " Yes, Delete ");
            verifyTrue(qualificationsPageObject.isToastMessageDisplayed(driver, "Successfully Deleted"));
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_35_Qualifications_Delete_Language() {
            qualificationsPageObject.clickToActionByName(driver, "English", "trash");
            verifyTrue(qualificationsPageObject.isPopupTitleDisplayed(driver, "Are you Sure?"));
            qualificationsPageObject.clickToButtonByTextOrange(driver, " Yes, Delete ");
            verifyTrue(qualificationsPageObject.isToastMessageDisplayed(driver, "Successfully Deleted"));
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_36_Qualifications_Delete_License() {
            qualificationsPageObject.clickToActionByName(driver, "ISTQB", "trash");
            verifyTrue(qualificationsPageObject.isPopupTitleDisplayed(driver, "Are you Sure?"));
            qualificationsPageObject.clickToButtonByTextOrange(driver, " Yes, Delete ");
            verifyTrue(qualificationsPageObject.isToastMessageDisplayed(driver, "Successfully Deleted"));
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_37_Qualifications_Delete_Attachment() {
            qualificationsPageObject.clickToActionByName(driver, "Test data 1.txt", "trash");
            verifyTrue(qualificationsPageObject.isPopupTitleDisplayed(driver, "Are you Sure?"));
            qualificationsPageObject.clickToButtonByTextOrange(driver, " Yes, Delete ");
            verifyTrue(qualificationsPageObject.isToastMessageDisplayed(driver, "Successfully Deleted"));
            verifyTrue(qualificationsPageObject.isLoadingSpinnerDisappear(driver));
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

