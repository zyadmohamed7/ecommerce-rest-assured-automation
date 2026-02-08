package org.example.testcases;

import com.fasterxml.jackson.core.type.TypeReference;
import io.qameta.allure.*;
import org.example.apis.ItemsEndpoint;
import org.example.apis.LoginEndpoint;
import org.example.apis.OrdersEndpoint;
import org.example.datagenerators.ItemDataGenerator;
import org.example.datagenerators.OrderDataGenerator;
import org.example.pojos.*;
import org.example.utils.JsonUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

@Epic("E-Commerce API Testing")
@Feature("Negative Testing")
public class NegativeFlowTest {
    AdminToken adminTokenData;
    CustomerToken userTokenData;
    UserCredentialsPojo adminUser;
    UserCredentialsPojo customerUser;

    @BeforeClass
    @Step("Setup: Login and generate tokens for Admin and Customer users")
    public void CreateTokens() throws IOException {
        LoginEndpoint loginRequest = new LoginEndpoint();
        List<UserCredentialsPojo> userCredentialsPojoList = JsonUtils.readJsonFile("users.json",
                new TypeReference<List<UserCredentialsPojo>>() {
                });

        // store the admin's token
        adminUser = userCredentialsPojoList.get(0);
        adminTokenData = loginRequest.login(adminUser)
                .then()
                .assertThat()
                .statusCode(200)
                .body("token", notNullValue())
                .body("token", not(isEmptyString()))
                .extract()
                .as(AdminToken.class);
        System.out.println("Admin Token: " + adminTokenData.getToken());

        // store the customer's token
        customerUser = userCredentialsPojoList.get(1);
        userTokenData = loginRequest.login(customerUser)
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("token", not(isEmptyString()))
                .extract()
                .as(CustomerToken.class);
        System.out.println("User Token: " + userTokenData.getToken());
    }

    @Test
    @Story("Item Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test creating item with invalid data")
    public void testCreateItemWithInvalidName() throws InterruptedException {
        ItemsEndpoint itemsEndpoint = new ItemsEndpoint(adminTokenData.getToken());
        CreateItemPojo invalidItem = ItemDataGenerator.invalidItemWithInvalidName("");
        itemsEndpoint.createitems(invalidItem)
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    @Story("Item Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test creating item with invalid data")
    public void testCreateItemWithInvalidPrice() throws InterruptedException {
        ItemsEndpoint itemsEndpoint = new ItemsEndpoint(adminTokenData.getToken());
        CreateItemPojo invalidItem = ItemDataGenerator.invalidItemWithInvalidPrice(-10);
        itemsEndpoint.createitems(invalidItem)
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    @Story("Item Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test creating item with invalid data")
    public void testCreateItemWithInvalidStock() throws InterruptedException {
        ItemsEndpoint itemsEndpoint = new ItemsEndpoint(adminTokenData.getToken());
        CreateItemPojo invalidItem = ItemDataGenerator.invalidItemWithInvalidStock(-10);
        itemsEndpoint.createitems(invalidItem)
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    @Story("Item Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test creating item with very long name")
    public void testCreateItemWithLongName() {
        ItemsEndpoint itemsEndpoint = new ItemsEndpoint(adminTokenData.getToken());
        CreateItemPojo longNameItem = ItemDataGenerator.generateItemWithLongName();

        itemsEndpoint.createitems(longNameItem)
                .then()
                .statusCode(400);
    }

    @Test
    @Story("Order Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test creating empty order")
    public void testCreateEmptyOrder() {
        OrdersEndpoint ordersEndpoint = new OrdersEndpoint(adminTokenData.getToken());
        CreateOrderPojo emptyOrder = OrderDataGenerator.generateEmptyOrder("customer@test.com");

        ordersEndpoint.createOrder(emptyOrder)
                .then()
                .statusCode(400);
    }
}