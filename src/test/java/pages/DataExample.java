package pages;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class DataExample {
    List<String> wordsAll = new ArrayList<>();
    int sum1;

    @Given("I have these words:")
    public void iHaveTheseWords(DataTable dataTable) {
        wordsAll = dataTable.asList(String.class);
    }

    @Given("I concatenate them with a space")
    public void iConcatenate() {
        String concatenated = String.join("$", wordsAll);
        System.out.println(concatenated);
    }

    @Given("I have numbers {int} and {int}")
    public void sumOfNumbers(int num1, int num2) {
        sum1 = num1 + num2;
    }

    @Given("I calculate their sum and verify it is {int}")
    public void sumOfNum(int sum) {
        System.out.println("sum of 2 numbers: " + sum1);
        Assert.assertEquals(sum1, sum);
    }
}
