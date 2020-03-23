package steps;

import io.restassured.http.ContentType;
import org.hamcrest.core.Is;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BDDStyleMethod {
    public static void SimpleGetPost(String postNumber) {
        given().contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/posts/" + postNumber)
                .then()
                .body("author", is("typicode"));
    }

    public static void PerformContainsCollection() {
        given().contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/posts")
                .then()
                .body("author", containsInAnyOrder("typicode", "Piotr")).statusCode(200);
    }

    public static void PathParamsContainsAuthor() {
        given().contentType(ContentType.JSON)
                .when()
                .pathParams("post", "1")
                .get("http://localhost:3000/posts/{post}")
                .then()
                .body("author", containsString("typicode"));
    }

    public static void QueryParamsHasSize() {
        given()
                .queryParam("_page", "1").queryParam("_limit", "2")
                .when()
                .get("http://localhost:3000/posts")
                .then()
                .body("author", hasItem("typicode"));
//                .body("author", hasSize(2));
    }

    public static void PostWithParam() {
        HashMap<String, String> postContent = new HashMap<>();
        postContent.put("id", "3");
        postContent.put("title", "Cake Java book");
        postContent.put("author", "Monika Bak");

        given()
                .contentType(ContentType.JSON)
                .with()
                .body(postContent)
                .when()
                .post("http://localhost:3000/posts")
                .then()
                .body("author", Is.is("Monika Bak"));
    }

    public void BodyParamsAuth() {
        HashMap<String, String> authentication = new HashMap<String, String>();
        authentication.put("email", "prashanth@gmail.com");
        authentication.put("password", "pass213");

        given()
                .contentType(ContentType.JSON)
                .with()
                .body(authentication)
                .when()
                .post("http://localhost:3000/auth/login")
                .then()
                .body("access_token", notNullValue());
    }

}
