package Uitiliti;

import Pages.LoginPage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    private static String getTestMethodNAME(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Web page screenshot .", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am on test passed method" + getTestMethodNAME(iTestResult) + "passed");
        Object testClass = iTestResult.getInstance();
        WiniumDriver driver = LoginPage.getDriver();
        if (driver instanceof WiniumDriver) {
            System.out.println("Screenshot captured for test case " + getTestMethodNAME(iTestResult));
            saveScreenshotPNG(driver);
        }
        saveTextLog(getTestMethodNAME(iTestResult) + "Passed and screen shot has been taken");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am on test Failed method" + getTestMethodNAME(iTestResult) + "Failed");
        Object testClass = iTestResult.getInstance();
        WiniumDriver driver = LoginPage.getDriver();
        if (driver instanceof WiniumDriver) {
            System.out.println("Screenshot captured for test case " + getTestMethodNAME(iTestResult));
            saveScreenshotPNG(driver);
        }
        saveTextLog(getTestMethodNAME(iTestResult) + "Failed and screen shot has been taken");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResultresult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
