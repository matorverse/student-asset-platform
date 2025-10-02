class Item {
    private int itemId;
    private String itemName;
    private String description;
    private int ownerId;  // userId of the owner
    private String status;   // available/lent
    private String category; // book, gadget, tool, etc.

    // Default constructor
    public Item() {
    }

    // Constructor with parameters
    public Item(int itemId, String itemName, String description, int ownerId, String status, String category) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.ownerId = ownerId;
        this.status = status;
        this.category = category;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", ownerId=" + ownerId +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
