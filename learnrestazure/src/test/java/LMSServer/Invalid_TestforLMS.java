package LMSServer;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Then;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import LMSServer.Resources.*;
import LMSServer.Ulility.Propertyfilereader;
public class Invalid_TestforLMS {
	
	
	Propertyfilereader reader;

	@BeforeClass
	public void setup() throws IOException {
	    reader = new Propertyfilereader();
	}
	
	 @Test()
	    public void test_invalidLogin() throws IOException {
	    	

	    	Response response = given()
	    	              .contentType("application/json")
	    	              .body( DataResources.lOGINdata)
	    	.when()
	    	        .post(EnvironmentSetup.baseurl + "/user/login")
	    	.then()
	    	        .statusCode(400)
	    	        .extract()
	    	        .response(); 	
	    }
	 
	 @Test()
	    public void Getallinstitutions() {

	        // A 415 Unsupported Media Type error often occurs because the API expects JSON data,
	        // and you must specify the request content type

	        Response response = given()
//	                .contentType("application/json")
	        .when()
	                .get(EnvironmentSetup.baseurl + "/getAll/institut/");
	                System.out.println(EnvironmentSetup.baseurl + "/getAll/institution");

	        response.then()
	                .statusCode(404);
	                
	      
	      
	    }
	 
	 
	 @Test(priority = 4)
	    public void Getallroles() {

	        System.out.println("Token: " + reader.getToken());

	        Response response = given()
	               // .header("Authorization", "Bearer " + reader.getToken())
	                .accept("application/json")
	        .when()
//	        		 post not working
	                .get(EnvironmentSetup.baseurl + "/roles/getAll");

	        response.then()
	        		
	                .statusCode(401)
	                .log().all();
	        List<String> Roles =response.jsonPath().getList("roles.originalRole");
	        List<String> Roles2 =response.jsonPath().getList("roles.roleValue");
	        System.out.println(Roles);
	        System.out.println(Roles2);
	        
	    }
	 
	 
	 @Test (priority = 5)
 	void  Getallcoursestructures(){

         Response response = given()
                // .header("Authorization", "Bearer " + reader.getToken())
                 .accept("application/json")
         .when()
//         		post not working
                 .get(EnvironmentSetup.baseurl + "/courses-structure/getAll");

         		response.then()
         		.statusCode(401)
         		.log().all();
        System.out.println(response.jsonPath().getString("message.value"));
 		
 	}
	 
	 
	 @Test (priority = 6)
 	void  Createnote() {
 		
 		 File data = new File("src/test/resources/LMs/createnote.json");
 		
 		Response response = given()
                 .header("Authorization", "Bearer " + reader.getToken())
//                 .accept("application/json") //“I want the response in JSON format”
//                 .contentType("application/json") //“I am sending JSON data to the server”
                 .body(data)
         .when()
//         		post not working
                 .post(EnvironmentSetup.baseurl + "/create/notes");    				
 		response.then()
         .body("data.title", equalTo("Untitled Note"));
        System.out.println( response.jsonPath().getString("data.title")); 
        System.out.println(response.jsonPath().getString("data.title"));
 		
 	}
	 
	 
	

}
