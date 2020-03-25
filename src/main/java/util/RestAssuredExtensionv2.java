package util;

import cucumber.api.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredExtensionv2 {
    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String method;
    private String url;

    /**
     * RestAssuredExtensionv2 constructor to pass the initial settings for the following methods
     * @param uri
     * @param method
     * @param token
     */
    public RestAssuredExtensionv2(String uri, String method, String token) {
        //Formulate the API url
        this.url = "http://localhost:3000" + uri;
        this.method = method;
        if (token != null) {
            builder.addHeader("Authorization", "Bearer " + token);
        }
    }

    /**
     * ExecuteAPI to execute the API for GET/POST/DELETE method
     * @return ResponseOption<Response>
     */
    private ResponseOptions<Response> ExecuteAPI() {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);

        if(this.method.equalsIgnoreCase(APIConstant.ApiMethods.POST)){
            return request.post(this.url);
        }else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET)){
            return request.get(this.url);
        }if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE)){
            return request.delete(this.url);
        }
        return null;
    }

    /**
     * Authenticate to get the token variable
     * @param body
     * @return string token
     */
    public String Authenticate(Object body) {
        builder.setBody(body);
        return ExecuteAPI().getBody().jsonPath().get("access_token");
    }

    /**
     * Executing API with query params being passed as the input of it
     * @param queryPath
     * @return Response
     */
    public ResponseOptions<Response> ExecuteWithQueryParams(Map<String, String> queryPath){
        builder.addQueryParams(queryPath);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteWithPathParams(Map<String, String> pathParam){
        builder.addPathParams(pathParam);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteWithPathParamsAndBody(Map<String, String> body, Map<String, String> params){
        builder.setBody(body);
        builder.addPathParams(params);
        return ExecuteAPI();
    }
}
