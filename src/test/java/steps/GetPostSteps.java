package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GetPostSteps {
    @Given("^I perform GET for \"([^\"]*)\"$")
    public void iPerformGETFor(String url) throws Throwable {
    }

    @And("^I perform GET for the post number \"([^\"]*)\"$")
    public void iPerformGETForThePostNumber(String postNumber) {
        BDDStyleMethod.SimpleGetPost(postNumber);
    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String arg0) throws Throwable {
    }

    @Then("^I should see the author names$")
    public void iShouldSeeTheAuthorNames() {
        BDDStyleMethod.PerformContainsCollection();
    }

    @Then("^I should see verify Get param$")
    public void iShouldSeeVerifyGetParam() {
        BDDStyleMethod.QueryParamsHasSize();
    }
}
