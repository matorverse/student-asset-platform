package com.studentassetsharing.ui.admin;

import com.studentassetsharing.dao.ItemDAO;
import com.studentassetsharing.dao.TransactionDAO;
import com.studentassetsharing.dao.UserDAO;
import com.studentassetsharing.ui.auth.LoginFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDashboardFrame extends JFrame {

    // --- NEW: A modern color palette ---
    private final Color COLOR_BACKGROUND = new Color(43, 43, 43);
    private final Color COLOR_SIDEBAR_START = new Color(35, 37, 45);
    private final Color COLOR_SIDEBAR_END = new Color(25, 27, 35);
    private final Color COLOR_ACCENT = new Color(0, 120, 215); // A vibrant blue
    private final Color COLOR_TEXT_LIGHT = new Color(200, 200, 200);

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);

    public AdminDashboardFrame() {
        setTitle("Admin Dashboard");
        setMinimumSize(new Dimension(950, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createSidebar(), BorderLayout.WEST);
        add(createMainPanel(), BorderLayout.CENTER);
    }

    // NEW: A custom panel that can paint a gradient
    private static class GradientPanel extends JPanel {
        private final Color startColor;
        private final Color endColor;
        public GradientPanel(Color start, Color end) {
            this.startColor = start;
            this.endColor = end;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private JPanel createSidebar() {
        JPanel sidebar = new GradientPanel(COLOR_SIDEBAR_START, COLOR_SIDEBAR_END);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(240, 0));
        sidebar.setBorder(new EmptyBorder(15, 0, 15, 0));

        JLabel titleLabel = new JLabel("Asset Sharing");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(10, 10, 40, 10));

        JButton manageUsersButton = createSidebarButton("Manage Users");
        JButton manageItemsButton = createSidebarButton("Manage Items");
        JButton logoutButton = createSidebarButton("Logout");

        manageUsersButton.addActionListener(e -> cardLayout.show(contentPanel, "Users"));
        manageItemsButton.addActionListener(e -> cardLayout.show(contentPanel, "Items"));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        sidebar.add(titleLabel);
        sidebar.add(manageUsersButton);
        sidebar.add(manageItemsButton);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(logoutButton);
        return sidebar;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(createStatsPanel(), BorderLayout.NORTH);

        contentPanel.setOpaque(false); // Make it transparent to show main panel background
        contentPanel.add(new ManageUsersPanel(), "Users");
        contentPanel.add(new ManageItemsPanel(), "Items");
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        cardLayout.show(contentPanel, "Users");
        return mainPanel;
    }

    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 15, 15));
        statsPanel.setOpaque(false);
        statsPanel.add(createStatCard("Total Users", String.valueOf(new UserDAO().getUserCount())));
        statsPanel.add(createStatCard("Total Items", String.valueOf(new ItemDAO().getItemCount())));
        statsPanel.add(createStatCard("Pending Requests", String.valueOf(new TransactionDAO().getPendingTransactionCount())));
        return statsPanel;
    }

    private JPanel createStatCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(COLOR_SIDEBAR_START);
        card.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, COLOR_ACCENT));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        valueLabel.setForeground(COLOR_ACCENT); // Use accent color for numbers
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel(title.toUpperCase());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        titleLabel.setForeground(COLOR_TEXT_LIGHT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        card.add(valueLabel, BorderLayout.CENTER);
        card.add(titleLabel, BorderLayout.SOUTH);
        return card;
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(COLOR_TEXT_LIGHT);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(15, 25, 15, 25));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        button.putClientProperty("FlatLaf.style",
                "button.flat: true;" +
                        "background: transparent;" +
                        "hoverBackground: #0078D7;" + // Use accent color for hover
                        "pressedBackground: #005A9E;");
        return button;
    }
}