package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
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

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size",equalTo(25));
    }
    //2. Verify the if the title of id = 57251 is equal to ”Capio cornu arma baiulus vito.”
    @Test
    public void test002() {
        response.body("find{it.id == 57251}.title", equalTo("Capio cornu arma baiulus vito."));
    }
    //3. Check the single user_id in the Array list (4040679)
    @Test
    public void test003() {
        response.body("user_id", hasItem(4040679));
    }
    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("user_id", hasItems(4040679,4040713,4040678));
    }
    /*5. Verify the body of userid = 4040692 is equal Tamen creptio ulterius. Pecus spes tamdiu. Calcar cruentus error. Auctus tubineus adduco. Denuncio decipio comitatus. Admitto celer degero. Fugit facilis defero. Torqueo asper tamen. Argentum deprecator theca. Ait celebrer ab. Doloremque arma dolore. Animi ut aveho. Voluptatem speciosus aut."
     */
    @Test
    public void test005() {
        response.body("[8].user_id", hasKey("body"));

    }
}
