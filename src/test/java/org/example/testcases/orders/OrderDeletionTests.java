package org.example.testcases.orders;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.apis.OrdersEndpoint;
import org.example.testcases.BaseTest;
import org.example.utils.SharedTestData;
import org.testng.annotations.Test;

@Epic("E-Commerce API Testing")
@Feature("Order Management")
public class OrderDeletionTests extends BaseTest {

    @Test(priority = 8)
    @Story("Order Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test deleting a paid order")
    public void shouldDeleteOrderSuccessfully_WhenOrderIsPaid() throws InterruptedException {

        String orderId = SharedTestData.getCreatedOrderId();
        OrdersEndpoint ordersEndpoint = new OrdersEndpoint(SharedTestData.getAdminToken());

        Response checkoutResponse = ordersEndpoint.deleteorder(orderId);

        checkoutResponse.then()
                .assertThat()
                .statusCode(204);

        Thread.sleep(2000);
        System.out.println("✅ Deleted order: " + orderId);
    }
}