package com.studentassetsharing.ui.auth;

import com.studentassetsharing.dao.UserDAO;
import com.studentassetsharing.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {

    private JTextField nameField, emailField, phoneField, deptField;
    private JPasswordField passwordField;
    private JButton registerButton, backToLoginButton;
    private UserDAO userDAO;

    public RegisterFrame() {
        userDAO = new UserDAO();

        setTitle("Student Registration");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Form Fields ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField();
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField();
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Phone No:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField();
        panel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        deptField = new JTextField();
        panel.add(deptField, gbc);

        // --- Buttons ---
        registerButton = new JButton("Register");
        backToLoginButton = new JButton("Back to Login");

        JPanel buttonPanel = new JPanel(); // Use FlowLayout by default
        buttonPanel.add(registerButton);
        buttonPanel.add(backToLoginButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(buttonPanel, gbc);

        add(panel);

        // --- Action Listeners ---
        registerButton.addActionListener(e -> handleRegister());

        backToLoginButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void handleRegister() {
        // Simple Validation
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Name, Email, and Password are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User newUser = new User();
        newUser.setName(nameField.getText());
        newUser.setEmail(emailField.getText());
        newUser.setPassword(new String(passwordField.getPassword())); // TODO: Hash this password!
        newUser.setPhoneNo(phoneField.getText());
        newUser.setDept(deptField.getText());

        if (userDAO.addUser(newUser)) {
            JOptionPane.showMessageDialog(this, "Registration Successful! Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new LoginFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed. Email may already be in use.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}