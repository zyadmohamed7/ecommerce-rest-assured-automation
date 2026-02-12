package org.example.testcases.items;

import io.qameta.allure.*;
import org.example.apis.ItemsEndpoint;
import org.example.testcases.BaseTest;
import org.example.utils.SharedTestData;
import org.testng.annotations.Test;

@Epic("E-Commerce API Testing")
@Feature("Item Management")
public class ItemRetrievalTests extends BaseTest {

    @Test(priority = 2)
    @Story("Item Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test retrieving an item by ID")
    public void shouldRetrieveItemSuccessfully_WhenItemExists() {

        String itemId = SharedTestData.getCreatedItemId();
        ItemsEndpoint itemsEndpoint = new ItemsEndpoint(SharedTestData.getAdminToken());


        itemsEndpoint.getitems(itemId)
                .then()
                .assertThat()
                .statusCode(200);

        System.out.println("✅ Retrieved item: " + itemId);
    }
}