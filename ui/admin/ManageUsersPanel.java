package com.studentassetsharing.ui.admin;

import com.studentassetsharing.dao.UserDAO;
import com.studentassetsharing.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class ManageUsersPanel extends JPanel {

    private final UserDAO userDAO;
    private JTable usersTable;
    private DefaultTableModel tableModel;

    public ManageUsersPanel() {
        this.userDAO = new UserDAO();
        setOpaque(false);
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("User Management"));

        String[] columnNames = {"ID", "Name", "Email", "Department", "Phone No"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        usersTable = new JTable(tableModel);
        usersTable.removeColumn(usersTable.getColumnModel().getColumn(0));

        // --- NEW: Style the table header ---
        JTableHeader header = usersTable.getTableHeader();
        header.setBackground(new Color(35, 37, 45));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        usersTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(usersTable);
        scrollPane.getViewport().setBackground(new Color(43, 43, 43));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        JButton deleteButton = new JButton("Delete User");
        JButton refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> loadUsers());
        deleteButton.addActionListener(e -> deleteSelectedUser());

        loadUsers();
    }

    // Unchanged methods
    private void loadUsers() {
        tableModel.setRowCount(0);
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            if (!"admin@example.com".equals(user.getEmail())) {
                tableModel.addRow(new Object[]{user.getUserId(), user.getName(), user.getEmail(), user.getDept(), user.getPhoneNo()});
            }
        }
    }
    private void deleteSelectedUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow == -1) { return; }
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure? This action is irreversible.", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int userId = (int) tableModel.getValueAt(usersTable.convertRowIndexToModel(selectedRow), 0);
            userDAO.deleteUser(userId);
            loadUsers();
        }
    }
}