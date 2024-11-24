package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class GherkinPracticeSteps {
    @Given("Parse string parameter of {string}")
    public void stringParameter(String a) {
        System.out.println(a);
    }

    @And("Parse integer parameter of {int}")
    public void integerParameter(int a){
        System.out.println(a);
    }

    @And("Sum of {int} and {int}")
    public void sumOfTwo(int a, int b){
        System.out.println(a + b);
    }

    @Given("Result of {int} {string} {int}")
    public void firstTask(int a, String w, int b) {
        System.out.println(a / b);
    }

    @And("Result of {int} + {int} * {int}")
    public void secondTask(int a, int b, int c){
        System.out.println(a + b * c);
    }
    @And("Result of concatenation {string} and {string}")
    public void treeTask(String a, String b){
        System.out.println(a + b);
    }
}









