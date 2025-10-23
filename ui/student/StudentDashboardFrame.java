package com.studentassetsharing.ui.student;

import com.studentassetsharing.model.User;
import com.studentassetsharing.ui.auth.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class StudentDashboardFrame extends JFrame {

    private final User currentUser;

    public StudentDashboardFrame(User user) {
        this.currentUser = user;

        setTitle("Student Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Top Panel for Welcome Message and Logout ---
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        topPanel.add(welcomeLabel, BorderLayout.WEST);
        topPanel.add(logoutButton, BorderLayout.EAST);

        // --- Tabbed Pane for Main Content ---
        JTabbedPane tabbedPane = new JTabbedPane();

        // Pass the current user to the panels that need it
        tabbedPane.addTab("Browse Items", new BrowseItemsPanel(currentUser));
        tabbedPane.addTab("My Items", new MyItemsPanel(currentUser));
        tabbedPane.addTab("My Transactions", new MyTransactionsPanel(currentUser)); // Pass user here

        // --- Add components to the frame ---
        add(topPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }
}