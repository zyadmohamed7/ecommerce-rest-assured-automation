package org.example.pojos;

public class CreateItemPojo  {
    private String name;
    private double price;
    private int stock;

    public CreateItemPojo () {

    }

    public CreateItemPojo(String name) {
        this.name = name;

    }
    public CreateItemPojo(double price) {
        this.price = price;
    }
    public CreateItemPojo(int stock) {
        this.stock = stock;
    }

    public CreateItemPojo (String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}