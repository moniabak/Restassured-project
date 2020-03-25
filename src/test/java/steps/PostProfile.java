package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import steps.BDDStyle.BDDStyleMethod;
import util.RestAssuredExtension;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PostProfile {
    private static ResponseOptions<Response> response;

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

    @Given("^I perform POST for \"([^\"]*)\"$")
    public void iPerformPOSTFor(String arg0) throws Throwable {
        BDDStyleMethod.PostWithParam();
    }
}
