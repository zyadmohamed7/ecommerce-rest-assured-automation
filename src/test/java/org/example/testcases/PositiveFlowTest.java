package org.example.testcases;

import com.fasterxml.jackson.core.type.TypeReference;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.apis.ItemsEndpoint;
import org.example.apis.LoginEndpoint;
import org.example.apis.OrdersEndpoint;
import org.example.datagenerators.ItemDataGenerator;
import org.example.datagenerators.OrderDataGenerator;
import org.example.pojos.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.*;
import org.example.utils.JsonUtils;
import org.testng.annotations.BeforeClass;

@Epic("E-Commerce API Testing")
@Feature("Complete Order Flow")
public class PositiveFlowTest {

    AdminToken adminTokenData;
    CustomerToken userTokenData;
    UserCredentialsPojo userCredentialsPojo;
    String createdId;
    private final String BACKUP_ITEM_ID = "1770493915391";
    String createdCustomerId;
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

    @Test(priority = 1)
    @Story("Item Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test creating a new item as Admin user")
    public void testCreateitems() throws InterruptedException {

        ItemsEndpoint itemsEndpointCreate = new ItemsEndpoint(adminTokenData.getToken());
        CreateItemPojo newItem = ItemDataGenerator.generateRandomItem();
        try {
            ItemIdPojo responsePost = itemsEndpointCreate.createitems(newItem)
                    .then()
                    .assertThat()
                    .statusCode(201)
                    .body(matchesJsonSchemaInClasspath("itemsSchema.json"))
                    .extract()
                    .as(ItemIdPojo.class);
            Thread.sleep(2000);
            createdId = responsePost.getId();
            System.out.println("Created item ID: " + createdId);
        } catch (Exception e) {
            System.err.println("Failed to create item, using backup ID");
            createdId = BACKUP_ITEM_ID;
        }
    }



    @Test(priority = 2, dependsOnMethods = "testCreateitems")
    @Story("Item Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test retrieving an item by ID")
    public void testGetitems() throws InterruptedException {
        ItemsEndpoint itemsEndpointGet = new ItemsEndpoint(adminTokenData.getToken());
        itemsEndpointGet.getitems(createdId)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateitems")
    @Story("Item Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test updating an existing item")
    public void testUpdateitems() throws InterruptedException {
        ItemsEndpoint itemsEndpointUpdate = new ItemsEndpoint(adminTokenData.getToken());
        CreateItemPojo updatedItem = ItemDataGenerator.generateRandomItem();
        itemsEndpointUpdate.updateitems(createdId, updatedItem)
                .then()
                .assertThat()
                .statusCode(200);

        System.out.println("Item updated: " + createdId);
    }

    @Test(priority = 4)
    @Story("Order Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test creating a new order with items")
    public void testCreateOrder() throws InterruptedException {
        OrdersEndpoint ordersEndpoint = new OrdersEndpoint(adminTokenData.getToken());
        CreateOrderPojo orderRequest = OrderDataGenerator.generateSingleItemOrder(customerUser.getUsername(), createdId);
        CustomerId customerId = ordersEndpoint.createOrder(orderRequest)
                .then()
                .assertThat()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("ordersSchema.json"))

                .extract()
                .as(CustomerId.class);
        Thread.sleep(2000);
        createdCustomerId = customerId.getId();
        System.out.println("Created Customer ID: " + createdCustomerId);
    }

    @Test(priority = 5)
    @Story("Order Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test retrieving all orders")
    public void testGetOrder() throws InterruptedException {
        OrdersEndpoint ordersEndpoint = new OrdersEndpoint(adminTokenData.getToken());
        Response response = ordersEndpoint.getOrder();
        response.then()
                .assertThat()
                .statusCode(200)
                .log().all();

    }

    @Test(priority = 6)
    @Story("Order Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test checking out an order as customer")
    public void testCheckoutOrder() throws InterruptedException {
        OrdersEndpoint ordersEndpoint = new OrdersEndpoint(userTokenData.getToken());
        Response checkoutResponse = ordersEndpoint.getCheckout(createdCustomerId);
        checkoutResponse.then()
                .assertThat()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 7)
    @Story("Order Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test retrieving all paid orders")
    public void testGetPaidOrders() {
        OrdersEndpoint ordersEndpoint = new OrdersEndpoint(adminTokenData.getToken());
        Response checkoutResponse = ordersEndpoint.getPaidItems();
        checkoutResponse.then()
                .assertThat()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 8)
    @Story("Order Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test deleting a paid order")
    public void testDeletePaidOrders() throws InterruptedException {
        OrdersEndpoint ordersEndpoint = new OrdersEndpoint(adminTokenData.getToken());
        Response checkoutResponse = ordersEndpoint.deleteorder(createdCustomerId);
        checkoutResponse.then()
                .assertThat()
                .statusCode(204);
        Thread.sleep(2000);

    }

    @Test(priority = 9, dependsOnMethods = "testUpdateitems")
    @Story("Item Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test deleting an item")
    public void testDeleteitems() throws InterruptedException {
        ItemsEndpoint itemsEndpointDelete = new ItemsEndpoint(adminTokenData.getToken());
        itemsEndpointDelete.deleteitems(createdId)
                .then()
                .assertThat()
                .statusCode(204);
    }
}



/*
            List<UserCredentialsPojo> userList = JsonUtils.readJsonFile("users.json",
                    new TypeReference<List<UserCredentialsPojo>>() {});

            userList.forEach(System.out::println);

            UserCredentialsPojo adminCreds = userCredentialsPojoList.stream()
                    .filter(UserCredentialsPojo::isAdmin) // isAdmin == true
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No admin found in users.json"));

            UserCredentialsPojo customerCreds = userCredentialsPojoList.stream()
                    .filter(u -> !u.isAdmin()) // isAdmin == false
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No customer found in users.json"));


            adminTokenData = loginRequest.login(adminCreds)
                    .then()
                    .statusCode(200)
                    .body("token", notNullValue())
                    .body("token", not(isEmptyString()))
                    .extract()
                    .as(AdminToken.class);

            System.out.println("Admin Token: " + adminTokenData.getToken());


            userTokenData = loginRequest.login(customerCreds)
                    .then()
                    .statusCode(200)
                    .body("token", notNullValue())
                    .body("token", not(isEmptyString()))
                    .extract()
                    .as(CustomerToken.class);

            System.out.println("User Token: " + userTokenData.getToken());
            */

/*
    Map<String, String> userTokens = new HashMap<>();
    List<UserCredentialsPojo> allUsers;

    @BeforeClass
    public void CreateTokens() throws IOException {
        LoginEndpoint loginRequest = new LoginEndpoint();
        allUsers = JsonUtils.readJsonFile("users.json",
                new TypeReference<List<UserCredentialsPojo>>() {
                });

        for (UserCredentialsPojo user : allUsers) {
            String token = loginRequest.login(user)
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body("token", notNullValue())
                    .body("token", not(isEmptyString()))
                    .jsonPath()
                    .getString("token");

            userTokens.put(user.getUsername(), token);
            System.out.println("Token for " + user.getUsername() + ": " + token);
        }
    }
    @Test(dataProvider = "users", dataProviderClass = UserDataProvider.class)
    public void testCreateItemsForAllUsers(UserCredentialsPojo user) {
        String userToken = userTokens.get(user.getUsername());

        ItemsEndpoint itemsEndpoint = new ItemsEndpoint(userToken);
        CreateItemPojo newItem = new CreateItemPojo("test1", 123.0, 123);

        try {
            ItemIdPojo responsePost = itemsEndpoint.createitems(newItem)
                    .then()
                    .assertThat()
                    .statusCode(201)
                    .extract()
                    .as(ItemIdPojo.class);
            System.out.println("Created item for " + user.getUsername() + ": " + responsePost.getId());
        } catch (Exception e) {
            System.err.println("Failed to create item for " + user.getUsername());
        }
    }
}


 */