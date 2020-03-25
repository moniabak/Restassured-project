package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo_withoutbuilder.Posts;
import steps.BDDStyle.BDDStyleMethod;
import util.RestAssuredExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class GetPostSteps {
    private static ResponseOptions<Response> response;

    @Given("^I perform GET for \"([^\"]*)\"$")
    public void iPerformGETFor(String url) throws Throwable {
        response = RestAssuredExtension.GetOps(url);
    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) throws Throwable {
        Posts post = response.getBody().as(Posts.class);
        assertThat(post.getAuthor(), equalTo(authorName));
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
