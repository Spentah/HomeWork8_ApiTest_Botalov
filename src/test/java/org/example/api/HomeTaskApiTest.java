package org.example.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.example.model.Order;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

import static io.restassured.RestAssured.given;


public class HomeTaskApiTest {

    @BeforeClass
    public void setUp() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("my.properties"));
        RestAssured.requestSpecification = new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2/")
                .addHeader("api_key", System.getProperty("api.key"))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void postAndCheckOrder() {
        Order order = new Order();
        int id = new Random().nextInt(10);
        order.setId(id);
        given().body(order)
                .when().post("/store/order")
                .then().statusCode(200);
        Order actual = given().pathParam("orderId", id)
                .when().get("/store/order/{orderId}")
                .then().statusCode(200).extract().body().as(Order.class);
        Assert.assertEquals(actual.getId(), order.getId());

        System.setProperty("orderId", id + "");

        given().pathParam("orderId", System.getProperty("orderId"))
                .when().delete("/store/order/{orderId}")
                .then().statusCode(200);
        given().pathParam("orderId", System.getProperty("orderId"))
                .when().get("/store/order/{orderId}")
                .then().statusCode(404);
    }

}
