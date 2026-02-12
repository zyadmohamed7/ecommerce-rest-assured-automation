package org.example.testcases.orders;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.apis.OrdersEndpoint;
import org.example.testcases.BaseTest;
import org.example.utils.SharedTestData;
import org.testng.annotations.Test;

@Epic("E-Commerce API Testing")
@Feature("Order Checkout")
public class OrderCheckoutTests extends BaseTest {

    @Test(priority = 6)
    @Story("Order Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test checking out an order as customer")
    public void shouldCheckoutSuccessfully_WhenCustomerHasValidOrder() {

        String orderId = SharedTestData.getCreatedOrderId();
        OrdersEndpoint ordersEndpoint = new OrdersEndpoint(SharedTestData.getCustomerToken());

        Response checkoutResponse = ordersEndpoint.getCheckout(orderId);

        checkoutResponse.then()
                .assertThat()
                .statusCode(200);

        System.out.println("✅ Checked out order: " + orderId);
    }

    @Test(priority = 7)
    @Story("Order Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test retrieving all paid orders")
    public void shouldRetrievePaidOrdersSuccessfully_WhenPaidOrdersExist() {

        OrdersEndpoint ordersEndpoint = new OrdersEndpoint(SharedTestData.getAdminToken());

        Response checkoutResponse = ordersEndpoint.getPaidItems();

        checkoutResponse.then()
                .assertThat()
                .statusCode(200);

        System.out.println("✅ Retrieved all paid orders");
    }
}