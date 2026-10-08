package pageUIs;

import org.openqa.selenium.By;

public class BasePageUI {
    // OrangeHRM
    public static final String SPINNER_ICON = "Css=div.oxd-loading-spinner";
    public static final String TEXTBOX_BY_LABEL_ORANGE = "XPath=//label[text()=\"%s\"]/parent::div/following-sibling::div//input";
    public static final String TEXTAREA_BY_LABEL_ORANGE = "XPath=//label[text()='%s']/parent::div/following-sibling::div/textarea";
    public static final String TEXTBOX_BY_NAME_ORANGE = "XPath=//input[@name='%s']";
    public static final String BUTTON_BY_TEXT_ORANGE = "XPath=//button[contains(string(),'%s')]";
    public static final String BUTTON_BY_TEXT_IN_MAIN_TITLE = "XPath=//h6[text()='%s']/following-sibling::form//button[contains(string(),'%s')]";
    public static final String MODULE_BY_TEXT_IN_MENU_ITEM = "XPath=//span[text()='%s']/parent::a[contains(@class,'oxd-main-menu-item')]";
    public static final String MODULE_BY_TEXT_IN_MENU_NAVIGATION = "XPath=//div[@class='orangehrm-edit-employee-navigation']//div//a[text()='%s']";
    public static final String PARENT_DROPDOWN_BY_LABEL = "XPath=//label[text()='%s']/parent::div/following-sibling::div//i";
    public static final String DROPDOWN_BY_LABEL_DATE = "XPath=//label[text()='%s']/parent::div/following-sibling::div//input";
    public static final String DROPDOWN_TEXT_BY_LABEL_SELECT = "XPath=//label[text()='%s']/parent::div/following-sibling::div//input";
    public static final String DROPDOWN_TEXT_BY_LABEL_DATE = "XPath=//label[text()='%s']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text-input')]";
    public static final String CHILD_DROPDOWN_BY_LABEL = "XPath=//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span";

    // Locator để click mở Dropdown theo Label Name
    public static final String PARENT_DROPDOWN_BY_LABEL_Sub = "xpath=//label[text()='%s']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]";
    public static final String CHILD_DROPDOWN_OPTIONS_Sub = "xpath=//div[contains(@class,'oxd-select-dropdown')]//div[@role='option']//span";

    // Locator danh sách tất cả các Option trong menu vừa xổ ra
    public static final String CHILD_DROPDOWN_OPTIONS = "xpath=//div[contains(@class,'oxd-select-dropdown')]//div[@role='option']//span";

    public static final String TOAST_MESSAGE_BY_TEXT = "XPath=//p[contains(@class,'oxd-text--toast-message') and normalize-space()='%s']";
    public static final String ALERT_MESSAGE_BY_TEXT = "XPath=//p[contains(@class,'oxd-alert-content-text') and text()='%s']";
    public static final String RADIO_BUTTON_BY_LABEL = "XPath=//label[text()='%s']/input";
    public static final String CHECKBOX_BY_LABEL = "XPath=//p[text()='%s']/following-sibling::div//span";
    public static final String USER_DROPDOWN = "Css=p.oxd-userdropdown-name";
    public static final String LOGOUT_LINK = "XPath=//a[@class='oxd-userdropdown-link' and text()='Logout']";
    public static final String ERROR_MESSAGE = "Css=span.oxd-input-field-error-message";
    public static final String EDIT_PROFILE_IMAGE = "Css=div.orangehrm-edit-employee-image>img.employee-image";
    public static final String UPLOAD_FILE_TYPE = "css=input[type='file']";
    public static final String UPLOAD_FILE_TYPE_BY_LABEL = "xpath=//label[contains(text(),'%s')]/ancestor::div[contains(@class,'oxd-input-group')]//input[@type='file']";
    public static final String EDIT_REMOVE_ICON_BY_ID = "XPath=//div[text()='Id']/following::div[normalize-space()='%s']/ancestor::div[@class='oxd-table-card']//i[contains(@class,'%s')]";
    public static final String PAGINATION_NEXT_BUTTON = "XPath=//button[contains(@class,'oxd-pagination-page-item--previous-next')]/i[contains(@class,'bi-chevron-right')]/parent::button";
    public static final String CHECKBOX_RECORD_BY_ID = "XPath=//div[text()='%s']/ancestor::div[@class='oxd-table-card']//span[contains(@class,'oxd-checkbox-input')]";
    public static final String EDIT_REMOVE_ICON_BY_NAME = "XPath=//div[normalize-space()='%s']/ancestor::div[contains(@class,'oxd-table-card')]//i[contains(@class,'%s')]";
    public static final String TERMINATION_MESSAGE = "XPath=//h6[contains(.,'%s')]/following::p[1]";
    ;

    public static final String BUTTON_ADD_BY_TEXT = "xpath=//h6[text()='%s']/following-sibling::button[contains(.,'Add')]";

    // Locator lấy toàn bộ các dòng trong bảng
    public static final String ALL_ROW_CARDS = "xpath=//div[@class='oxd-table-body']/div[@class='oxd-table-card']";

    // Locator tương đối tìm trong từng dòng (dùng dấu chấm ở đầu)
    public static final String PENCIL_BUTTON_IN_ROW = "xpath=.//button[i[contains(@class,'bi-pencil')]]";
    public static final String TRASH_BUTTON_IN_ROW = "xpath=.//button[i[contains(@class,'bi-trash')]]";
    // Locator tổng quát tìm popup theo tiêu đề
    public static final String DYNAMIC_POPUP_TITLE = "xpath=//p[contains(@class,'oxd-text--card-title') and text()='%s']";
    public static final String SAVE_BUTTON = "xpath=//div[contains(@class, 'oxd-dialog-container-default')]//button[normalize-space()='Save']";
    public static final String CHECKBOX_BY_DOC_NUM_EXPIRY = "xpath=//div[@role='row'][.//div[text()='%s'] and .//div[text()='%s'] and .//div[text()='%s']]//span[contains(@class, 'oxd-checkbox-input')]";

    public static final String TABLE_DISPLAY_INFOs = "xpath=//div[text()='Id']/following::div[text()='%s']//following::div[text()='%s']//preceding::div[text()='First (& Middle) Name']//following::div[text()='Last Name']//following::div[text()='%s']//following::div[text()='%s']//preceding::div[text()='Job Title']//following::div[text()='Employment Status']//following::div[text()='%s']//following::div[text()='%s']//following::div[text()='%s']//preceding::div[text()='Supervisor']";
    public static final String TABLE_DISPLAY_INFO = "xpath=//div[contains(@class,'oxd-table-card')][\n" +
            "  .//div[normalize-space()='%s'] and \n" +
            "  .//div[normalize-space()='%s'] and \n" +
            "  .//div[normalize-space()='%s'] and \n" +
            "  .//div[normalize-space()='%s'] and \n" +
            "  .//div[normalize-space()='%s'] and \n" +
            "  .//div[normalize-space()='%s'] and \n" +
            "  .//div[normalize-space()='%s']\n" +
            "]";
    public static final String DYNAMIC_AUTOCOMPLETE_TEXTBOX_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//input";
//    public static final String AUTOCOMPLETE_OPTIONS = "xpath=//div[contains(@class,'oxd-autocomplete-dropdown')]//div[contains(@class,'oxd-autocomplete-option')]";
    // Locator trỏ chính xác vào các dòng option trong menu sổ xuống của OrangeHRM
    public static final String AUTOCOMPLETE_LISTBOX = "xpath=//div[@role='listbox']";
    public static final String AUTOCOMPLETE_OPTIONS = "xpath=//div[@role='listbox']//div[contains(@class,'oxd-autocomplete-option')]";
    // Locator tổng quát bắt theo text biến đổi
    public static final String DYNAMIC_RECORD_FOUND_TEXT = "xpath=//span[contains(@class,'oxd-text') and normalize-space()='%s']";
    public static final String DYNAMIC_SELECT_ALL_CHECKBOX_BY_SECTION= "xpath=//h6[text()='%s']/following::div[contains(@class,'oxd-table-header')]//span[contains(@class,'oxd-checkbox-input')]";
    public static final String DYNAMIC_SELECT_SINGLE_CHECKBOX_BY_SECTION_INDEX= "xpath=//h6[text()='%s']/following::i[contains(@class,'bi-trash')][%s]";
}