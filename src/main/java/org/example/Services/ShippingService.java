package org.example.Services;

import java.util.*;
import org.example.Models.Shippable;

public class ShippingService {
    public static void ship(List<Shippable> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");

        Map<String, Integer> itemCounts = new LinkedHashMap<>();
        Map<String, Double> weightMap = new HashMap<>();  //assuming that each product has a unique name

        for (Shippable item : items) {
            itemCounts.put(item.getName(), itemCounts.getOrDefault(item.getName(), 0) + 1);
            weightMap.put(item.getName(), item.getWeight());
        }

        double totalWeight = 0;

        for (String name : itemCounts.keySet()) {
            int count = itemCounts.get(name);
            double weight = weightMap.get(name);
            double totalItemWeight = weight * count;
            totalWeight += totalItemWeight;

            if (totalItemWeight < 1) {
                System.out.println(count + "x " + name + " " + (int)(totalItemWeight * 1000) + "gm");
            } else {
                System.out.println(count + "x " + name + " " + String.format("%.1f", totalItemWeight) + "kg");
            }
        }


        System.out.println("Total package weight " + String.format("%.1f", totalWeight) + "kg");
    }
}
