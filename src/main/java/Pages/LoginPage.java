package Pages;

import Uitiliti.ActionsClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.winium.WiniumDriver;


public class LoginPage {
    private static WiniumDriver driver = null;
    private static By userNameField = By.name("اسم المستخدم");
    private static By passwordField = By.name(" كلمة المرور");
    private static By loginButton = By.name("تسجيل الدخول");

    public LoginPage(WiniumDriver driver) {
        this.driver = driver;
    }
    public static WiniumDriver getDriver(){return driver;};
    @Step("Login with user name: {userName} and Password:{Password}")
    public static void LoginPageActions(String userName, String Password) {
        ActionsClass act= new ActionsClass(driver);
        driver.findElement(userNameField).sendKeys(userName);
        driver.findElement(passwordField).sendKeys(Password);
        act.Click(driver.findElement(loginButton));
    }
}
