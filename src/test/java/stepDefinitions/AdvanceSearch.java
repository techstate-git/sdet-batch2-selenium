package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverManager;

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
}













