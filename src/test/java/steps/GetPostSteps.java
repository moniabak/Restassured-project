package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.Address;
import pojo.Location;
import pojo.Login;
import pojo.Posts;
import util.APIConstant;
import util.RestAssuredExtension;
import util.RestAssuredExtensionv2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class GetPostSteps {
    private static ResponseOptions<Response> response;
    private static String token;

    @Given("^I perform GET for \"([^\"]*)\"$")
    public void iPerformGETFor(String url) throws Throwable {
        response = RestAssuredExtension.GetOpsWithToken(url, response.getBody().jsonPath().get("access_token"));
    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) throws Throwable {

        //With builder pattern
        Posts posts = new Posts.Builder().build();
        Posts post = response.getBody().as(posts.getClass());

        //without builder pattern
//        Posts posts = response.getBody().as(Posts.class);
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

    @Given("^I perform POST for \"([^\"]*)\"$")
    public void iPerformPOSTFor(String arg0) throws Throwable {
        BDDStyleMethod.PostWithParam();
    }


    @Given("^I perform authentication operation for \"([^\"]*)\" with body$")
    public void iPerformAuthenticationOperationForWithBody(String uri, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

//        HashMap<String, String> body = new HashMap<>();
//        body.put("email", data.get(1).get(0));
//        body.put("password", data.get(1).get(1));

        Login login = new Login();
        login.setEmail(data.get(1).get(0));
        login.setPassword(data.get(1).get(1));

        RestAssuredExtensionv2 restAssuredExtensionv2 = new RestAssuredExtensionv2(uri, APIConstant.ApiMethods.POST, null);
        token = restAssuredExtensionv2.Authenticate(login);
    }

    @And("^I perform GET for address \"([^\"]*)\"$")
    public void iPerformGETForAddress(String uri, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("id", data.get(1).get(0));

        RestAssuredExtensionv2 restAssuredExtensionv2 = new RestAssuredExtensionv2(uri, "GET", token);
        response = restAssuredExtensionv2.ExecuteWithQueryParams(queryParams);
    }

    @Then("^I should see the street name as \"([^\"]*)\" for the \"([^\"]*)\" address$")
    public void iShouldSeeTheStreetNameAsForTheAddress(String streetName, String type) throws Throwable {
        Location[] location = response.getBody().as(Location[].class);

        Address address = location[0].getAddress().stream().filter(x->x.getType().equalsIgnoreCase(type)).findFirst().orElse(null);

        assertThat(address.getStreet(), equalTo(streetName));
    }

    @Then("^I should see the author name as \"([^\"]*)\" with json validation$")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String arg0) throws Throwable {
        String responseBody = response.getBody().asString();

        assertThat(responseBody, matchesJsonSchemaInClasspath("post.json"));
    }
}
