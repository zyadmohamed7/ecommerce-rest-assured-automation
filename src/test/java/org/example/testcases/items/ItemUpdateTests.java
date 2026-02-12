package org.example.testcases.items;

import io.qameta.allure.*;
import org.example.apis.ItemsEndpoint;
import org.example.datagenerators.ItemDataGenerator;
import org.example.pojos.CreateItemPojo;
import org.example.testcases.BaseTest;
import org.example.utils.SharedTestData;
import org.testng.annotations.Test;

@Epic("E-Commerce API Testing")
@Feature("Item Management")
public class ItemUpdateTests extends BaseTest {

    @Test(priority = 3)
    @Story("Item Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test updating an existing item")
    public void shouldUpdateItemSuccessfully_WhenItemExists() {

        String itemId = SharedTestData.getCreatedItemId();
        ItemsEndpoint itemsEndpoint = new ItemsEndpoint(SharedTestData.getAdminToken());
        CreateItemPojo updatedItem = ItemDataGenerator.generateRandomItem();


        itemsEndpoint.updateitems(itemId, updatedItem)
                .then()
                .assertThat()
                .statusCode(200);

        System.out.println("✅ Updated item: " + itemId);
    }
}