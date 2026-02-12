package org.example.testcases.items;

import io.qameta.allure.*;
import org.example.apis.ItemsEndpoint;
import org.example.testcases.BaseTest;
import org.example.utils.SharedTestData;
import org.testng.annotations.Test;

@Epic("E-Commerce API Testing")
@Feature("Item Management")
public class ItemDeletionTests extends BaseTest {

    @Test(priority = 9)
    @Story("Item Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test deleting an item")
    public void shouldDeleteItemSuccessfully_WhenItemExists() {

        String itemId = SharedTestData.getCreatedItemId();
        ItemsEndpoint itemsEndpoint = new ItemsEndpoint(SharedTestData.getAdminToken());

        itemsEndpoint.deleteitems(itemId)
                .then()
                .assertThat()
                .statusCode(204);

        System.out.println("✅ Deleted item: " + itemId);
    }
}