package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("page","1");
        qParam.put("per_page","20");
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/";
        response = given()
                .queryParams(qParam)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void test001() {
        List<Integer> allId = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All ids : " + allId);
        System.out.println("------------------End of Test---------------------------");

    }
    //2. Extract the all Names
    @Test
    public void test002() {
        List<Integer> allName = response.extract().path("name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All names : " + allName);
        System.out.println("------------------End of Test---------------------------");

    }
    //3. Extract the name of 5 th object
    @Test
    public void test003() {
        String name = response.extract().path("[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5 th object: " + name);
        System.out.println("------------------End of Test---------------------------");

    }
    //4. Extract the names of all object whose status = inactive

    @Test
    public void test004() {
        List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the names of all object whose status = inactive: " + names);
        System.out.println("------------------End of Test---------------------------");

    }
    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the gender of all the object whose status = active: " + gender);
        System.out.println("------------------End of Test---------------------------");

    }
    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> nameS = response.extract().path("findAll{it.gender == 'female'}.gender");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the names of the object whose gender = female: " + nameS);
        System.out.println("------------------End of Test---------------------------");

    }
    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<String> allEmail = response.extract().path("findAll{it.status == 'inactive'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the emails of the object where status = inactive " + allEmail.size());
        System.out.println("------------------End of Test---------------------------");

    }
    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<String> ids = response.extract().path("findAll{it.gender == 'male'}.id");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the ids of the object where gender = male: " + ids);
        System.out.println("------------------End of Test---------------------------");

    }
    //9. Get all the status
    @Test
    public void test009() {
        List<String> statusALL = response.extract().path("status");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all the status: " + statusALL);
        System.out.println("------------------End of Test---------------------------");

    }
    //10. Get email of the object where name = Saraswati Dhawan
    @Test
    public void test010() {
        List<String> email = response.extract().path("findAll{it.name == 'Saraswati Dhawan'}.email");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email of the object where name = Saraswati Dhawan: " + email);
        System.out.println("------------------End of Test---------------------------");

    }
    //11. Get gender of id = 4040687
    @Test
    public void test011() {
        List<String> genderOfID = response.extract().path("findAll{it.id ==  4040702}.gender");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("gender of id = 4040687: " + genderOfID);
        System.out.println("------------------End of Test---------------------------");

    }
}
