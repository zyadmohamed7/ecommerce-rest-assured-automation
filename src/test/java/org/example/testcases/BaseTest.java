package org.example.testcases;

import com.fasterxml.jackson.core.type.TypeReference;
import io.qameta.allure.Step;
import org.example.apis.LoginEndpoint;
import org.example.pojos.AdminToken;
import org.example.pojos.CustomerToken;
import org.example.pojos.UserCredentialsPojo;
import org.example.utils.JsonUtils;
import org.example.utils.SharedTestData;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    @Step("Global Setup: Login and generate tokens for Admin and Customer users")
    public void globalSetup() throws IOException {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║   🚀 GLOBAL SETUP - PERFORMING LOGIN            ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        performLogin();
    }

    private void performLogin() throws IOException {
        LoginEndpoint loginRequest = new LoginEndpoint();

        // read users from the users.json file
        List<UserCredentialsPojo> userCredentialsPojoList = JsonUtils.readJsonFile("users.json",
                new TypeReference<List<UserCredentialsPojo>>() {});

        // admin token
        UserCredentialsPojo adminUser = userCredentialsPojoList.get(0);
        AdminToken adminTokenData = loginRequest.login(adminUser)
                .then()
                .assertThat()
                .statusCode(200)
                .body("token", notNullValue())
                .body("token", not(isEmptyString()))
                .extract()
                .as(AdminToken.class);

        SharedTestData.setAdminToken(adminTokenData.getToken());
        System.out.println("✅ Admin logged in - Token: " + adminTokenData.getToken());

        // regular customer token
        UserCredentialsPojo customerUser = userCredentialsPojoList.get(1);
        CustomerToken userTokenData = loginRequest.login(customerUser)
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("token", not(isEmptyString()))
                .extract()
                .as(CustomerToken.class);

        SharedTestData.setCustomerToken(userTokenData.getToken());
        SharedTestData.setCustomerUsername(customerUser.getUsername());
        System.out.println("✅ Customer logged in - Token: " + userTokenData.getToken());

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║   ✅ GLOBAL SETUP COMPLETE                      ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");
    }
}