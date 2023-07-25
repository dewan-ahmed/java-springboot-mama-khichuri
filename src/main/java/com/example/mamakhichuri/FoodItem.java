package com.example.mamakhichuri;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class FoodItem {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String type;

    public FoodItem() {
        this.id = UUID.randomUUID();  // generate a random UUID for each new FoodItem
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
