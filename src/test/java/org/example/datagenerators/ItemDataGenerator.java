package org.example.datagenerators;

import com.github.javafaker.Faker;
import org.example.pojos.CreateItemPojo;

public class ItemDataGenerator {
    private static final Faker faker = new Faker();

    public static CreateItemPojo generateRandomItem() {
        return new CreateItemPojo(
                faker.commerce().productName(),
                Double.parseDouble(faker.commerce().price(10.0, 1000.0)),
                faker.number().numberBetween(1, 500)
        );
    }

    public static CreateItemPojo generateItemWithName(String name) {
        return new CreateItemPojo(
                name,
                Double.parseDouble(faker.commerce().price(10.0, 1000.0)),
                faker.number().numberBetween(1, 500)
        );
    }

    public static CreateItemPojo generateItemWithPriceRange(double minPrice, double maxPrice) {
        return new CreateItemPojo(
                faker.commerce().productName(),
                Double.parseDouble(faker.commerce().price(minPrice, maxPrice)),
                faker.number().numberBetween(1, 500)
        );
    }

    public static CreateItemPojo generateCustomItem(String name, double price, int stock) {
        return new CreateItemPojo(name, price, stock);
    }

     //invalid data
    public static CreateItemPojo invalidItemWithInvalidName(String name) {
        return new CreateItemPojo();
    }
    public static CreateItemPojo invalidItemWithInvalidPrice(double price) {
        return new CreateItemPojo();
    }
    public static CreateItemPojo invalidItemWithInvalidStock(int stock) {
        return new CreateItemPojo();
    }

     //boundary testing
    public static CreateItemPojo generateItemWithLongName() {
        String longName = faker.lorem().characters(500); // 500 characters
        return new CreateItemPojo(
                longName,
                Double.parseDouble(faker.commerce().price(10.0, 1000.0)),
                faker.number().numberBetween(1, 500)
        );
    }
}