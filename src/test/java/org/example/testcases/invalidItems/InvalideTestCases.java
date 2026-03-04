package org.example.testcases.invalidItems;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.apis.ItemsEndpoint;
import org.example.datagenerators.ItemDataGenerator;
import org.example.pojos.AdminToken;
import org.example.pojos.CreateItemPojo;
import org.testng.annotations.Test;

public class InvalideTestCases {

    AdminToken adminTokenData;

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
}


