package com.studentassetsharing.ui.student;

import com.studentassetsharing.dao.ItemDAO;
import com.studentassetsharing.dao.TransactionDAO;
import com.studentassetsharing.model.Item;
import com.studentassetsharing.model.Transaction;
import com.studentassetsharing.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class BrowseItemsPanel extends JPanel {

    private final User currentUser;
    private final ItemDAO itemDAO;
    private final TransactionDAO transactionDAO;
    private JTable itemsTable;
    private DefaultTableModel tableModel;

    public BrowseItemsPanel(User currentUser) {
        this.currentUser = currentUser;
        this.itemDAO = new ItemDAO();
        this.transactionDAO = new TransactionDAO();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Table Setup ---
        String[] columnNames = {"ID", "Item Name", "Category", "Description", "Owner ID"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        itemsTable = new JTable(tableModel);

        // --- THE FIX: Remove columns in reverse order to avoid index shifting issues ---
        itemsTable.removeColumn(itemsTable.getColumnModel().getColumn(4)); // Remove "Owner ID" first (was at index 4)
        itemsTable.removeColumn(itemsTable.getColumnModel().getColumn(0)); // Then remove "ID" (was at index 0)

        JScrollPane scrollPane = new JScrollPane(itemsTable);

        // --- Buttons Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton borrowButton = new JButton("Borrow Selected Item");
        JButton refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);
        buttonPanel.add(borrowButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Action Listeners ---
        refreshButton.addActionListener(e -> loadItems());

        borrowButton.addActionListener(e -> borrowSelectedItem());

        // Initial data load
        loadItems();
    }

    private void loadItems() {
        // Clear existing data
        tableModel.setRowCount(0);

        List<Item> availableItems = itemDAO.getAllAvailableItems();
        for (Item item : availableItems) {
            // Do not show items owned by the current user
            if (item.getUserId() != currentUser.getUserId()) {
                Object[] row = new Object[]{
                        item.getItemId(),
                        item.getItemName(),
                        item.getCategory(),
                        item.getDescription(),
                        item.getUserId() // Owner's ID
                };
                tableModel.addRow(row);
            }
        }
    }

    private void borrowSelectedItem() {
        int selectedRow = itemsTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item from the table to borrow.", "No Item Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Retrieve the hidden item ID and owner ID from the model
        int itemId = (int) tableModel.getValueAt(itemsTable.convertRowIndexToModel(selectedRow), 0);
        int ownerId = (int) tableModel.getValueAt(itemsTable.convertRowIndexToModel(selectedRow), 4);

        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setItemId(itemId);
        transaction.setBorrowerId(currentUser.getUserId());
        transaction.setOwnerId(ownerId);
        transaction.setRequestDate(new Date()); // Set current date as request date
        transaction.setStatus("Pending");

        transactionDAO.createTransaction(transaction);

        // Update the item's availability to show it's no longer available
        itemDAO.updateItemAvailability(itemId, "Pending Request");

        JOptionPane.showMessageDialog(this, "Request sent to the owner!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Refresh the list to remove the requested item
        loadItems();
    }
}