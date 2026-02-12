package org.example.testcases.orders;

import io.qameta.allure.*;
import org.example.apis.OrdersEndpoint;
import org.example.datagenerators.OrderDataGenerator;
import org.example.pojos.CreateOrderPojo;
import org.example.pojos.CustomerId;
import org.example.testcases.BaseTest;
import org.example.utils.SharedTestData;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("E-Commerce API Testing")
@Feature("Order Management")
public class OrderCreationTests extends BaseTest {

    @Test(priority = 4)
    @Story("Order Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test creating a new order with items")
    public void shouldCreateOrderSuccessfully_WhenValidItemsProvided() throws InterruptedException {

        String itemId = SharedTestData.getCreatedItemId();
        String customerUsername = SharedTestData.getCustomerUsername();

        OrdersEndpoint ordersEndpoint = new OrdersEndpoint(SharedTestData.getAdminToken());
        CreateOrderPojo orderRequest = OrderDataGenerator.generateSingleItemOrder(
                customerUsername, itemId);


        CustomerId customerId = ordersEndpoint.createOrder(orderRequest)
                .then()
                .assertThat()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("ordersSchema.json"))
                .extract()
                .as(CustomerId.class);

        Thread.sleep(2000);

        // store order ID for checkout tests
        SharedTestData.setCreatedOrderId(customerId.getId());
        System.out.println("✅ Created order ID: " + customerId.getId());
    }
}