package com.vz.steps;
        import com.vz.DriverManager;

        import org.testng.Assert;

        import cucumber.api.java.en.Given;
        import cucumber.api.java.en.Then;
        import cucumber.api.java.en.When;
        import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class SimpleStep{

    @Given("^a precondition$")
    public void a_precondition() throws Throwable {

        Long id  = DriverManager.GetID();
        System.out.println("given " + id + " ");
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @When("^an action takes place$")
    public void an_action_takes_place() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^the expected behavior is displayed$")
    public void the_expected_behavior_is_displayed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
}