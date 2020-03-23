package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import util.RestAssuredExtension;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;


public class GetPostSteps {
    private static ResponseOptions<Response> response;

    @Given("^I perform GET for \"([^\"]*)\"$")
    public void iPerformGETFor(String url) throws Throwable {
        response = RestAssuredExtension.GetOps(url);
    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) throws Throwable {
        assertThat(response.getBody().jsonPath().get("author"), hasItem("typicode"));
    }

    @Then("^I should see the author names$")
    public void iShouldSeeTheAuthorNames() {
        BDDStyleMethod.PerformContainsCollection();
    }

    @Then("^I should see verify Get param$")
    public void iShouldSeeVerifyGetParam() {
        BDDStyleMethod.QueryParamsHasSize();
    }

    @Given("^I perform POST for \"([^\"]*)\"$")
    public void iPerformPOSTFor(String arg0) throws Throwable {
        BDDStyleMethod.PostWithParam();
    }

    @Given("^I perform POST for \"([^\"]*)\" with body$")
    public void iPerformPOSTForWithBody(String url, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();
        //Set body
        HashMap<String, String> body = new HashMap<>();
        body.put("name", data.get(1).get(0));

        HashMap<String, String> params = new HashMap<>();
        params.put("profileNo", data.get(1).get(1));

        //Perform post operation
        response = RestAssuredExtension.PostOpsWithParams(url, params, body);
    }

    @Then("^I should see the body has name as \"([^\"]*)\"$")
    public void iShouldSeeTheBodyHasNameAs(String name) throws Throwable {
        assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
    }
}
