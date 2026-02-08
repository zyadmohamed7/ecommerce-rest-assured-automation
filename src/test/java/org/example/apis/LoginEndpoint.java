package org.example.apis;

import io.restassured.response.Response;
import org.example.pojos.UserCredentialsPojo;

import static io.restassured.RestAssured.given;

public class LoginEndpoint {
    private static final String LOGIN_ENDPOINT = "/login";
    public Response login(UserCredentialsPojo userCredentialsPojo) {

        return given().spec(BaseApis.getRequestSpecification())
                .body(userCredentialsPojo)
                .when()
                .post(LOGIN_ENDPOINT);
    }
}
