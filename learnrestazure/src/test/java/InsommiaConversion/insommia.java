package InsommiaConversion;

import io.restassured.response.Response;


import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class insommia {

    static String token = "SDET_TOKEN_12345";
    static String traineeId;

   
    @Test(priority = 2)
    void createTrainee() {

        Response res =
            given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{\"name\":\"Trainee1\",\"company\":\"Google\"}")
            .when()
                .post("http://localhost:6000/Trainees");

        Assert.assertEquals(res.statusCode(), 201);

        traineeId = res.jsonPath().getString("id");
        Assert.assertNotNull(traineeId);
    }

    @Test(priority = 3)
    void getTrainee() {

        Response res =
            when()
                .get("http://localhost:6000/Trainees/1");

        Assert.assertEquals(res.statusCode(), 200);

        String id = res.jsonPath().getString("id");
        String name = res.jsonPath().getString("name");

        Assert.assertEquals(id, "1");
        Assert.assertNotNull(name);
    }

  
    @Test(priority = 4)
    void validationTest() {

        Response res =
            when()
                .get("http://localhost:6000/Trainees/1");

        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("id"), "1");
        Assert.assertTrue(res.jsonPath().getString("company").contains("Google"));
    }

  
    @Test(priority = 5)
    void negativeTest() {

        Response res =
            when()
                .get("http://localhost:6000/Trainees/999");

        Assert.assertNotEquals(res.statusCode(), 200);
        Assert.assertNull(res.jsonPath().getString("name"));
    }

    // ================= DELETE =================
    @Test(priority = 6)
    void deleteTest() {

        Response res =
            when()
                .delete("http://localhost:6000/Trainees/1");

        Assert.assertEquals(res.statusCode(), 200);
    }

    // ================= OPTIONS + CORS =================
    @Test(priority = 7)
    void optionsCorsTest() {

        Response res =
            when()
                .options("http://localhost:6000/Trainees");

        Assert.assertEquals(res.statusCode(), 204);

        String origin = res.getHeader("Access-Control-Allow-Origin");
        String methods = res.getHeader("Access-Control-Allow-Methods");
        String headers = res.getHeader("Access-Control-Allow-Headers");

        Assert.assertEquals(origin, "*");
        Assert.assertTrue(methods.contains("GET"));
        Assert.assertTrue(headers.toLowerCase().contains("content-type"));
    }
}