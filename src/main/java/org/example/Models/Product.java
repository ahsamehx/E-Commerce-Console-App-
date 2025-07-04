package org.example.Models;

public class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    //I will add setters below in case that the business logic or requirements need to change the item price or Name
    public void setName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty.");
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");
        this.quantity = quantity;
    }


    public void reduceQuantity(int amount) {
        if (amount > quantity)
            throw new IllegalArgumentException("The current stock is not enough for this amount.");
        quantity -= amount;
    }

    //    public boolean isExpired() {
//        return false;
//    }
}
