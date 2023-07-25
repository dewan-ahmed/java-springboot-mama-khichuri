package com.example.mamakhichuri;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FoodItemRepository {

    private List<FoodItem> foodItems = new ArrayList<>();

    public List<FoodItem> findAll() {
        return foodItems;
    }

    public Optional<FoodItem> findById(UUID id) {
        return foodItems.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public FoodItem save(FoodItem foodItem) {
        // Without the following line, an update will actually duplicate entry
        // foodItems.removeIf(item -> item.getId().equals(foodItem.getId()));
        foodItems.add(foodItem);
        return foodItem;
    }

    public void delete(FoodItem foodItem) {
        foodItems.remove(foodItem);
    }
}
