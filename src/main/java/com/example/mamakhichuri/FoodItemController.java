package com.example.mamakhichuri;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/foodItems")
public class FoodItemController {

    private FoodItemRepository repository = new FoodItemRepository();

    @GetMapping
    public List<FoodItem> getFoodItems() {
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
        Optional<FoodItem> optionalFoodItem = repository.findById(id);
        if (optionalFoodItem.isPresent()) {
            repository.delete(optionalFoodItem.get());
            return new ResponseEntity<>("FoodItem deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("FoodItem not found with id: " + id, HttpStatus.NOT_FOUND);
        }
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
