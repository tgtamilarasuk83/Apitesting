package Testapi;

import org.testng.annotations.Test;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class CreateuserTest {

    @Test
    public void createUserTes1t() {

        Map<String, Object> payload = new HashMap<>();

        payload.put("title", "tamilarasu");
        payload.put("body", "learning");
        payload.put("userId", 1);

        System.out.println(payload);

        Response response =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post("https://jsonplaceholder.typicode.com/posts");

      
        System.out.println("Status Code: " + response.getStatusCode());
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 201, "Status code mismat");
    }
}