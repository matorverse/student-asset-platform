package com.studentassetsharing.model;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password; // Should be the hashed password
    private String phoneNo;
    private String dept;

    // Constructors
    public User() {}

    // Getters and Setters for each field...
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }
}