#  Student Community Asset Platform (Java + Swing + MySQL)

**Purpose:** A Java-based platform for students to **lend and borrow campus resources** for a small fee, keeping track of item details, due dates, and ownership.

---

##  Project Structure

```
student-community-asset-platform/
 ├─ src/
 │   ├─ model/              # Data classes
 │   │   ├─ User.java
 │   │   ├─ Item.java
 │   │   └─ Transaction.java
 │   │
 │   ├─ dao/                # Database access (CRUD)
 │   │   ├─ UserDAO.java
 │   │   ├─ ItemDAO.java
 │   │   ├─ TransactionDAO.java
 │   │   └─ DBConnection.java
 │   │
 │   ├─ service/            # Business logic
 │   │   ├─ UserService.java
 │   │   ├─ ItemService.java
 │   │   └─ TransactionService.java
 │   │
 │   ├─ ui/                 # Swing GUI
 │   │   ├─ LoginUI.java
 │   │   ├─ DashboardUI.java
 │   │   ├─ ItemFormUI.java
 │   │   ├─ TransactionUI.java
 │   │   └─ AdminUI.java
 │   │
 │   └─ Main.java           # Entry point
 │
 ├─ docs/                   # UML diagrams, ERD, reports
 ├─ .gitignore              # Ignore bin, .idea, .class, etc.
 └─ README.md
```

---

##  Core Classes

### **1. User.java**

Represents a student user.

```java
public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String role; // student/admin

    // constructors, getters, setters
}
```

### **2. Item.java**

Represents an item available for lending.

```java
public class Item {
    private int itemId;
    private String itemName;
    private String description;
    private int ownerId;  
    private String status;   // available/lent
    private String category; // book, gadget, tool, etc.

    // constructors, getters, setters
}
```

### **3. Transaction.java**

Represents a lending/receiving record.

```java
import java.util.Date;

public class Transaction {
    private int transactionId;
    private int itemId;
    private int lenderId;
    private int borrowerId;
    private Date lendDate;
    private Date dueDate;
    private Date returnDate;
    private double fee;

    // constructors, getters, setters
}
```

---

## ⚙️ Service Layer

These classes handle **business logic** and interact with DAOs:

* **UserService.java** → register/login users, verify credentials.
* **ItemService.java** → add items, update status, list available items.
* **TransactionService.java** → create lending record, check due dates, calculate fee.

---

##  Database Design (MySQL)

### `users` table

```sql
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    role VARCHAR(20)
);
```

### `items` table

```sql
CREATE TABLE items (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(100),
    description TEXT,
    owner_id INT,
    category VARCHAR(50),
    status VARCHAR(20),
    FOREIGN KEY (owner_id) REFERENCES users(user_id)
);
```

### `transactions` table

```sql
CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    item_id INT,
    lender_id INT,
    borrower_id INT,
    lend_date DATE,
    due_date DATE,
    return_date DATE,
    fee DECIMAL(10,2),
    FOREIGN KEY (item_id) REFERENCES items(item_id),
    FOREIGN KEY (lender_id) REFERENCES users(user_id),
    FOREIGN KEY (borrower_id) REFERENCES users(user_id)
);
```

---

##  Swing UI Flow

1. **LoginUI** → User login/registration.
2. **DashboardUI** → Show items, add new items, view active transactions.
3. **ItemFormUI** → Add/edit item details.
4. **TransactionUI** → Borrow an item, show due dates, return item.
5. **AdminUI** → Manage all users/items (optional).

---

##  Main Class (Entry Point)

```java
public class Main {
    public static void main(String[] args) {
        new LoginUI(); // Start with login screen
    }
}
```

---

##  License

This project is for **academic purposes** under the student community initiative.
Free to use and modify with proper credits.
