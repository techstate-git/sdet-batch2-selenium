package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverManager;

import java.util.ArrayList;
import java.util.List;

public class AdvanceSearch {
    WebDriver driver = DriverManager.getDriver();

    @When("I type a search term and verify results")
    public void iTypeSearchTerm(DataTable dataTable) {
        List<String> searchTerms = dataTable.asList(String.class);
        System.out.println(searchTerms);
        for (String term : searchTerms) {
            //[Deals, Electronics, Fashion]
            //WebElement page = driver.findElement(By.linkText(term));

            WebElement searchBox = driver.findElement(By.xpath("//input[@id='gh-ac']"));
            searchBox.clear();
            searchBox.sendKeys(term);

            WebElement searchButton = driver.findElement(By.xpath("//input[@id='gh-btn']"));
            searchButton.click();

            driver.navigate().back();
        }
    }

    @When("I navigate to the following pages and print each page has a title")
    public void iNavigateAndPrintTitle(DataTable dataTable) {
        List<String> pageTitles = dataTable.asList(String.class);
        System.out.println(pageTitles);

        for (String pageTitle : pageTitles) {
            WebElement clothing = driver.findElement(By.linkText(pageTitle));
            clothing.click();
            System.out.println(driver.getTitle());
            driver.navigate().back();
        }
    }

    @When("I check the following popular categories are displayed")
    public void iCheckTheFollowingSectionHeaders(DataTable dataTable) {
        List<String> headers = dataTable.asList(String.class);

        for (String header : headers) {
            try {
                WebElement headerElement = driver.findElement(
                        By.xpath("//h3[@class='vl-popular-destinations-evo__name' and text()='" + header + "']"));

                if (headerElement.isDisplayed()) {
                    System.out.println("The section header \"" + header + "\" is present");
                }
            } catch (Exception e) {
                System.out.println("Element header \"" + header + "\" is not present");
            }
        }
    }

    @Then("I navigate to the Gift Cards page")
    public void iNavigateToTheGiftCardsPage() {
        driver.navigate().to("https://www.ebay.com/giftcards");
    }

    @When("I check the benefits are the same as my examples")
    public void iCheckTheBenefitsAreTheSameAsMyExamples(DataTable dataTable) {
        List<String> expectedBenefits = dataTable.asList(String.class);
        List<String> displayedBenefits = new ArrayList<>();

        List<WebElement> benefitElementsOnPage =
                driver.findElements(By.xpath("//h3[@class='text-col__title']"));

        for (WebElement element : benefitElementsOnPage) {
            String elementText = element.getText().trim();
            displayedBenefits.add(elementText); //Hassle-free
            System.out.println("Item: " + elementText + " has been added");
        }

        System.out.println("Final list of elements: " + displayedBenefits);
        System.out.println("Expected list of elements: " + expectedBenefits);

        Assert.assertEquals(expectedBenefits, displayedBenefits);
    }

    @When("I navigate to the Deals section")
    public void iNavigateToTheSection() {
        driver.navigate().to("https://www.ebay.com/deals/other-deals");
    }

    @And("I click on the {string} category")
    public void iClockOnCategory(String category) { //Collectibles
        WebElement categoryElement =
                driver.findElement(By.xpath("//span[@class='dne-see-more-type-text-span' and text()='"+ category +"']"));
        categoryElement.click();
    }

    @Then("I should verify that at least {string} products are displayed")
    public void verifyProductCount(String minCount) { //10
        int minCountInt = Integer.parseInt(minCount);

        List<WebElement> products = driver.findElements(By.xpath("//div[@class='slashui-image-cntr']"));
        int productCount = products.size();

        Assert.assertTrue("Expected at least " + minCountInt + " products, but found only " +
                productCount,productCount >= minCountInt);

        System.out.println("Verified that at least " + minCountInt + " products are displayed.");
    }
}













