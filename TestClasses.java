import java.util.Date;

public class TestClasses {
    public static void main(String[] args) {
        // Test User class
        User user = new User(1, "John Doe", "john@example.com", "password123", "student");
        System.out.println("User: " + user.toString());
        user.setName("Jane Doe");
        System.out.println("Updated User Name: " + user.getName());

        // Test Item class
        Item item = new Item(1, "Laptop", "A portable computer", 1, "available", "gadget");
        System.out.println("Item: " + item.toString());
        item.setStatus("lent");
        System.out.println("Updated Item Status: " + item.getStatus());

        // Test Transaction class
        Date lendDate = new Date();
        Date dueDate = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000); // 7 days later
        Transaction transaction = new Transaction(1, 1, 1, 2, lendDate, dueDate, null, 0.0);
        System.out.println("Transaction: " + transaction.toString());
        transaction.setFee(5.0);
        System.out.println("Updated Transaction Fee: " + transaction.getFee());
    }
}
