package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import util.RestAssuredExtension;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class DELETEPostSteps {
    private ResponseOptions<Response> response;

    @Given("^I ensure to POST for \"([^\"]*)\" with body as$")
    public void iEnsureToPOSTForWithBodyAs(String url, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

        Map<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        RestAssuredExtension.PostOpsWithBody(url, body);
    }

    @And("^I Perform DELETE operation for \"([^\"]*)\"$")
    public void iPerformDELETEOperationFor(String url, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

        Map<String, String> params = new HashMap<>();
        params.put("postid", data.get(1).get(0));

        RestAssuredExtension.DeleteWithParam(url, params);

    }

    @Then("^I \"([^\"]*)\" see the body with title as \"([^\"]*)\"$")
    public void iShouldNotSeeTheBodyWithTitleAs(String condition, String title) throws Throwable {
        if (condition.equalsIgnoreCase("should not")) {
            assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
        } else {
            assertThat(response.getBody().jsonPath().get("title"), Is.is(title));
        }
    }

    @And("^I perform GET op for \"([^\"]*)\"$")
    public void iPerformGETOpFor(String url, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

        Map<String, String> params = new HashMap<>();
        params.put("postid", data.get(1).get(0));

        response = RestAssuredExtension.GetWithPathParams(url, params);
    }
}
