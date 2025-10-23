# Student Asset Platform

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-orange.svg)](https://www.mysql.com/)
[![Swing](https://img.shields.io/badge/Swing-GUI-green.svg)](https://docs.oracle.com/javase/tutorial/uiswing/)

A comprehensive Java-based platform designed for students to share, lend, and borrow resources within their community. The system encourages collaboration, reduces unnecessary expenses, and promotes a culture of sustainability on campus.

## 🌟 Features

- **User Authentication**: Secure login and registration system
- **Asset Management**: Add, browse, and manage items for sharing
- **Transaction Tracking**: Complete transaction history and management
- **Admin Dashboard**: Administrative controls for user and item management
- **Student Dashboard**: Personalized interface for students
- **Database Integration**: MySQL database with JDBC connectivity

## 📁 Project Structure

```
student-asset-platform/
├── Main.java                    # Application entry point
├── README.md                    # Project documentation
├── studentdumb.sql              # Database dump file
├── dao/                         # Data Access Objects
│   ├── DatabaseConnection.java  # Database connection utilities
│   ├── ItemDAO.java            # Item data operations
│   ├── TransactionDAO.java     # Transaction data operations
│   └── UserDAO.java            # User data operations
├── model/                       # Data models
│   ├── Item.java                # Item entity
│   ├── Transaction.java         # Transaction entity
│   ├── TransactionDetail.java   # Transaction detail entity
│   └── User.java                # User entity
├── ui/                          # User Interface components
│   ├── admin/                   # Admin interface
│   │   ├── AdminDashboardFrame.java
│   │   ├── ManageItemsPanel.java
│   │   └── ManageUsersPanel.java
│   ├── auth/                    # Authentication interface
│   │   ├── LoginFrame.java
│   │   └── RegisterFrame.java
│   ├── components/              # Shared UI components
│   └── student/                 # Student interface
│       ├── BrowseItemsPanel.java
│       ├── MyItemsPanel.java
│       ├── MyTransactionsPanel.java
│       └── StudentDashboardFrame.java
├── service/                     # Business logic services
└── util/                        # Utility classes
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- JDBC Driver for MySQL

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/matorverse/student-asset-platform.git
   cd student-asset-platform
   ```

2. **Set up the database**
   - Import the `studentdumb.sql` file into your MySQL database
   - Update database connection settings in `dao/DatabaseConnection.java`

3. **Compile and run**
   ```bash
   javac Main.java
   java Main
   ```

## 🛠️ Technologies Used

- **Java Swing**: For GUI development
- **MySQL**: Database management
- **JDBC**: Database connectivity
- **Maven/Gradle**: Dependency management (if applicable)

## 📊 Database Schema

The application uses a MySQL database with the following main tables:
- `users`: User account information
- `items`: Available items for sharing
- `transactions`: Transaction records
- `transaction_details`: Detailed transaction information

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Contact

For questions or support, please open an issue on GitHub.

---

**Made with ❤️ for student communities**
