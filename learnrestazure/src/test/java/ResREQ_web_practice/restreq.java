package ResREQ_web_practice;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class restreq {
	
		int id;
	 
	    @Test
	    void ListUsers() {

	        given()
	        .header("x-api-key", "reqres_1319ebc914e040e4b5476d0cd90952e7")

	        .when()
	            .get("https://reqres.in/api/users?page=2")

	        .then()
	            .statusCode(200)
	            .body("page", equalTo(2))
	            .log().all();
	    }
	    
	    
	    @Test
	    void AddUser() {
	    	
	    	 HashMap<String, String> data = new HashMap<>();
	    	 data.put("email", "Tamil.in");
	    	 data.put("first_name", "Tamil");
	    	 data.put("last_name", "arasu");
	    	 data.put("avatar", "https://reqres.in/img/facesk/7-image.jpg");
	         data.put("name", "John");
	         data.put("job", "QA Engineer");
	         
	    	given()
	        .header("x-api-key", "reqres_1319ebc914e040e4b5476d0cd90952e7")
            .contentType(ContentType.JSON)
            .body(data)

	        .when()
	            .post("https://reqres.in/api/users?page=2")
	            

	        .then()
	            .statusCode(201)
	            .body("name", equalTo("John"))
	            .body("job", equalTo("QA Engineer"))
	            .log().all()
	    	    .body("name", equalTo("John"));
	    	
	    }
}
