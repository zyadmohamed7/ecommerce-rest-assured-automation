package org.example.datagenerators;

import com.github.javafaker.Faker;
import org.example.pojos.CreateOrderPojo;
import org.example.pojos.OrderItemPojo;

import java.util.ArrayList;
import java.util.List;

public class OrderDataGenerator {
    private static final Faker faker = new Faker();


    public static CreateOrderPojo generateSingleItemOrder(String username, String itemId) {
        OrderItemPojo orderItem = new OrderItemPojo(
                itemId,
                faker.number().numberBetween(1, 10)
        );

        List<OrderItemPojo> items = new ArrayList<>();
        items.add(orderItem);

        return new CreateOrderPojo(username, items);
    }


    public static CreateOrderPojo generateMultiItemOrder(String username, List<String> itemIds) {
        List<OrderItemPojo> items = new ArrayList<>();

        for (String itemId : itemIds) {
            OrderItemPojo orderItem = new OrderItemPojo(
                    itemId,
                    faker.number().numberBetween(1, 5)
            );
            items.add(orderItem);
        }

        return new CreateOrderPojo(username, items);
    }



    public static CreateOrderPojo generateOrderWithQuantity(String username, String itemId, int quantity) {
        OrderItemPojo orderItem = new OrderItemPojo(itemId, quantity);
        List<OrderItemPojo> items = new ArrayList<>();
        items.add(orderItem);

        return new CreateOrderPojo(username, items);
    }


    public static CreateOrderPojo generateLargeQuantityOrder(String username, String itemId) {
        OrderItemPojo orderItem = new OrderItemPojo(
                itemId,
                faker.number().numberBetween(1000, 10000) // Large quantity
        );

        List<OrderItemPojo> items = new ArrayList<>();
        items.add(orderItem);

        return new CreateOrderPojo(username, items);
    }


    public static CreateOrderPojo generateEmptyOrder(String username) {
        return new CreateOrderPojo(username, new ArrayList<>());
    }
}