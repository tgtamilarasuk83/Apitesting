package RestAzure;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetUsers {

    static int id;

    @Order(1)
    @Test
    void getUsers() {

        given()

        .when()
            .get("https://jsonplaceholder.typicode.com/posts")

        .then()
            .statusCode(200)
            .body("[0].id", equalTo(1))
            .log().all();
    }

    @Order(2)
    @Test
    void createUser() {

        HashMap<String, String> data = new HashMap<>();

        data.put("name", "pavan");
        data.put("job", "trainer");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(data)

                .when()
                .post("https://jsonplaceholder.typicode.com/posts");

                 id = response.jsonPath().getInt("id");

        response.then()
                .statusCode(201)
                .body("name", equalTo("pavan"))
                .body("job", equalTo("trainer"))
                .log().all();

        System.out.println("Created ID : " + id);
        System.out.println("___________________________________________________________________________________");
    }
    
    
    
    @Order(4)
    @Test
    void SeeAddedUser() {

        Response response = given()

        .when()
            .get("https://jsonplaceholder.typicode.com/posts/" + id);

        System.out.println("___________________________________________________________________________________");

        response.then()
            .statusCode(404)      // JSONPlaceholder does NOT save new records
            .log().all();

        System.out.println("___________________________________________________________________________________");
    }

    @Order(4)
    @Test
    void updateUser() {

        HashMap<String, String> data = new HashMap<>();

        data.put("name", "Tamil");
        data.put("job", "developer");


        Response response = given()
                .contentType(ContentType.JSON)
                .body(data)

        .when()
                .put("https://jsonplaceholder.typicode.com/posts/" + id);

        response.then()
                .statusCode(200)
                .body("name", equalTo("Tamil"))
                .body("job", equalTo("developer"))
                .log().all();

        System.out.println("Updated ID : " + id);
        System.out.println("___________________________________________________________________________________");
    }
    
    
    @Order(5)
    @Test
    void deletePost() {

        System.out.println("Deleting ID : " + id);

        Response response = given()

        .when()
            .delete("https://jsonplaceholder.typicode.com/posts/" + id);

        response.then()
                .statusCode(200)
                .log().all();
    }
}