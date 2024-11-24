package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='gh-ac']")
    private WebElement searchBox;

    @FindBy(xpath = "//input[@id='gh-btn']")
    private WebElement searchButton;

    @FindBy(xpath = "//h1[@class='srp-controls__count-heading']")
    private WebElement searchResults;

    public void enterSearchTerm(String product) {
        searchBox.clear();
        searchBox.sendKeys(product);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String getSearchResultsText() {
        return  searchResults.getText();
    }
}
