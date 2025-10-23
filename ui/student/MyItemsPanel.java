package com.studentassetsharing.ui.student;

import com.studentassetsharing.dao.ItemDAO;
import com.studentassetsharing.model.Item;
import com.studentassetsharing.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MyItemsPanel extends JPanel {

    private final User currentUser;
    private final ItemDAO itemDAO;
    private JTable myItemsTable;
    private DefaultTableModel tableModel;

    public MyItemsPanel(User currentUser) {
        this.currentUser = currentUser;
        this.itemDAO = new ItemDAO();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Table Setup ---
        String[] columnNames = {"ID", "Item Name", "Category", "Availability"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        myItemsTable = new JTable(tableModel);
        myItemsTable.removeColumn(myItemsTable.getColumnModel().getColumn(0)); // Hide ID
        JScrollPane scrollPane = new JScrollPane(myItemsTable);

        // --- Buttons Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addNewItemButton = new JButton("Add New Item");
        JButton deleteItemButton = new JButton("Delete Selected Item");
        JButton refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);
        buttonPanel.add(addNewItemButton);
        buttonPanel.add(deleteItemButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Action Listeners ---
        refreshButton.addActionListener(e -> loadUserItems());
        addNewItemButton.addActionListener(e -> openAddNewItemDialog());
        deleteItemButton.addActionListener(e -> deleteSelectedItem());

        loadUserItems();
    }

    private void loadUserItems() {
        tableModel.setRowCount(0);
        List<Item> myItems = itemDAO.getItemsByOwner(currentUser.getUserId());
        for (Item item : myItems) {
            Object[] row = new Object[]{
                    item.getItemId(),
                    item.getItemName(),
                    item.getCategory(),
                    item.getAvailability()
            };
            tableModel.addRow(row);
        }
    }

    private void openAddNewItemDialog() {
        // Create components for the dialog
        JTextField itemNameField = new JTextField(20);
        JTextField categoryField = new JTextField(20);
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        // Layout the components in a panel
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Item Name:"));
        panel.add(itemNameField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionScrollPane);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Item",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (itemNameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Item name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Item newItem = new Item();
            newItem.setUserId(currentUser.getUserId());
            newItem.setItemName(itemNameField.getText());
            newItem.setCategory(categoryField.getText());
            newItem.setDescription(descriptionArea.getText());
            newItem.setAvailability("Available");

            itemDAO.addItem(newItem);
            loadUserItems(); // Refresh the table
        }
    }

    private void deleteSelectedItem() {
        int selectedRow = myItemsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this item?", "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int itemId = (int) tableModel.getValueAt(myItemsTable.convertRowIndexToModel(selectedRow), 0);
            itemDAO.deleteItem(itemId);
            loadUserItems(); // Refresh
        }
    }
}