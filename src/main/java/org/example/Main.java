package org.example;

import org.example.Models.*;
import org.example.Services.CheckoutService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Nour", 2000);

        Cheese cheese = new Cheese("Cheese", 100, 5, LocalDate.now().plusDays(1), 0.2);
        Biscuits biscuits = new Biscuits("Biscuits", 150, 5, LocalDate.now().plusDays(5), 0.7);
        ScratchCard scratchCard = new ScratchCard("ScratchCard", 50, 10);

        Cart cart = new Cart();
        cart.add(cheese, 1);
        cart.add(cheese, 1);
        try {
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }
}
