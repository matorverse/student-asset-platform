# Student Asset Sharing Platform

A Java-based desktop application for students to share and manage assets (items) within a university or educational institution. The platform allows students to register, login, browse available items, add their own items for sharing, and manage transactions.

## Features

### User Management
- User registration and login
- Role-based access (Student, Admin)

### Item Management
- Add items for sharing
- Browse available items
- Update item details
- Remove items

### Transaction Management
- Record borrowing/lending transactions
- View transaction history
- Manage transaction details

### Admin Features
- Manage users
- Manage items
- Oversee all transactions

## Project Structure

```
student-asset-platform/
├── Main.java                    # Application entry point
├── dao/                         # Data Access Objects
│   ├── DatabaseConnection.java  # Database connection utilities
│   ├── ItemDAO.java            # Item data operations
│   ├── TransactionDAO.java     # Transaction data operations
│   └── UserDAO.java            # User data operations
├── model/                       # Data models
│   ├── Item.java               # Item model
│   ├── Transaction.java        # Transaction model
│   ├── TransactionDetail.java  # Transaction detail model
│   └── User.java               # User model
├── service/                     # Business logic (if any)
├── ui/                          # User Interface
│   ├── admin/                   # Admin interface components
│   │   ├── AdminDashboardFrame.java
│   │   ├── ManageItemsPanel.java
│   │   └── ManageUsersPanel.java
│   ├── auth/                    # Authentication components
│   │   ├── LoginFrame.java
│   │   └── RegisterFrame.java
│   └── student/                 # Student interface components
│       ├── BrowseItemsPanel.java
│       ├── MyItemsPanel.java
│       ├── MyTransactionsPanel.java
│       └── StudentDashboardFrame.java
└── util/                        # Utility classes (if any)
```

## Technologies Used

- **Language:** Java
- **GUI Framework:** Swing
- **Database:** MySQL
- **Build Tool:** None (direct compilation)
- **Version Control:** Git

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL Connector/J (JDBC driver)

## Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/matorverse/student-asset-platform.git
   cd student-asset-platform
   ```

2. **Database Setup:**
   - Install MySQL Server
   - Create a database named `student_asset_db`
   - Run the provided SQL script to create tables (if available)

3. **Configure Database Connection:**
   - Update database credentials in `dao/DatabaseConnection.java`

4. **Compile and Run:**
   ```bash
   javac Main.java
   java Main
   ```

## Database Schema

The application uses the following main tables:
- `users` - User information
- `items` - Item details
- `transactions` - Transaction records
- `transaction_details` - Transaction details

## Usage

1. **Launch the application** by running `Main.java`
2. **Register** as a new user or **login** with existing credentials
3. **Students** can browse items, add their own items, and view transactions
4. **Admins** have additional privileges to manage users and items

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For questions or support, please contact the development team.
