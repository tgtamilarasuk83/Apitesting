package Differentdatatrice;

import org.json.JSONObject;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import Differentdatatrice.Trainee;
import static io.restassured.RestAssured.*;

import java.io.File;

public class Datadriven {

    static String token = "SDET_TOKEN_12345";
    static String traineeId;

   
    @Test(priority = 1)
    void createTraineeUsingPOJO() {

    	Trainee trainee = new Trainee("Trainee11","trainee11@example.com","ABC Corp","1100000");

        Response res =
                given()
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .body(trainee)
                .when()
                        .post("http://localhost:6001/Trainees");

        res.then().log().all();

        Assert.assertEquals(res.statusCode(), 201);

        traineeId = res.jsonPath().getString("id");
        Assert.assertNotNull(traineeId);
    }

  
    @Test(priority = 2)
    void createTraineeUsingJSONObject() {

        JSONObject trainee = new JSONObject();

        trainee.put("name", "Trainee12");
        trainee.put("email", "trainee12@example.com");
        trainee.put("company", "Google");
        trainee.put("id", "120000000");

        Response res =
                given()
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .body(trainee.toString())
                .when()
                        .post("http://localhost:6001/Trainees");

        res.then().log().all();

        Assert.assertEquals(res.statusCode(), 201);

        traineeId = res.jsonPath().getString("id");
        Assert.assertNotNull(traineeId);
    }
    
    @Test(priority = 3)
    public void createTrainee() {

        File jsonFile = new File("src/test/resources/trainee.json");

        Response response =
                given()
                        .contentType("application/json")
                        .body(jsonFile)
                .when()
                        .post("http://localhost:6001/Trainees");

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 201);
    }
}