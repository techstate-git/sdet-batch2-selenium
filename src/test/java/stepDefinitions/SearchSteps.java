package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.DriverManager;

public class SearchSteps {
    WebDriver driver = DriverManager.getDriver();
    HomePage homePage = new HomePage(driver);

    @Given("I am on the eBay homepage")
    public void ebayHomePage() throws InterruptedException {
        driver.get("https://www.ebay.com/");
        Thread.sleep(5000);
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
}






