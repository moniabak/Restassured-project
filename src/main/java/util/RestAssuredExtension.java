package util;

import cucumber.api.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import javafx.util.Builder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class RestAssuredExtension {
    public static RequestSpecification request;

    public RestAssuredExtension() {
        //Arrange
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000");
        builder.setContentType(ContentType.JSON);
        RequestSpecification requestBuild = builder.build();
        request = RestAssured.given().spec(requestBuild);
    }

    public static void GetOpsWithParam(String url, Map<String, String> params) {
        //Act
        request.pathParams(params);
        try {
            request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static ResponseOptions<Response> GetOps(String url) {
        //Act
        try {
            return request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseOptions<Response> PostOpsWithParams(String url, Map<String, String> params, Map<String, String> body){

        request.pathParams(params);
        request.body(body);
        return request.post(url);
    }

    public static ResponseOptions<Response> PostOpsWithBody(String url, Map<String, String> body){
        request.body(body);
        return request.post(url);
    }

    public static ResponseOptions<Response> DeleteWithParam(String url, Map<String, String> param){
        request.pathParams(param);
        return request.delete(url);
    }

    public static ResponseOptions<Response> GetWithPathParams(String url, Map<String, String> params){
        request.pathParams(params);
        return request.get(url);
    }

    public static ResponseOptions<Response> PutOpsWithBodyAndParams(String url, Map<String, String> body, Map<String, String> params) {
        request.pathParams(params);
        request.body(body);
        return request.put(url);
    }
}
