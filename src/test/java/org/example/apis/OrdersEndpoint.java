package org.example.apis;

import io.restassured.response.Response;
import org.example.apis.config.RequestSpecFactory;
import org.example.pojos.CreateOrderPojo;

import static io.restassured.RestAssured.given;

public class OrdersEndpoint {

    private final String token;
    private static final String ORDERS_ENDPOINT = "/orders";

    public OrdersEndpoint(String token) {
        this.token = token;
    }

    public Response createOrder(CreateOrderPojo payload) {
        return given()
                .spec(RequestSpecFactory.getAuthSpec(token))
                .body(payload)
                .when()
                .post(ORDERS_ENDPOINT);
    }

    public Response getOrder() {
        return given()
                .spec(RequestSpecFactory.getAuthSpec(token))
                .when()
                .get(ORDERS_ENDPOINT);
    }

    public Response getCheckout(String id) {
        return given()
                .spec(RequestSpecFactory.getAuthSpec(token))
                .when()
                .post(ORDERS_ENDPOINT + "/" + id + "/checkout");
    }

    public Response getPaidItems() {
        return given()
                .spec(RequestSpecFactory.getAuthSpec(token))
                .when()
                .get(ORDERS_ENDPOINT + "/paid");
    }

    public Response deleteorder(String id) {
        return given()
                .spec(RequestSpecFactory.getAuthSpec(token))
                .when()
                .delete(ORDERS_ENDPOINT + "/" + id);
    }
}
