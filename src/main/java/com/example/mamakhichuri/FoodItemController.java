package com.example.mamakhichuri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foodItems")
public class FoodItemController {

    @Autowired
    private FoodItemRepository repository;

    @Autowired
    private DataSource dataSource;

    // A helper function to check if the database is connected
    // Currently, it's not used in the code but you can choose to use it
    private void checkDatabaseConnection() {
        try (var connection = dataSource.getConnection()) {
            if (connection.isValid(2)) {
                System.out.println("Database connected");
            } else {
                System.out.println("Database not connected");
            }
        } catch (SQLException ex) {
            System.out.println("Database not connected: " + ex.getMessage());
        }
    }

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
            newFoodItem.setId(id); // Set the ID of the existing entity
            repository.save(newFoodItem);
            return new ResponseEntity<>(newFoodItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("FoodItem not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
