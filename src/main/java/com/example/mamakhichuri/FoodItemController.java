package com.example.mamakhichuri;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/foodItems")
public class FoodItemController {

    private final FoodItemRepository repository;

    public FoodItemController(FoodItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<FoodItem> getFoodItems() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodItem(@PathVariable UUID id) {
        Optional<FoodItem> optionalFoodItem = repository.findById(id);
        if (optionalFoodItem.isPresent()) {
            return new ResponseEntity<>(optionalFoodItem.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("FoodItem not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public FoodItem createFoodItem(@RequestBody FoodItem foodItem) {
        return repository.save(foodItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoodItem(@PathVariable UUID id) {
        repository.deleteById(id);
        return new ResponseEntity<>("FoodItem deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFoodItem(@PathVariable UUID id, @RequestBody FoodItem newFoodItem) {
        Optional<FoodItem> optionalFoodItem = repository.findById(id);
        if (optionalFoodItem.isPresent()) {
            FoodItem existingFoodItem = optionalFoodItem.get();
            existingFoodItem.setName(newFoodItem.getName());
            existingFoodItem.setType(newFoodItem.getType());
            repository.save(existingFoodItem);
            return new ResponseEntity<>(existingFoodItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("FoodItem not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
