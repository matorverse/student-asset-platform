package com.studentassetsharing.model;

import java.sql.Timestamp;

public class Item {
    private int itemId;
    private int userId; // Owner's ID
    private String itemName;
    private String description;
    private String category;
    private String availability;
    private Timestamp dateAdded;

    // Constructors
    public Item() {}

    // Getters and Setters for each field...
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
    
    public Timestamp getDateAdded() { return dateAdded; }
    public void setDateAdded(Timestamp dateAdded) { this.dateAdded = dateAdded; }
}