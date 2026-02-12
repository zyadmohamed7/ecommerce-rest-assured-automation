package org.example.apis;

import io.restassured.response.Response;
import org.example.apis.config.RequestSpecFactory;
import org.example.pojos.CreateItemPojo;

import static io.restassured.RestAssured.given;

public class ItemsEndpoint {

    private final String token;
    private static final String ITEMS_ENDPOINT = "/items";

    public ItemsEndpoint(String token) {
        this.token = token;
    }

    public Response createitems(CreateItemPojo payload) {
        return given()
                .spec(RequestSpecFactory.getAuthSpec(token))
                .body(payload)
                .when()
                .post(ITEMS_ENDPOINT);
    }

    public Response getitems(String id) {
        return given()
                .spec(RequestSpecFactory.getAuthSpec(token))
                .when()
                .get(ITEMS_ENDPOINT + "/" + id);
    }

    public Response updateitems(String id, CreateItemPojo payload) {
        return given()
                .spec(RequestSpecFactory.getAuthSpec(token))
                .body(payload)
                .when()
                .put(ITEMS_ENDPOINT + "/" + id);
    }

    public Response deleteitems(String id) {
        return given()
                .spec(RequestSpecFactory.getAuthSpec(token))
                .when()
                .delete(ITEMS_ENDPOINT + "/" + id);
    }
}
