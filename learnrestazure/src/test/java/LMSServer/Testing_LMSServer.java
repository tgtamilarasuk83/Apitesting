package LMSServer;

import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Then;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import LMSServer.Resources.*;
import LMSServer.Ulility.Propertyfilereader;
public class Testing_LMSServer {
	
	
	Propertyfilereader reader;

	@BeforeClass
	public void setup() throws IOException {
	    reader = new Propertyfilereader();
	}
	
	
	
	@Test(priority = 1)
	public void healthcheck() {

	   Response res =  given()
	    .when()
	        .get(EnvironmentSetup.baseurl);
	    res.then()
	        .log().headers()
	        .statusCode(200)
	        .header("access-control-allow-credentials", "true")
	        .header("content-type", "text/html; charset=utf-8");   
	    System.out.println(res.header("content-length"));
	}
	
	
	



	@Test(priority = 2)
    public void test_Login() throws IOException {
    	

    	Response response = given()
    	              .contentType("application/json")
    	              .body( DataResources.lOGINdata)
    	.when()
    	        .post(EnvironmentSetup.baseurl + "/user/login")
    	.then()
    	        .statusCode(201)
//    	        .log().all()
    	        .extract()
    	        .response();
    	
//    	METHOD1
    	
//    	String setCookie = response.getHeader("Set-Cookie");
    	
//    	String token = setCookie.substring(
//    	        setCookie.indexOf("token=") + 6,
//    	        setCookie.indexOf(";")
//    	);    	
//    	System.out.println("________________________________________________________-");
//    	System.out.println(token);
    	
    	
    	String token2 = response.jsonPath().getString("token");
    	System.out.println("________________________________________________________-");
//    	System.out.println("Token: " + token2);
//    	EnvironmentSetup.Authorization_Token = token2;
    	
    	// Update properties file
    
    	reader.setToken(token2);
    	
//    	#3r method of get token from the cookies
    	Map<String,String> Cookie = response.getCookies();
    	System.out.println(Cookie);
    	
    	
    	
    	
    }
    
    

   
    
    
    @Test(priority = 3)
    public void Getallinstitutions() {

        // A 415 Unsupported Media Type error often occurs because the API expects JSON data,
        // and you must specify the request content type

        Response response = given()
                .contentType("application/json")
        .when()
                .get(EnvironmentSetup.baseurl + "/getAll/institution");
                System.out.println(EnvironmentSetup.baseurl + "/getAll/institution");

        response.then()
                .statusCode(200)
                .log().all();
        		
        System.out.println("________________________________________________________-");
        List<String> institutions =response.jsonPath().getList("getAllInstitution.inst_name");
        System.out.println(response.getStatusCode());
        System.out.println(institutions);
        System.out.println("________________________________________________________-");
      
    }
    
    
    @Test(priority = 4)
    public void Getallroles() {

        System.out.println("Token: " + reader.getToken());

        Response response = given()
                .header("Authorization", "Bearer " + reader.getToken())
                .accept("application/json")
        .when()
//        		 post not working
                .get(EnvironmentSetup.baseurl + "/roles/getAll");

        response.then()
        		
                .statusCode(200)
                .log().all();
        List<String> Roles =response.jsonPath().getList("roles.originalRole");
        List<String> Roles2 =response.jsonPath().getList("roles.roleValue");
        System.out.println(Roles);
        System.out.println(Roles2);
        
    }
    
    	@Test (priority = 5)
    	void  Getallcoursestructures(){

            Response response = given()
                    .header("Authorization", "Bearer " + reader.getToken())
                    .accept("application/json")
            .when()
//            		post not working
                    .get(EnvironmentSetup.baseurl + "/courses-structure/getAll");

            		response.then()
            		.statusCode(200)
            		.log().all();
           System.out.println(response.jsonPath().getString("message.value"));
    		
    	}
    	
//    	Part B — Notes API (full CRUD)
    	
    	@Test (priority = 6)
    	void  Createnote() {
    		
    		 File data = new File("src/test/resources/LMs/createnote.json");
    		
    		Response response = given()
                    .header("Authorization", "Bearer " + reader.getToken())
                    .accept("application/json") //“I want the response in JSON format”
                    .contentType("application/json") //“I am sending JSON data to the server”
                    .body(data)
            .when()
//            		post not working
                    .post(EnvironmentSetup.baseurl + "/create/notes");    				
    		response.then()
            .statusCode(201)
            .body("success", equalTo(true))
            .body("message", equalTo("Note created successfully"))
            .body("data.title", equalTo("Daily Standup Notes"));
           System.out.println( response.jsonPath().getString("data.title")); 
           System.out.println(response.jsonPath().getString("data.title"));
    		
    	}
    	
    	@Test(priority = 7)
    	void Getallnotes() { 
//    		A query parameter is extra information you add to a URL to filter, search, or control data.
    		Response response = given()
    		        .header("Authorization", "Bearer " + reader.getToken())
    		        .pathParam("PATH1","getAll")
    		        .pathParam("PATH2","notes")  // using path params	
    		        .accept("application/json")
    		        .queryParam("page", 1)
    		        .queryParam("limit", 50)
    		        .queryParam("search", "Daily")
    		        .queryParam("isPinned", true)
    		        .queryParam("sortBy", "lastEdited")
    		        .queryParam("sortOrder", "desc")
    		.when()
    		  		.get(EnvironmentSetup.baseurl + "/{PATH1}/{PATH2}");
    		
    		response.then()
    			.statusCode(200);
    		
    		System.out.println(response.prettyPrint());
    		
    	}
    	
    	
 }
