package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.DriverManager;

public class SearchSteps {
    WebDriver driver = DriverManager.getDriver();
    HomePage homePage = new HomePage(driver);
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

    private String minPrice;
    private String maxPrice;

    @Given("I am on the eBay homepage")
    public void ebayHomePage() throws InterruptedException {
        driver.get("https://www.ebay.com/");
    }

    @When("I search for {string}")
    public void iSearchFor(String product) {
        homePage.enterSearchTerm(product);
        homePage.clickSearchButton();
    }

    @Then("I should see search results containing {string}")
    public void searchResults(String result) {
        String resultsText = homePage.getSearchResultsText();
        System.out.println(resultsText);
        Assert.assertTrue(resultsText.toLowerCase().contains(result.toLowerCase()));

        if (resultsText.contains(result)) {
            System.out.println("The Strings are equal");
        } else {
            System.out.println("The Strings are not equal");
        }
    }

    @And("I apply a price filter from {string} to {string}")
    public void applyPriceFilter(String from, String to) {
        this.minPrice = from;
        this.maxPrice = to;
        searchResultsPage.applyPriceFilter(from, to);
    }

    @Then("I should see search results within the price range")
    public void setSearchResultsPage(){
        int min = Integer.parseInt(minPrice);
        int max = Integer.parseInt(maxPrice);
        Assert.assertTrue(searchResultsPage.areResultsWithinPriceRange(min, max));
    }

    @And("I sort the results by {string}")
    public void sortTheResultsBy(String option) {
        searchResultsPage.sortResultsBy(option);
    }

    @Then("I should see results sorted from lowest to highest price")
    public void seeSortedResultsLowToHigh() {
        Assert.assertTrue("The results are sorted correctly!", searchResultsPage.arePricesSortedLowToHigh());
    }

    @And("I filter the results by category {string}")
    public void filterByCategory(String category) {
        searchResultsPage.selectCategory(category);
    }

    @And("I filter the results by Brand {string}")
    public void filterByBrand(String brand) {
        searchResultsPage.selectBrand(brand);
    }

    @Then("I should verify that results contains brand {string} on each item")
    public void verifyItemTitleHasContainingText(String brand) {
        searchResultsPage.verifyContainsJordan(brand);
    }

    @And("I click on the first product")
    public void clickOnFirstProduct() {
        searchResultsPage.clickOnSecondItem();
    }

    @Then("I should verify that the product title contains {string}")
    public void verifyTitleContains(String itemTitle){
        Assert.assertTrue(searchResultsPage.verifyMainTitle(itemTitle));
    }

    @And("I should verify that the product specifications are displayed")
    public void verifyProductSpec(){
        Assert.assertTrue(searchResultsPage.productSpecIsDisplayed());
    }
}







