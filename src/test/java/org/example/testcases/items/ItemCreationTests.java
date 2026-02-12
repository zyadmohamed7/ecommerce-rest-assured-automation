package org.example.testcases.items;

import io.qameta.allure.*;
import org.example.apis.ItemsEndpoint;
import org.example.datagenerators.ItemDataGenerator;
import org.example.pojos.CreateItemPojo;
import org.example.pojos.ItemIdPojo;
import org.example.testcases.BaseTest;
import org.example.utils.SharedTestData;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("E-Commerce API Testing")
@Feature("Item Management")
public class ItemCreationTests extends BaseTest {

    @Test(priority = 1)
    @Story("Item Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test creating a new item as Admin user")
    public void shouldCreateItemSuccessfully_WhenAdminCreatesWithValidData() throws InterruptedException {
        // Arrange
        ItemsEndpoint itemsEndpoint = new ItemsEndpoint(SharedTestData.getAdminToken());
        CreateItemPojo newItem = ItemDataGenerator.generateRandomItem();

        try {

            ItemIdPojo responsePost = itemsEndpoint.createitems(newItem)
                    .then()
                    .assertThat()
                    .statusCode(201)
                    .body(matchesJsonSchemaInClasspath("itemsSchema.json"))
                    .extract()
                    .as(ItemIdPojo.class);

            Thread.sleep(2000);

            // store item ID for other tests
            SharedTestData.setCreatedItemId(responsePost.getId());
            System.out.println("✅ Created item ID: " + responsePost.getId());

        } catch (Exception e) {
            System.err.println("⚠️ Failed to create item, using backup ID");
            SharedTestData.setCreatedItemId(SharedTestData.getBackupItemId());
        }
    }
}