package com.lms.Employee.Controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @BeforeAll
    public static void setup() {
        // Specify the base URL to the RESTful web service
        String temp  = "http://localhost:8080/api/";
//        String temp = RestAssured.baseURI;
    }

    @Test
    public void testCreateEmployee() {
        String requestBody = """
                {
                     "employeeName" : "Kartikey",
                     "email" : "jfjxa@test.com",
                     "password" : "ppaa",
                     "role" : "joiner"
                 }""";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/create")
                .then()
                .statusCode(201)
                .extract().response();

        String status = response.jsonPath().getString("statusCode");
        String message = response.jsonPath().getString("message");

        assertEquals("201", status);
        assertEquals("Created successfully", message);
    }

//    @Test
//    void createUser()
//    {
//        HashMap<String, Object> data = new HashMap<>();
//
//        data.put("employeeName" , "Kartikey");
//        data.put("email" , "jfjxa@test.com");
//        data.put("password" , "jfjxa");
//        data.put("role" , "joiner");
//
//
////                     "email" : "jfjxa@test.com",
////                     "password" : "ppaa",
////                     "role" : "joiner"
////)
//
//        given()
//                .contentType("application/json")
//                .body(data)
//                .when()
//                .post("http://localhost:8090/api/create")
//                .then()
//                .statusCode(201)
//                .log().all();
//    }
}
