package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("page","1");
        qParam.put("per_page","25");
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/";
        response = given()
                .queryParams(qParam)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test001() {
        List<String> allTitle = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All titles : " + allTitle);
        System.out.println("------------------End of Test---------------------------");

    }
    //2. Extract the total number of record
    @Test
    public void test002() {
        List<Integer> totalRecord = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total records : " + totalRecord);
        System.out.println("------------------End of Test---------------------------");

    }
    //3. Extract the body of 15 th record
    @Test
    public void test003() {
        List<String> bodyOfRecord = response.extract().path("[14].body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  body of 15 th record : " + bodyOfRecord);
        System.out.println("------------------End of Test---------------------------");

    }
    //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> allOfRecord = response.extract().path("user_id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  user_id of all the records : " + allOfRecord   );
        System.out.println("------------------End of Test---------------------------");

    }
    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<String> titleOfAllRecord = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records : " + titleOfAllRecord   );
        System.out.println("------------------End of Test---------------------------");

    }
    //6. Extract the title of all records whose user_id = 4040679
    @Test
    public void test006() {
        List<HashMap<String, ?>> titleOfRecord = response.extract().path("findAll{it.user_id == 4040679}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 4040679: " + titleOfRecord   );
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Extract the body of all records whose id = 56935
    @Test
    public void test007() {
        List<HashMap<String, ?>> bodyOfRecord = response.extract().path("findAll{it.id == 56935}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  body of all records whose id = 56935: " + bodyOfRecord   );
        System.out.println("------------------End of Test---------------------------");
    }
}
