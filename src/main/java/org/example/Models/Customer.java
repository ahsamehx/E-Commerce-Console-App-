package org.example.Models;

public class Customer {
    private String name;
    private double balance;   //assuming that the customer balance is known in the app explicitly like gift card
    private Cart cart;  // won't use it to stick to the given example structure

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public double getBalance() { return balance; }

    public void deduct(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }
}
