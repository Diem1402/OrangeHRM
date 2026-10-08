package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.LoginPageObject;
import pageUIs.BasePageUI;
import pageUIs.EmergencyContactsPageUI;
import pageUIs.EmployeeListPageUI;


import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BasePage {

    public static BasePage getBasePage() {
        return new BasePage();
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public By getByXpath(String locator) {
        return By.xpath(locator);
    }

    protected WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    protected WebElement getWebElement(WebDriver driver, String locator, String... value) {
        return driver.findElement(getByLocator(castParameter(locator, value)));
    }

    public String castParameter(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    protected By getByLocator(String locatorType) {
        if (locatorType == null || locatorType.trim().isEmpty()) {
            throw new IllegalArgumentException("LocatorType cannot be null or empty");
        }

        String[] locatorArray = locatorType.trim().split("=", 2);
        String locatorPrefix = locatorArray[0].trim();
        String locatorValue = locatorArray[1].trim();


        switch (locatorPrefix.toUpperCase()) {
            case "XPATH":
                return By.xpath(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            case "ID":
                return By.id(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            case "CLASS":
                return By.className(locatorValue);
            default:
                throw new IllegalArgumentException("LocatorType not recognized" + locatorType);
        }
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restValue) {
        getElement(driver, castParameter(locator, restValue)).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(keyToSend);
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend, String... restValue) {
        getElement(driver, castParameter(locator, restValue)).clear();
        getElement(driver, castParameter(locator, restValue)).sendKeys(keyToSend);
    }

    public void sendKeyToElementForHRM(WebDriver driver, String locator, CharSequence keyToSend, String... restValue) {
        getWebElement(driver, castParameter(locator, restValue)).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        getWebElement(driver, castParameter(locator, restValue)).sendKeys(Keys.DELETE);
        getWebElement(driver, castParameter(locator, restValue)).sendKeys(keyToSend);
    }

    public void selectItemFromDropDown(WebDriver driver, String locator, String textItem) {
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    public void selectItemFromDropDown(WebDriver driver, String locator, String textItem, String... restValue) {
        new Select(getElement(driver, castParameter(locator, restValue))).selectByVisibleText(textItem);
    }

    public String getSelectedValueFromDropDown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator))
                .getFirstSelectedOption().getText();
    }

    public boolean isDropDownMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(2);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));

        sleepInSecond(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem, String... restValue) {
        getElement(driver, castParameter(parentLocator, restValue)).click();
        sleepInSecond(2);

        By childBy = getByLocator(castParameter(childItemLocator, restValue));
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

        sleepInSecond(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }


    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... Value) {
        return getElement(driver, castParameter(locator, Value)).getAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName, String... Value) {
        return getElement(driver, castParameter(locator, Value)).getDomProperty(propertyName);
    }

    public String getTextElement(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getTextElement(WebDriver driver, String locator, String... restValue) {
        return getElement(driver, castParameter(locator, restValue)).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRgbaColor(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public List<WebElement> getListElements(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListElements(WebDriver driver, String locator, String... restValue) {
        return driver.findElements(getByLocator(castParameter(locator, restValue)));
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListElements(driver, locator).size();
    }

    public int getListElementNumber(WebDriver driver, String locator, String... restValue) {
        return getListElements(driver, castParameter(locator, restValue)).size();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        boolean status = false;
        try {
            // Trường hợp 1: Element có hiển thị ở trên UI và có xuất hiện ở trong DOM (Visible/ Displayed) => true
            // Trường hợp 2: Element không hiển thị ở trên UI và có xuất hiện ở trong DOM (Invisible) => false
            WebElement element = getElement(driver, locator);
            status = element.isDisplayed();
        } catch (NoSuchElementException e) {
            // Trường hợp 3: Element không hiển thị ở trên UI và không có ở trong DOM ( Invisible ) => false
            throw new RuntimeException(e);
        }
        return status;
    }


    public boolean isElementDisplayed(WebDriver driver, String locator, String... restValue) {
        boolean status = false;
        try {
            // Trường hợp 1: Element có hiển thị ở trên UI và có xuất hiện ở trong DOM (Visible/ Displayed) => true
            // Trường hợp 2: Element không hiển thị ở trên UI và có xuất hiện ở trong DOM (Invisible) => false
            WebElement element = getElement(driver, castParameter(locator, restValue));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            // Trường hợp 3: Element không hiển thị ở trên UI và không có ở trong DOM ( Invisible ) => false
            return status;
        }
    }

    private void overrideGlobalTimeout(WebDriver driver, long timeInSecond) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSecond));
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... restValue) {
        System.out.println("Start time = " + new Date().toString());
        overrideGlobalTimeout(driver, SHORT_TIMEOUT);
        List<WebElement> elements =  getListElements(driver, castParameter(locator, restValue));
        overrideGlobalTimeout(driver, LONG_TIMEOUT);

        if (elements.size() == 0) {
            System.out.println("Trường hợp 3: Element không hiển thị ở trên UI và không có ở trong DOM (Invisible)");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Trường hợp 2: Element không hiển thị ở trên UI và có xuất hiện ở trong DOM (Invisible)");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else {
            System.out.println("Trường hợp 1: Element có hiển thị ở trên UI và có xuất hiện ở trong DOM (Visible/ Displayed)");
            return false;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        System.out.println("Start time = " + new Date().toString());
        overrideGlobalTimeout(driver, SHORT_TIMEOUT);
        List<WebElement> elements =  getListElements(driver, locator);
        overrideGlobalTimeout(driver, LONG_TIMEOUT);

        if (elements.size() == 0) {
            System.out.println("Trường hợp 3: Element không hiển thị ở trên UI và không có ở trong DOM (Invisible)");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Trường hợp 2: Element không hiển thị ở trên UI và có xuất hiện ở trong DOM (Invisible)");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else {
            System.out.println("Trường hợp 1: Element có hiển thị ở trên UI và có xuất hiện ở trong DOM (Visible/ Displayed)");
            return false;
        }
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restValue) {
        return getElement(driver, castParameter(locator, restValue)).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }
    public boolean isElementEnabled(WebDriver driver, String locator,String... restValue) {
        return getElement(driver,castParameter(locator,restValue)).isEnabled();
    }

    public void checkTheCheckBoxOrRadio(WebDriver driver, String locator) {
        if (!getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }

    public void checkTheCheckBoxOrRadio(WebDriver driver, String locator, String... restValue) {
        if (!getElement(driver, castParameter(locator, restValue)).isSelected()) {
            getElement(driver, castParameter(locator, restValue)).click();
        }
    }

    public void uncheckTheCheckBox(WebDriver driver, String locator) {
        if (getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }

    public void switchToIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    public void switchToDefaultPage(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    public void clickToElementByAction(WebDriver driver, String locator) {
        new Actions(driver).click(getElement(driver, locator)).perform();
    }

    public void clickAndHoldToElement(WebDriver driver, String locator) {
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }

    public void releaseLeftMouse(WebDriver driver) {
        new Actions(driver).release();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }

    public void dragAndDropToElement(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    public void scrollToElement(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }

    public void scrollToElement(WebDriver driver, String locator,String ... restValue) {
        new Actions(driver).scrollToElement(getElement(driver, castParameter(locator, restValue))).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys keys) {
        new Actions(driver).sendKeys(getElement(driver, locator), keys).perform();
    }

    public void sleepInSecond(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public Dimension getElementSize(WebDriver driver, String locator) {
        return getElement(driver, locator).getSize();
    }
    public Dimension getElementSize(WebDriver driver, String locator, String... restValue) {
        return getElement(driver, castParameter(locator,restValue)).getSize();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Set<Cookie> getPageCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setPageCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
    }

    public Alert waitAlertPresent(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitAlertPresent(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitAlertPresent(driver).dismiss();
    }

    public String getTextAlert(WebDriver driver) {
        return waitAlertPresent(driver).getText();
    }

    public void sendKeyToAlert(WebDriver driver, String valueToSendKey) {
        waitAlertPresent(driver).sendKeys(valueToSendKey);
    }

    public void openUrlByNewTab(WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.TAB).get(url);
    }

    public String getCurrentWindowID(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public void openUrlByNewWindow(WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.WINDOW).get(url);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void highlightElement(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSecond(3);
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, castParameter(locator, value)));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getElement(driver, locator));
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
        sleepInSecond(1);
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void setAttributeInDOMByJS(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public void sendKeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete" +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getElement(driver, locator));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, locator)));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator, String... value) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, castParameter(locator, value))));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator, String... value) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(castParameter(locator, value))));
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";

        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        getWebElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName.trim());
    }

    public void uploadSingleFileByLabel(WebDriver driver, String labelName, String fileName) {
        // Tự động đảm bảo có dấu phân cách thư mục chuẩn
        String fullFilePath = GlobalConstants.UPLOAD_PATH + File.separator + fileName;

        // Đảm bảo getWebElement tìm đúng tới thẻ input[type='file']
        getWebElement(driver, BasePageUI.UPLOAD_FILE_TYPE_BY_LABEL, labelName).sendKeys(fullFilePath);
    }


    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... value) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, value))));
    }

    public void waitForElementSelected(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator, String... value) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, value))));
    }

    public void waitForElementPresence(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }
    public void waitForElementPresence(WebDriver driver, String locator, String... value) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(castParameter(locator,value))));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... value) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, value))));
    }
    public void waitForElementInvisibleNotInDom(WebDriver driver, String locator, String... value) {
        new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, value))));
    }


    public WebElement waitElementClickable(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public WebElement waitElementClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator, String... value) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, value))));
    }

    private int SHORT_TIMEOUT = GlobalConstants.SHORT_TIMEOUT;

    private int LONG_TIMEOUT = GlobalConstants.LONG_TIMEOUT;

    // OrangeHRM
    public boolean isLoadingSpinnerDisappear(WebDriver driver) {
        return waitListElementInvisible(driver, BasePageUI.SPINNER_ICON);
    }

    public void enterToTextboxByLabel(WebDriver driver, String labelName, String valueToSendkey) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL_ORANGE, labelName);
        sendKeyToElementForHRM(driver, BasePageUI.TEXTBOX_BY_LABEL_ORANGE, valueToSendkey, labelName);
    }
    public void enterToTextAreaByLabel(WebDriver driver, String labelName, String valueToSendkey) {
        waitForElementVisible(driver, BasePageUI.TEXTAREA_BY_LABEL_ORANGE, labelName);
        sendKeyToElementForHRM(driver, BasePageUI.TEXTAREA_BY_LABEL_ORANGE, valueToSendkey, labelName);
    }
//    public void enterToDropdownByLabel(WebDriver driver, String labelName, String valueToSendkey) {
//        waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_LABEL_DATE, labelName);
//        sendKeyToElementForHRM(driver, BasePageUI.DROPDOWN_BY_LABEL_DATE, valueToSendkey, labelName);
//    }
    public void enterToDropdownByLabel(WebDriver driver, String labelName, String valueToSendkey) {
        // 1. Chờ element hiển thị
        waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_LABEL_DATE, labelName);

        // 2. Lấy WebElement
        WebElement element = getWebElement(driver, BasePageUI.DROPDOWN_BY_LABEL_DATE, labelName);

        // 3. Bôi đen (Ctrl + A) và xóa sạch dữ liệu cũ
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);

        // 4. Nhập giá trị mới
        element.sendKeys(valueToSendkey);

        // 5. Nhấn TAB để đóng Calendar Popover & kích hoạt sự kiện validation (blur event)
        element.sendKeys(Keys.TAB);
    }

    public String getTextboxDropdownByLabel(WebDriver driver, String value) {
        waitForElementVisible(driver, BasePageUI.DROPDOWN_TEXT_BY_LABEL_DATE, value);
        return getTextElement(driver, BasePageUI.DROPDOWN_TEXT_BY_LABEL_DATE, value);
    }
    public void enterToTextboxByName(WebDriver driver, String nameAttribute, String valueToSendkey) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_NAME_ORANGE, nameAttribute);
        sendKeyToElementForHRM(driver, BasePageUI.TEXTBOX_BY_NAME_ORANGE, valueToSendkey, nameAttribute);
    }

    public void clickToButtonByTextOrange(WebDriver driver, String buttonName) {
        waitForElementVisible(driver, BasePageUI.BUTTON_BY_TEXT_ORANGE, buttonName);
        clickToElementByJS(driver, BasePageUI.BUTTON_BY_TEXT_ORANGE, buttonName);
    }
    public void clickToSaveButtonPopUp(WebDriver driver, String buttonName) {
        waitForElementVisible(driver, BasePageUI.SAVE_BUTTON, buttonName);
        clickToElementByJS(driver, BasePageUI.SAVE_BUTTON, buttonName);
    }
    public boolean isRadioIsSelected(WebDriver driver, String labelName) {
        waitForElementPresence(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
        return isElementSelected(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
    }

    public void clickToButtonByTextInMainTitle(WebDriver driver, String buttonText) {
        waitForElementVisible(driver, BasePageUI.BUTTON_BY_TEXT_IN_MAIN_TITLE, buttonText);
        clickToElement(driver, BasePageUI.BUTTON_BY_TEXT_IN_MAIN_TITLE, buttonText);
    }


    public String getTextboxValueByName(WebDriver driver, String textboxName) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_NAME_ORANGE, textboxName);
        return getElementDOMProperty(driver, BasePageUI.TEXTBOX_BY_NAME_ORANGE, "value", textboxName);
    }
    public String getTextboxValueByLabel(WebDriver driver, String textboxName) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL_ORANGE, textboxName);
        return getElementDOMProperty(driver, BasePageUI.TEXTBOX_BY_LABEL_ORANGE, "value", textboxName);
    }
    public String getTextboxValueByLabelDate(WebDriver driver, String textboxName) {
        waitForElementVisible(driver, BasePageUI.DROPDOWN_TEXT_BY_LABEL_SELECT, textboxName);
        return getElementAttribute(driver, BasePageUI.DROPDOWN_TEXT_BY_LABEL_SELECT, "value", textboxName);
    }
    public String getTerminateMessage(WebDriver driver, String messageText) {
        waitForElementVisible(driver, BasePageUI.TERMINATION_MESSAGE,messageText);
        return getTextElement(driver, BasePageUI.TERMINATION_MESSAGE,messageText);
    }

    public void clickToModuleByTextInMenuItem(WebDriver driver, String moduleName) {
        waitElementClickable(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        clickToElement(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
    }
    public void clickToModuleByTextInMenuNavigation(WebDriver driver, String moduleName) {
        waitElementClickable(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_NAVIGATION, moduleName);
        clickToElement(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_NAVIGATION, moduleName);
    }

    public boolean isModuleByTextInMenuItemDisplayed(WebDriver driver, String moduleName) {
        waitForElementVisible(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        return isElementDisplayed(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
    }
    public boolean isModuleByTextInMenuItemUndisplayed(WebDriver driver, String moduleName) {
//        waitForElementInvisibleNotInDom(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        return isElementUndisplayed(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
    }

    public void selectDropdownByLabel(WebDriver driver, String labelName, String valueToSelect) {
        waitElementClickable(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, labelName);
        selectItemInCustomDropdown(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, BasePageUI.CHILD_DROPDOWN_BY_LABEL, valueToSelect, labelName);
    }
    public void selectDropdownByLabels(WebDriver driver, String labelName, String valueToSelect) {
        waitElementClickable(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL_Sub, labelName);
        selectItemInCustomDropdown(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL_Sub, BasePageUI.CHILD_DROPDOWN_OPTIONS_Sub, valueToSelect, labelName);
    }


    public boolean isToastMessageDisplayed(WebDriver driver, String toastMessage) {
        waitForElementVisible(driver, BasePageUI.TOAST_MESSAGE_BY_TEXT, toastMessage);
        return isElementDisplayed(driver, BasePageUI.TOAST_MESSAGE_BY_TEXT, toastMessage);
    }

    public boolean isAlertMessageDisplayed(WebDriver driver, String alertMessage) {
        waitForElementVisible(driver, BasePageUI.ALERT_MESSAGE_BY_TEXT, alertMessage);
        return isElementDisplayed(driver, BasePageUI.ALERT_MESSAGE_BY_TEXT, alertMessage);
    }

    public String getErrorMessageAtTextbox(WebDriver driver) {
        waitForElementVisible(driver, BasePageUI.ERROR_MESSAGE);
        return getTextElement(driver, BasePageUI.ERROR_MESSAGE);
    }

    public void clickToRadioButtonByLabel(WebDriver driver, String labelName) {
        waitForElementPresence(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
        clickToElementByJS(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
    }

    public void clickToCheckboxByLabel(WebDriver driver, String labelName) {
        waitElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL, labelName);
        clickToElement(driver, BasePageUI.CHECKBOX_BY_LABEL, labelName);
    }

    public LoginPageObject clickLogoutOnTopMenu(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.USER_DROPDOWN);
        clickToElement(driver, BasePageUI.USER_DROPDOWN);
        waitElementClickable(driver, BasePageUI.LOGOUT_LINK);
        clickToElement(driver, BasePageUI.LOGOUT_LINK);
        return PageGenerator.getPage(LoginPageObject.class, driver);
    }

    // Records
    public void clickToAddButtonByTextOrange(WebDriver driver,String buttonName) {
        waitForElementVisible(driver, BasePageUI.BUTTON_ADD_BY_TEXT, buttonName);
        clickToElementByJS(driver, BasePageUI.BUTTON_ADD_BY_TEXT, buttonName);
    }
    // 1. Hai hàm public chính chỉ còn đúng 1 dòng
    public boolean isAllRowsDisabledExceptFirst(WebDriver driver) {
        return checkRowsState(driver,1, false); // Bắt đầu từ dòng 2 (index 1), kỳ vọng DISABLE (false)
    }

    public boolean areAllRowsEnabled(WebDriver driver) {
        return checkRowsState(driver,0, true);  // Bắt đầu từ dòng 1 (index 0), kỳ vọng ENABLE (true)
    }

    public void clickToActionByName(WebDriver driver,String firstname, String action) {
        waitForElementVisible(driver, BasePageUI.EDIT_REMOVE_ICON_BY_NAME, firstname, action);
        clickToElementByJS(driver, BasePageUI.EDIT_REMOVE_ICON_BY_NAME, firstname, action);
    }
    public void clickToActionById(WebDriver driver,String Id, String action) {
        waitForElementVisible(driver, BasePageUI.EDIT_REMOVE_ICON_BY_ID, Id, action);
        clickToElementByJS(driver, BasePageUI.EDIT_REMOVE_ICON_BY_ID, Id, action);
    }

    public boolean isPopupTitleDisplayed(WebDriver driver, String titleName) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_POPUP_TITLE, titleName);
        return isElementDisplayed(driver, BasePageUI.DYNAMIC_POPUP_TITLE, titleName);
    }
    public boolean isDeleteButtonDisplayed(WebDriver driver, String buttonName) {
        waitForElementVisible(driver, BasePageUI.BUTTON_BY_TEXT_ORANGE, buttonName);
        return isElementDisplayed(driver, BasePageUI.BUTTON_BY_TEXT_ORANGE, buttonName);
    }
    public boolean isNotDeleteButtonDisplayed(WebDriver driver, String buttonName) {
        waitForElementInvisibleNotInDom(driver, BasePageUI.BUTTON_BY_TEXT_ORANGE, buttonName);
        return isElementUndisplayed(driver, BasePageUI.BUTTON_BY_TEXT_ORANGE, buttonName);
    }
    // 2. Hàm Helper private xử lý logic dùng chung
    private boolean checkRowsState(WebDriver driver, int startIndex, boolean expectedEnabled) {
        List<WebElement> rows = getListElements(driver,BasePageUI.ALL_ROW_CARDS);

        for (int i = startIndex; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            WebElement pencilBtn = row.findElement(getByLocator(BasePageUI.PENCIL_BUTTON_IN_ROW));
            WebElement trashBtn = row.findElement(getByLocator(BasePageUI.TRASH_BUTTON_IN_ROW));

            if (isButtonEnabled(pencilBtn) != expectedEnabled || isButtonEnabled(trashBtn) != expectedEnabled) {
                System.out.println("Lỗi trạng thái tại dòng thứ: " + (i + 1));
                return false;
            }
        }
        return true;
    }

    // 3. Hàm Helper phụ kiểm tra trạng thái hoạt động của 1 button
    private boolean isButtonEnabled(WebElement element) {
        return element.isEnabled()
                && element.getAttribute("disabled") == null
                && !element.getAttribute("class").contains("disabled");
    }

    public void selectItemInAutocompleteByLabel(WebDriver driver, String labelName, String valueToType, String expectedItem) {
        // 1. Focus và làm sạch ô Input
        String inputXpath = String.format(BasePageUI.DYNAMIC_AUTOCOMPLETE_TEXTBOX_BY_LABEL, labelName);
        waitElementClickable(driver, inputXpath);
        WebElement inputElement = getWebElement(driver, inputXpath);

        inputElement.click();
        inputElement.sendKeys(Keys.CONTROL + "a");
        inputElement.sendKeys(Keys.DELETE);

        // 2. Gõ từ khóa với độ trễ 50ms (Kích hoạt Vue.js Event & API mà không làm chậm script)
        for (char c : valueToType.toCharArray()) {
            inputElement.sendKeys(String.valueOf(c));
            sleepInSecond(1);
        }

        // 3. Chờ khung Dropdown (Listbox) mở ra thành công
        waitForElementVisible(driver, BasePageUI.AUTOCOMPLETE_LISTBOX);

        // 4. Click trực tiếp vào Item theo tên đã chuẩn hóa (Không cần vòng lặp for)
        String normalizedExpectedText = expectedItem.replaceAll("\\s+", " ").trim();
        String optionXpath = String.format(BasePageUI.AUTOCOMPLETE_OPTIONS, normalizedExpectedText);

        waitElementClickable(driver, optionXpath);
        clickToElement(driver, optionXpath);
    }
//    public void selectItemInAutocompleteByLabel(WebDriver driver, String labelName, String valueToType, String expectedItem) {
//        // 1. Định vị ô Input theo Label
//        String inputXpath = String.format(BasePageUI.DYNAMIC_AUTOCOMPLETE_TEXTBOX_BY_LABEL, labelName);
//        waitElementClickable(driver, inputXpath);
//        WebElement inputElement = getWebElement(driver, inputXpath);
//
//        // 2. Click Focus và xóa sạch dữ liệu cũ bằng phím tắt (Tránh dùng clear() làm hỏng Event)
//        inputElement.click();
//        inputElement.sendKeys(Keys.CONTROL + "a");
//        inputElement.sendKeys(Keys.DELETE);
//
//
//        // 3. Gõ từng ký tự để kích hoạt sự kiện Reactive/Debounce API của OrangeHRM
//        for (char c : valueToType.toCharArray()) {
//            inputElement.sendKeys(String.valueOf(c));
//            sleepInSecond(150);
//        }
//
//        // 4. Chờ danh sách gợi ý hiển thị bằng hàm Wrapper của BasePage
//        waitForElementVisible(driver, BasePageUI.AUTOCOMPLETE_OPTIONS);
//        List<WebElement> allItems = getListElements(driver, BasePageUI.AUTOCOMPLETE_OPTIONS);
//
//        // 5. Duyệt danh sách và click chọn item đã chuẩn hóa khoảng trắng
//        for (WebElement item : allItems) {
//            String actualItemText = item.getText().replaceAll("\\s+", " ").trim();
//            String expectedItemText = expectedItem.replaceAll("\\s+", " ").trim();
//
//            if (actualItemText.contains(expectedItemText)) {
//                item.click();
//                break;
//            }
//        }
//    }



    public boolean isRecordCountDisplayed(WebDriver driver, String expectedCountText) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_RECORD_FOUND_TEXT, expectedCountText);
        return isElementDisplayed(driver, BasePageUI.DYNAMIC_RECORD_FOUND_TEXT, expectedCountText);
    }

    public void selectAllRecordsBySection(WebDriver driver, String sectionName) {
        waitElementClickable(driver,BasePageUI.DYNAMIC_SELECT_ALL_CHECKBOX_BY_SECTION, sectionName);
        clickToElementByJS(driver,BasePageUI.DYNAMIC_SELECT_ALL_CHECKBOX_BY_SECTION, sectionName);
    }

    public void selectSingleRecordsBySectionIndex(WebDriver driver, String workExperience, String number) {
        waitElementClickable(driver, BasePageUI.DYNAMIC_SELECT_SINGLE_CHECKBOX_BY_SECTION_INDEX,workExperience, number);
        clickToElementByJS(driver, BasePageUI.DYNAMIC_SELECT_SINGLE_CHECKBOX_BY_SECTION_INDEX,workExperience, number);
    }

    public void clickToCheckboxByEmployeeId(WebDriver driver, String Id) {
        waitElementClickable(driver,BasePageUI.CHECKBOX_RECORD_BY_ID, Id);
        scrollToElement(driver,BasePageUI.CHECKBOX_RECORD_BY_ID, Id);
        clickToElementByJS(driver,BasePageUI.CHECKBOX_RECORD_BY_ID, Id);
    }

    public void clickToCheckboxByEmployeeIdWithPagination(WebDriver driver, String id) {
        while (!isElementDisplayed(driver, BasePageUI.CHECKBOX_RECORD_BY_ID, id)) {
            // Kiểm tra nếu nút Next vẫn click được thì chuyển trang, nếu không thấy nữa thì dừng
            if (isElementDisplayed(driver, BasePageUI.PAGINATION_NEXT_BUTTON)) {
                clickToElement(driver, BasePageUI.PAGINATION_NEXT_BUTTON);
                isLoadingSpinnerDisappear(driver);
            } else {
                break; // Đã duyệt hết tất cả các trang mà không thấy ID
            }
        }
        // Bấm chọn checkbox khi tìm thấy
        clickToElementByJS(driver, BasePageUI.CHECKBOX_RECORD_BY_ID, id);
    }
}
