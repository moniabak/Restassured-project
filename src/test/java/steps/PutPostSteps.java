package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import util.RestAssuredExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PutPostSteps {
    private ResponseOptions<Response> response;

    @And("^I Perform PUT operation for \"([^\"]*)\"$")
    public void iPerformPUTOperationFor(String url, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

        Map<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        Map<String, String> params = new HashMap<>();
        params.put("postid", data.get(1).get(0));

        RestAssuredExtension.PutOpsWithBodyAndParams(url, body, params);
    }
}
