package org.example.apis.config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.utils.ConfigReader;

public class RequestSpecFactory {

    private static final RequestSpecification baseSpec;
    private static final ResponseSpecification baseResponseSpec;

    // initialize once -> static
    static {
        baseSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUrl())
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .setRelaxedHTTPSValidation()
                .build()
                .log().ifValidationFails();

        baseResponseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

    // base spec in case of no auth
    public static RequestSpecification getBaseSpec() {
        return baseSpec;
    }

    // authenticated spec using bearer
    public static RequestSpecification getAuthSpec(String token) {
        return new RequestSpecBuilder()
                .addRequestSpecification(baseSpec)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    public static ResponseSpecification getResponseSpec() {
        return baseResponseSpec;
    }
}
