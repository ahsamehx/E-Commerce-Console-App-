package org.example.Services;

import org.example.Models.*;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        List<Shippable> shippables = new ArrayList<>();
        double subtotal = 0;

        for (CartItem item : cart.getItems()) {
            if (item.product instanceof ExpirableProduct && ((ExpirableProduct) item.product).isExpired()) {
                throw new IllegalStateException(item.product.getName() + " is expired.");
            }

            if (item.quantity > item.product.getQuantity()) {
                throw new IllegalStateException(item.product.getName() + " is out of stock.");
            }

            item.product.reduceQuantity(item.quantity);
            subtotal += item.getTotalPrice();

            if (item.product instanceof Shippable) {
                for (int i = 0; i < item.quantity; i++) {
                    shippables.add((Shippable) item.product);
                }
            }
        }

        double shippingFees = shippables.isEmpty() ? 0 : 30; //assuming that the shipping fees are constant as per the given example
        double total = subtotal + shippingFees;

        if (customer.getBalance() < total) {
            throw new IllegalStateException("Insufficient balance.");
        }

        ShippingService.ship(shippables);
        customer.deduct(total);

        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.quantity + "x " + item.product.getName() + " " + (int) item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int) subtotal);
        System.out.println("Shipping " + (int) shippingFees);
        System.out.println("Amount " + (int) total);
    }
}
