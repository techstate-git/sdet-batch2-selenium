package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebUtils {
    private static WebDriver driver;

    public static WebElement waitForElement(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void sendKeysToElement(String text, WebElement element, Duration timeout) {
        //waitForElement() = WebElement element = driver.findElement(By.xpath(""));
        WebElement visibleElement = waitForElement(element, timeout);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    public static void clickElement(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();
    }
}









