package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity > product.getQuantity()) {
            System.out.println("______________________________");
            System.out.println("Cannot add " + quantity + "x " + product.getName() + ". Only " + product.getQuantity() + " in stock.");
            System.out.println("______________________________");
            return;
        }

        for (CartItem item : items) {
            if (item.product.equals(product)) {        // To avoid duplicating when adding the same item
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }


    public void removeProduct(Product product) {
        items.removeIf(item -> item.product.equals(product));
    }

    public void updateQuantity(Product product, int newQuantity) {
        for (CartItem item : items) {
            if (item.product.equals(product)) {
                if (newQuantity <= 0) {
                    items.remove(item); //assume that negative like zero so we remove
                } else if (newQuantity > product.getQuantity()) {
                    System.out.println("Cannot update "+ product.getName()+".Only " + product.getQuantity() + " in stock.");
                } else {
                    item.setQuantity(newQuantity);
                    System.out.println("Quantity updated to " + newQuantity);
                }
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }


    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}
