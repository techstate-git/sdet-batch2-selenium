package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static utils.WebUtils.clickElement;
import static utils.WebUtils.sendKeysToElement;

public class SearchResultsPage {
    WebDriver driver;

    @FindBy(xpath = "//input[@aria-label='Minimum Value in $']")
    private WebElement minPriceInput;

    @FindBy(xpath = "//input[@aria-label='Maximum Value in $']")
    private WebElement maxPriceInput;

    @FindBy(xpath = "//button[@aria-label='Submit price range']")
    private WebElement applyButton;

    @FindBy(xpath = "//span[@class='s-item__price']")
    private List<WebElement> priceElements;

    @FindBy(xpath = "//button[@aria-label='Sort selector. Best Match selected.']")
    private WebElement sortDropDown;

    @FindBy(xpath = "//ul[@class='srp-results srp-grid clearfix']//div[@class='s-item__info clearfix']/a//span[@role='heading']")
    private List<WebElement> item_title;

    @FindBy(xpath = "//ul[@class='srp-results srp-list clearfix']/li[3]//a[@class='s-item__link']")
    private WebElement secondItem;

    @FindBy(xpath = "//h1[@class='x-item-title__mainTitle']/span")
    private WebElement main_item_title;

    @FindBy(xpath = "//div[@id='ProductDetails']")
    private WebElement productSpec_1;

    @FindBy(xpath = "//div[@class='tabs']")
    private WebElement productSpec_2;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void applyPriceFilter(String minPrice, String maxPrice) {
        sendKeysToElement(minPrice, minPriceInput, Duration.ofSeconds(5));
        sendKeysToElement(maxPrice, maxPriceInput, Duration.ofSeconds(5));
        clickElement(applyButton, Duration.ofSeconds(5));
    }

    public boolean areResultsWithinPriceRange(int minPrice, int maxPrice) {
        for (WebElement element : priceElements) {
            // Replace eny character except: "0123456789."
            // " Tech state  77625.. Techstate" -> "77625.."
            String priceText = element.getText().replaceAll("[^0-9.]", "");

            if (priceText.isEmpty()) {
                System.out.println("Skipping an item with no price displayed");
                continue;
            }

            double price;

            try {
                price = Double.parseDouble(priceText);
            } catch (NumberFormatException e) {
                System.out.println("Skipping an item with an invalid price: " + priceText);
                continue;
            }

            if (price < minPrice || price > maxPrice) {
                return false;
            } else {
                System.out.println("Valid price within range: " + price);
            }
        }
        return true;
    }

    //String option = Price + Shipping: lowest first
    public void sortResultsBy(String option) {
        clickElement(sortDropDown, Duration.ofSeconds(5));
        WebElement sortOptions = driver.findElement(By.xpath("//ul[@class='fake-menu__items']/li/a/span[.='" + option + "']"));
        clickElement(sortOptions, Duration.ofSeconds(5));
    }

    public boolean arePricesSortedLowToHigh() {
        List<Double> prices = new ArrayList<>(); //List of actual prices from page

        for (WebElement priceElement : priceElements) {

            String priceText = priceElement.getText().replaceAll("[^0-9.]", "");
            if (!priceText.isEmpty()) {
                prices.add(Double.parseDouble(priceText));
            }
        }
        // 0.5, 0.9, 1.5, 0.99

        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        // 0.5, 0.9, 0.99, 1.5

        if (!prices.equals(sortedPrices)) {
            System.out.println("The results are not sorted from lowest to highest price!");
            System.out.println("Actual prices: " + prices);
            System.out.println("Expected prices: " + sortedPrices);
            return false;
        }

        return true;
    }

    public void selectCategory(String category) {
        //ul[@class='srp-refine__category__list']//li/span[contains(text(),'"+ category +"')]
        WebElement categoryElement = driver.findElement(By.xpath("//span[text()='" + category + "']"));
        categoryElement.click();
    }

    public void selectBrand(String brand) {
        //div[@class="x-refine__multi-select"]//span[text()='Jordan']
        WebElement categoryElement = driver.findElement(By.xpath("//div[@class='x-refine__multi-select']//span[text()='" + brand + "']"));
        categoryElement.click();
    }

    public boolean verifyContainsJordan(String brand) {
        for (WebElement element : item_title) {
            String elementTitleText = element.getText();
            if (elementTitleText.contains(brand)) {
                return true;
            } else {
                System.out.println("Invalid Title: " + elementTitleText);
            }
        }
        return false;
    }

    public void clickOnSecondItem() {
        String currentWindow = driver.getWindowHandle(); //Original Session ID
        System.out.println("Current Session" + currentWindow);
        secondItem.click();
        switchToNewTab(currentWindow);
    }

    public void switchToNewTab(String currentWindow) {
        Set<String> allWindows = driver.getWindowHandles(); //Original Session ID, Secondary Session ID
        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public boolean verifyMainTitle(String title) {
        String mainItemTitle = main_item_title.getText();
        System.out.println(mainItemTitle);

        if (main_item_title.isDisplayed()) {
            return true;
        }

        if (mainItemTitle.toLowerCase().contains(title.toLowerCase())) {
            System.out.println("Title: " + mainItemTitle + "contains: " + title);
            return true;
        }
        return false;
    }

    public boolean productSpecIsDisplayed() {
        try {
            WebElement productSpec1 = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(productSpec_1));
            if (productSpec1.isDisplayed()) {
                System.out.println("product-Spec_1 is displayed");
                return true;
            }
        } catch (Exception e) {
            System.out.println("product-Spec_1 not found");
        }

        try {
            WebElement productSpec2 = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(productSpec_2));
            if (productSpec2.isDisplayed()) {
                System.out.println("product-Spec_2 is displayed");
                return true;
            }
        } catch (Exception e) {
            System.out.println("product-Spec_2 not found");
        }

        return false;
    }
}














