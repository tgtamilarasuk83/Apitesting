package Testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

public class getUserTests {

    @Test
    public void getUsers() {

        Response response = RestAssured.given().when().get("https://jsonplaceholder.typicode.com/user/1"); 
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        response.prettyPrint();
        
        String name =  response.jsonPath().getString(null);
        Assert.assertEquals(name, "Lenannne gram");
               

        
    }
}