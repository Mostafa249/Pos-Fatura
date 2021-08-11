package Uitiliti;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.WiniumDriver;

public class ActionsClass {
    private WiniumDriver driver;

    public ActionsClass(WiniumDriver driver) {
        this.driver = driver;
    }

    public void Click(WebElement element) {
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(element).click().perform();
    }
    public void SendKeys(WebElement element, String text) {
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.sendKeys(element,text).perform();
    }
}
