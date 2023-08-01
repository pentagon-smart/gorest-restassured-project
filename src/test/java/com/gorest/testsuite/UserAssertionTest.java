package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
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

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
       response.body("size",equalTo(20));
    }
    //2. Verify the if the name of id = 4040695 is equal to ”Jagadish Ahuja”
    @Test
    public void test002() {
        response.body("findAll{it.id == 4104810}", hasItem(hasEntry("name","Jagadish Ahuja")));
    }

    //3. Check the single ‘Name’ in the Array list ("Bhasvan Kapoor")
    @Test
    public void test003() {
        response.body("name", hasItem("Bhasvan Kapoor"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList ("Bhasvan Kapoor", "Chaturbhuj Reddy", "Fr. Preity Guneta")
    @Test
    public void test004() {
        response.body("name", hasItems("Hiranmay Prajapat", "Chandrakin Shukla I", "Chakrika Marar"));
    }
    //5. Verify the email of userid = 4040702 is equal “tarun_rana@lynch-oconner.example”
    @Test
    public void test005() {
        response.body("findAll{it.id == '4040690'}", hasItem(hasEntry("email", "saraswati_dhawan@larkin.example")));

    }
    //6. Verify the status is “Active” of user name is “Chaturbhuj Reddy”
    @Test
    public void test006() {
        response.body("findAll{it.status == 'active'}", hasItem(hasEntry("name", "Hiranmay Prajapat")));

    }
    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007() {
        response.body("findAll{it.gender == 'male'}", hasItem(hasEntry("name", "Bhima Chaturvedi")));

    }
}
