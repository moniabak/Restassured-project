package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.Address;
import pojo.Location;
import pojo.Posts;
import util.RestAssuredExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class GetPostSteps {
    private static ResponseOptions<Response> response;

    @Given("^I perform GET for \"([^\"]*)\"$")
    public void iPerformGETFor(String url) throws Throwable {
        response = RestAssuredExtension.GetOpsWithToken(url, response.getBody().jsonPath().get("access_token"));
    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) throws Throwable {
        Posts posts = response.getBody().as(Posts.class);
        assertThat(posts.getAuthor(), equalTo(authorName));
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

    @Given("^I perform GET for address \"([^\"]*)\"$")
    public void iPerformGETForAddress(String url, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

        Map<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));

        response = RestAssuredExtension.GetWithPathParams(url, body);

    }

    @Then("^I should see the street name as \"([^\"]*)\" for the \"([^\"]*)\" address$")
    public void iShouldSeeTheStreetNameAsForTheAddress(String streetName, String type) throws Throwable {
        Location[] location = response.getBody().as(Location[].class);

        Address address = location[0].getAddress().stream().filter(x->x.getType().equalsIgnoreCase(type)).findFirst().orElse(null);

        assertThat(address.getStreet(), equalTo(streetName));
    }

    @Given("^I perform authentication operation for \"([^\"]*)\" with body$")
    public void iPerformAuthenticationOperationForWithBody(String url, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

        HashMap<String, String> body = new HashMap<>();
        body.put("email", data.get(1).get(0));
        body.put("password", data.get(1).get(1));

        response = RestAssuredExtension.PostOpsWithBody(url, body);
    }

    @Then("^I should see the author name as \"([^\"]*)\" with json validation$")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String arg0) throws Throwable {
        String responseBody = response.getBody().asString();

        assertThat(responseBody, matchesJsonSchemaInClasspath("post.json"));
    }
}
