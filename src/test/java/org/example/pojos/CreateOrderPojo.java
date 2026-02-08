package org.example.pojos;

import java.util.List;

public class CreateOrderPojo {
    private String customerId;
    private List<OrderItemPojo> items;

    public CreateOrderPojo() { }

    public CreateOrderPojo(String customerId, List<OrderItemPojo> items) {
        this.customerId = customerId;
        this.items = items;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemPojo> getItems() {
        return items;
    }
    public void setItems(List<OrderItemPojo> items) {
        this.items = items;
    }
}
