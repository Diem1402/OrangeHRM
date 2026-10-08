package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class PageGenerator {

    public static <T extends BasePage> T getPage(Class<T> pageClass, WebDriver driver) {
        try {
            // Lấy constructor nhận WebDriver
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            // Tạo instance mới của page class
            return constructor.newInstance(driver);
        } catch (Exception e) {
        throw new RuntimeException("Can not init Page Object class:" + pageClass.getSimpleName(), e);}
    }
}
