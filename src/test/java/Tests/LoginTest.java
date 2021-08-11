package Tests;

import Pages.LoginPage;
import Uitiliti.configFileReader;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners(Uitiliti.Listeners.class)
public class LoginTest {
    private static WiniumDriver driver;
    private static DesktopOptions options;
    configFileReader config;

    @BeforeMethod
    public void setUp() throws MalformedURLException, InterruptedException {
        config = new configFileReader();
        options = new DesktopOptions();
        options.setApplicationPath(config.getAppPath());
        driver = new WiniumDriver(new URL("http://localhost:9999"), options);
        Thread.sleep(5000);
        LoginPage lp = new LoginPage(driver);
    }

    @Step("Start our test cases.")
    @Description("Login with empty user name.")
    @Test(description = "login with unvalid data")
    public void LoginWithEmptyUserName() {
        LoginPage.LoginPageActions("", "123456");
        String ActualResult = driver.findElement(By.name("من فضلك حدد اسم المستخدم")).getAttribute("Name");
        Assert.assertEquals("من فضلك حدد اسم المستخدم", ActualResult);
    }

    @Description("Login with empty password.")
    @Test(dependsOnMethods = {"LoginWithEmptyUserName"}, description = "login with unvalid data")
    public void LoginWithOutPassword() {
        LoginPage.LoginPageActions("Admin", "");
        String ActualResult = driver.findElement(By.name("من فضلك حدد كلمة المرور")).getAttribute("Name");
        Assert.assertEquals("من فضلك حدد كلمة المرور", ActualResult);
    }

    @Description("Login with wrong user name.")
    @Test(dependsOnMethods = {"LoginWithOutPassword"}, description = "login with unvalid data")
    public void LoginWithwrongUserName() throws InterruptedException {
        LoginPage.LoginPageActions("mostafa", "123789");
        Thread.sleep(5000);
        String ActualResult = driver.findElement(By.name("تسجيل الدخول")).getAttribute("Name");
        Assert.assertEquals("تسجيل الدخول", ActualResult);
    }

    @Description("Login with wrong Password.")
    @Test(dependsOnMethods = {"LoginWithwrongUserName"}, description = "Login with unvalid data")

    public void LoginWithwWrongPassword() throws InterruptedException {
        LoginPage.LoginPageActions("admin", "254356");
        Thread.sleep(5000);
        String ActualResult = driver.findElement(By.name("تسجيل الدخول")).getAttribute("Name");
        Assert.assertEquals("تسجيل الدخول", ActualResult);
    }

    @Description("Login with correct user name and password.")
    @Test(dependsOnMethods = {"LoginWithwWrongPassword"}, description = "login with valid data")

    public void LoginWithwCorrectCredintials() throws InterruptedException {
        LoginPage.LoginPageActions("admin", "123789");
        Thread.sleep(5000);
        String ActualResult = driver.findElement(By.name("فاتوره مبيعات فاتوره مبيعات")).getAttribute("Name");
        Assert.assertEquals("فاتوره مبيعات فاتوره مبيعات", ActualResult);
    }

    @Step("Close the app")
    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
