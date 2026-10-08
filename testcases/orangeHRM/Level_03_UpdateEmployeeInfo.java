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

public class Level_03_UpdateEmployeeInfo extends BaseTest {
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
    private String employeeID, employeeID2, adminUsername, adminPassword, employeeFirstNamme, employeeLastName, workEmail,otherEmail;
    private String employeeUserName1,employeeUserName2, employeePassword,phoneNumber, username,driversLicense,editEmployeeId,editOtherId;

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browser) {
        driver = getBrowserDriver(appUrl, browser);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUsername = "diemautomation";
        adminPassword = "Auto222$$$";
        driversLicense = "DL" + generateRandomNumber();
        employeeFirstNamme = "Lyn";
        employeeLastName = "Fan";
        employeeUserName1 = "Lyn.Fan" + generateRandomNumber();
        workEmail = "Lyn.Fan" + generateRandomNumber() + "@gmail.com";
        otherEmail = "Lyn.Fan" + generateRandomNumber() + "@gmail.com";
        employeeUserName2 = "LeFan" + generateRandomNumber();
        employeePassword = "Auto111$$$";
        employeeID = String.valueOf(generateRandomNumber());
        employeeID2 = String.valueOf(generateRandomNumber());
        phoneNumber = String.valueOf(generateRandomPhoneNumber());
        editEmployeeId = String.valueOf(generateRandomNumber());
        editOtherId = String.valueOf(generateRandomNumber());

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
        public void Employee_09_EditPersonalDetails() {

            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToButtonByTextOrange(driver, "Add");
            addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
            verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
            addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstNamme);
            addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastName);
            addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", employeeID); // CAT

            addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
            addEmployeePage.enterToTextboxByLabel(driver, "Username", employeeUserName1); // CAT
            addEmployeePage.enterToTextboxByLabel(driver, "Password", employeePassword);
            addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", employeePassword);

            addEmployeePage.clickToButtonByTextOrange(driver, "Save");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

            verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
            personalDetailPage.sleepInSecond(2);

            personalDetailPage.enterToTextboxByName(driver, "firstName", "Joey");
            personalDetailPage.enterToTextboxByName(driver, "lastName", "Hex");
            personalDetailPage.enterToTextboxByName(driver, "middleName", "J");
            personalDetailPage.enterToTextboxByLabel(driver, "Employee Id", editEmployeeId); // CAT
            personalDetailPage.enterToTextboxByLabel(driver, "Other Id", editOtherId); // CAT
            personalDetailPage.enterToTextboxByLabel(driver, "Driver's License Number", driversLicense); // CAT
            personalDetailPage.enterToDropdownByLabel(driver, "License Expiry Date", "2026-02-02");
            personalDetailPage.selectDropdownByLabel(driver, "Nationality", "Afghan");
            personalDetailPage.selectDropdownByLabel(driver, "Marital Status", "Single");
            personalDetailPage.enterToDropdownByLabel(driver, "Date of Birth", "1999-02-10");
            personalDetailPage.clickToRadioButtonByLabel(driver, "Female");
            personalDetailPage.clickToButtonByTextOrange(driver, "Save");

            verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Updated"));
            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
            personalDetailPage.sleepInSecond(2);

            verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), "Joey");
            verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), "Hex");
            verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), editEmployeeId); // CAT
            verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Other Id"), editOtherId); // CAT
            verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Driver's License Number"), driversLicense); // CAT
            verifyEquals(personalDetailPage.getTextboxValueByLabelDate(driver, "License Expiry Date"), "2026-02-02");
            verifyEquals(personalDetailPage.getTextboxDropdownByLabel(driver, "Nationality"), "Afghan");
            verifyEquals(personalDetailPage.getTextboxDropdownByLabel(driver, "Marital Status"), "Single");
            verifyEquals(personalDetailPage.getTextboxValueByLabelDate(driver, "Date of Birth"), "1999-02-10");
            verifyTrue(personalDetailPage.isRadioIsSelected(driver, "Female"));
        }

        @Test
        public void Employee_10_UploadAvatar() throws InterruptedException {
            personalDetailPage.clickToProfileImage();
            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
            Dimension oldProfileImageSize = personalDetailPage.getProfileNaturalImageSize();
            // Invalid File Type
            personalDetailPage.uploadMultipleFiles(driver, "RichText.rtf");
            verifyEquals(personalDetailPage.getErrorMessageAtProfileImage(), "File type not allowed");

            // Maximum Size
            personalDetailPage.uploadMultipleFiles(driver, "1.5MB.jpg");
            verifyEquals(personalDetailPage.getErrorMessageAtProfileImage(), "Attachment Size Exceeded");

            personalDetailPage.uploadMultipleFiles(driver, "2MB.jpg");
            verifyEquals(personalDetailPage.getErrorMessageAtProfileImage(), "Attachment Size Exceeded");

            personalDetailPage.uploadMultipleFiles(driver, "4MB.jpg");
            verifyEquals(personalDetailPage.getErrorMessageAtProfileImage(), "Attachment Size Exceeded");

            // Maximun Dimension
            personalDetailPage.uploadMultipleFiles(driver, "Dimension.jpg");

            // Valid File Type
            personalDetailPage.uploadMultipleFiles(driver, "Cowboy.png");
//            personalDetailPage.uploadMultipleFiles(driver, "Panda.gif");

//            personalDetailPage.clickToButtonByTextOrange(driver, "Save");
//            verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Updated"));
//            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
//            personalDetailPage.sleepInSecond(2);
//
//            Dimension newProfileImageSize = personalDetailPage.getProfileNaturalImageSize();
//
//            // Verify not equals
//            verifyNotEquals(oldProfileImageSize, newProfileImageSize);
        }

        @Test
        public void Employee_11_ContactDetails() throws InterruptedException {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToButtonByTextOrange(driver, "Add");
            addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
            verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
            addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstNamme);
            addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastName);
            addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", employeeID2); // CAT

            addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
            addEmployeePage.enterToTextboxByLabel(driver, "Username", employeeUserName2); // CAT
            addEmployeePage.enterToTextboxByLabel(driver, "Password", employeePassword);
            addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", employeePassword);

            addEmployeePage.clickToButtonByTextOrange(driver, "Save");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

            verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
            personalDetailPage.sleepInSecond(2);

            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Contact Details");
            contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);
            contactDetailPage.enterToTextboxByLabel(driver, "Street 1", "13 Conline Row");
            Thread.sleep(2000);
            contactDetailPage.enterToTextboxByLabel(driver, "Street 2", "14 Lincon Row");
            Thread.sleep(2000);
            contactDetailPage.enterToTextboxByLabel(driver, "City", "New York");
            contactDetailPage.enterToTextboxByLabel(driver, "State/Province", "Colombia");
            contactDetailPage.enterToTextboxByLabel(driver, "Zip/Postal Code", "12345");
            contactDetailPage.enterToTextboxByLabel(driver, "Home", "0989787867");
            contactDetailPage.enterToTextboxByLabel(driver, "Mobile", "0989787869");
            contactDetailPage.enterToTextboxByLabel(driver, "Work", "02839998888");
            contactDetailPage.enterToTextboxByLabel(driver, "Work Email", workEmail);
            contactDetailPage.enterToTextboxByLabel(driver, "Other Email", otherEmail);
            contactDetailPage.selectDropdownByLabel(driver, "Country", "Afghan");
            contactDetailPage.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(contactDetailPage.isToastMessageDisplayed(driver, "Successfully Updated"));

            verifyTrue(contactDetailPage.isLoadingSpinnerDisappear(driver));
            loginPage = contactDetailPage.clickLogoutOnTopMenu(driver);

            // FIX //CAT: Label là "Username", truyền vào biến employeeUserName
            loginPage.enterToTextboxByLabel(driver, "Username", employeeUserName2);
            loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
            loginPage.clickToButtonByTextOrange(driver, "Login");
            dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

            verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
            dashboardPage.sleepInSecond(2);

            verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "My Info"));
            dashboardPage.clickToModuleByTextInMenuItem(driver, "My Info");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Contact Details");
            contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);

            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Street 1"), "13 Conline Row");
            Thread.sleep(2000);
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Street 2"), "14 Lincon Row");
            Thread.sleep(2000);
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "City"), "New York");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "State/Province"), "Colombia");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Zip/Postal Code"), "12345");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Home"), "0989787867");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Mobile"), "0989787869");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Work"), "02839998888");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Work Email"), workEmail);
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Other Email"), otherEmail);
            verifyEquals(contactDetailPage.getTextboxDropdownByLabel(driver, "Country"), "Afghan");

            // Cập nhật lại thông tin địa chỉ
            contactDetailPage.enterToTextboxByLabel(driver, "Street 1", "13 Mathew Row");
            contactDetailPage.enterToTextboxByLabel(driver, "Street 2", "14 Eli Row");
            contactDetailPage.clickToButtonByTextOrange(driver, "Save"); // Thêm Save để lưu thay đổi
            verifyTrue(contactDetailPage.isToastMessageDisplayed(driver, "Successfully Updated"));

            verifyTrue(contactDetailPage.isLoadingSpinnerDisappear(driver));
            loginPage = contactDetailPage.clickLogoutOnTopMenu(driver);

            // FIX //CAT: Label là "Username", truyền vào biến adminUsername
            loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
            loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
            loginPage.clickToButtonByTextOrange(driver, "Login");

            dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);
            verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));

            // BỔ SUNG: Kiểm tra chắc chắn menu "PIM" hiển thị trước khi click
            verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "PIM"));

            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.enterToTextboxByLabel(driver, "Employee Id", employeeID2);
            employeeListPage.clickToButtonByTextOrange(driver, "Search");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver)); // Chờ bảng load xong kết quả

            employeeListPage.clickToActionById(driver, employeeID2, "pencil");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Contact Details");
            contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);

            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Street 1"), "13 Mathew Row");
            Thread.sleep(2000);
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Street 2"), "14 Eli Row");
            Thread.sleep(2000);
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "City"), "New York");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "State/Province"), "Colombia");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Zip/Postal Code"), "12345");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Home"), "0989787867");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Mobile"), "0989787869");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Work"), "Worker");
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Work Email"), workEmail);
            verifyEquals(contactDetailPage.getTextboxValueByLabel(driver, "Other Email"), otherEmail);
            verifyEquals(contactDetailPage.getTextboxDropdownByLabel(driver, "Country"), "Afghan");
        }

        @Test
        public void Employee_12_Add_Emergency_Contact_Validation_Empty_Fields() {
            dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
            employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.enterToTextboxByLabel(driver, "Employee Id", employeeID2);
            employeeListPage.clickToButtonByTextOrange(driver, "Search");
            verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

            employeeListPage.clickToActionById(driver, employeeID2, "pencil");
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

            personalDetailPage.clickToModuleByTextInMenuNavigation(driver, "Emergency Contacts");
            emergencyContactsPageObject = PageGenerator.getPage(EmergencyContactsPageObject.class, driver);
            verifyTrue(emergencyContactsPageObject.isLoadingSpinnerDisappear(driver));

            emergencyContactsPageObject.clickToAddButtonByTextOrange(driver, "Assigned Emergency Contacts");
            emergencyContactsPageObject.clickToButtonByTextOrange(driver, "Save");

            verifyEquals(emergencyContactsPageObject.getErrorMessageAtTextbox(driver), "Required");
            verifyEquals(emergencyContactsPageObject.getErrorMessageAtTextbox(driver), "Required");
            verifyEquals(emergencyContactsPageObject.getErrorMessageAtTextbox(driver), "At least one phone number is required");
            emergencyContactsPageObject.clickToButtonByTextOrange(driver, "Cancel");
            verifyTrue(emergencyContactsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_13_EmergencyDetails() {
            emergencyContactsPageObject.clickToAddButtonByTextOrange(driver, "Assigned Emergency Contacts");

            emergencyContactsPageObject.enterToTextboxByLabel(driver, "Name", "Luat");
            emergencyContactsPageObject.enterToTextboxByLabel(driver, "Relationship", "Husband");
            emergencyContactsPageObject.enterToTextboxByLabel(driver, "Home Telephone", phoneNumber);
            emergencyContactsPageObject.enterToTextboxByLabel(driver, "Mobile", phoneNumber);
            emergencyContactsPageObject.enterToTextboxByLabel(driver, "Work Telephone", phoneNumber);
            emergencyContactsPageObject.clickToButtonByTextOrange(driver, "Save");

            verifyTrue(emergencyContactsPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(emergencyContactsPageObject.isLoadingSpinnerDisappear(driver));
            emergencyContactsPageObject.sleepInSecond(2);

            emergencyContactsPageObject.clickToAddButtonByTextOrange(driver, "Assigned Emergency Contacts");
            verifyTrue(emergencyContactsPageObject.isAllRowsDisabledExceptFirst(driver));
            emergencyContactsPageObject.clickToButtonByTextOrange(driver, "Cancel");
            verifyTrue(emergencyContactsPageObject.isLoadingSpinnerDisappear(driver));
            emergencyContactsPageObject.sleepInSecond(2);
            verifyTrue(emergencyContactsPageObject.areAllRowsEnabled(driver));
        }

        @Test
        public void Employee_14_Add_Dependent_Validation_Empty_Name() {
            emergencyContactsPageObject.clickToModuleByTextInMenuNavigation(driver, "Dependents");
            dependentsPageObject = PageGenerator.getPage(DependentsPageObject.class, driver);
            verifyTrue(dependentsPageObject.isLoadingSpinnerDisappear(driver));

            dependentsPageObject.clickToAddButtonByTextOrange(driver, "Assigned Dependents");
            dependentsPageObject.selectDropdownByLabel(driver, "Relationship", "Child");
            dependentsPageObject.clickToButtonByTextOrange(driver, "Save");

            verifyEquals(dependentsPageObject.getErrorMessageAtTextbox(driver), "Required");
            dependentsPageObject.clickToButtonByTextOrange(driver, "Cancel");
            verifyTrue(dependentsPageObject.isLoadingSpinnerDisappear(driver));
        }

        @Test
        public void Employee_15_Dependents()  {
            dependentsPageObject.clickToAddButtonByTextOrange(driver, "Assigned Dependents");
            dependentsPageObject.enterToTextboxByLabel(driver, "Name", "Henry");
            dependentsPageObject.selectDropdownByLabel(driver, "Relationship", "Child");
            dependentsPageObject.enterToDropdownByLabel(driver, "Date of Birth", "1999-02-10");
            dependentsPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(dependentsPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));

            verifyTrue(dependentsPageObject.isLoadingSpinnerDisappear(driver));
            dependentsPageObject.sleepInSecond(2);

            dependentsPageObject.clickToActionByName(driver, "Henry", "pencil");

            dependentsPageObject.enterToTextboxByLabel(driver, "Name", "John");
            dependentsPageObject.selectDropdownByLabel(driver, "Relationship", "Child");
            dependentsPageObject.enterToDropdownByLabel(driver, "Date of Birth", "1999-02-10");
            dependentsPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(dependentsPageObject.isToastMessageDisplayed(driver, "Successfully Updated"));

            verifyTrue(dependentsPageObject.isLoadingSpinnerDisappear(driver));
            dependentsPageObject.sleepInSecond(2);

            dependentsPageObject.clickToActionByName(driver, "John", "trash");
            verifyTrue(dependentsPageObject.isPopupTitleDisplayed(driver, "Are you Sure?"));
            dependentsPageObject.clickToButtonByTextOrange(driver, " Yes, Delete ");
            verifyTrue(dependentsPageObject.isToastMessageDisplayed(driver, "Successfully Deleted"));
        }

        @Test
        public void Employee_16_Immigration() {
            dependentsPageObject.clickToModuleByTextInMenuNavigation(driver, "Immigration");
            immigrationPageObject = PageGenerator.getPage(ImmigrationPageObject.class, driver);
            verifyTrue(immigrationPageObject.isLoadingSpinnerDisappear(driver));

            immigrationPageObject.clickToAddButtonByTextOrange(driver, "Assigned Immigration Records");
            immigrationPageObject.enterToTextboxByLabel(driver, "Number", "22");
            immigrationPageObject.enterToTextboxByLabel(driver, "Eligible Status", "Approved");
            immigrationPageObject.selectDropdownByLabel(driver, "Issued By", "Algeria");
            immigrationPageObject.enterToDropdownByLabel(driver, "Issued Date", "2025-02-10");
            immigrationPageObject.enterToDropdownByLabel(driver, "Expiry Date", "2027-02-10");
            immigrationPageObject.enterToDropdownByLabel(driver, "Eligible Review Date", "2027-02-10");
            immigrationPageObject.enterToTextAreaByLabel(driver, "Comments", "Original document held by employee");
            immigrationPageObject.clickToButtonByTextOrange(driver, "Save");
            verifyTrue(immigrationPageObject.isToastMessageDisplayed(driver, "Successfully Saved"));
            verifyTrue(immigrationPageObject.isLoadingSpinnerDisappear(driver));

            immigrationPageObject.clickToCheckboxByDocNumEx("Passport", "22", "2027-02-10");
            verifyTrue(immigrationPageObject.isDeleteButtonDisplayed(driver, "Delete Selected"));
            immigrationPageObject.clickToCheckboxByDocNumEx("Passport", "22", "2027-02-10");
            verifyTrue(immigrationPageObject.isNotDeleteButtonDisplayed(driver, "Delete Selected"));
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

