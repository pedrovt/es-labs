package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

/**
 * hellocucumber - hellocucumber.Stepdefs <br>
 *
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 1.0 - February 14, 2020
 */
public class Stepdefs {

    private String today;
    private String actualAnswer;

    @Given("today is Sunday")

    public void today_is_Sunday() {
        // Write code here that turns the phrase above into concrete actions
        today = "Sunday";
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        // Write code here that turns the phrase above into concrete actions
        actualAnswer = IsItFriday.isItFriday(today);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(expectedAnswer, actualAnswer);
    }
}
