package org.example.testcases.orders;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.apis.OrdersEndpoint;
import org.example.testcases.BaseTest;
import org.example.utils.SharedTestData;
import org.testng.annotations.Test;

@Epic("E-Commerce API Testing")
@Feature("Order Management")
public class OrderRetrievalTests extends BaseTest {

    @Test(priority = 5)
    @Story("Order Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test retrieving all orders")
    public void shouldRetrieveAllOrdersSuccessfully_WhenOrdersExist() {

        OrdersEndpoint ordersEndpoint = new OrdersEndpoint(SharedTestData.getAdminToken());

        Response response = ordersEndpoint.getOrder();

        response.then()
                .assertThat()
                .statusCode(200)
                .log().all();

        System.out.println("✅ Retrieved all orders");
    }
}