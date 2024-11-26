package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.WebUtils.clickElement;
import static utils.WebUtils.sendKeysToElement;

public class SearchResultsPage {
    WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@aria-label='Minimum Value in $']")
    private WebElement minPriceInput;

    @FindBy(xpath = "//input[@aria-label='Maximum Value in $']")
    private WebElement maxPriceInput;

    @FindBy(xpath = "//button[@aria-label='Submit price range']")
    private WebElement applyButton;

    public void applyPriceFilter(String minPrice, String maxPrice) {
        sendKeysToElement(minPrice, minPriceInput, Duration.ofSeconds(5));
        sendKeysToElement(maxPrice, maxPriceInput, Duration.ofSeconds(5));
        clickElement(applyButton, Duration.ofSeconds(5));
    }
}














