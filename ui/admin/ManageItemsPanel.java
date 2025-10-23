package com.studentassetsharing.ui.admin;

import com.studentassetsharing.dao.ItemDAO;
import com.studentassetsharing.model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageItemsPanel extends JPanel {

    private final ItemDAO itemDAO;
    private JTable itemsTable;
    private DefaultTableModel tableModel;

    public ManageItemsPanel() {
        this.itemDAO = new ItemDAO();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Item Management"));

        String[] columnNames = {"ID", "Item Name", "Category", "Availability", "Owner ID"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        itemsTable = new JTable(tableModel);
        itemsTable.removeColumn(itemsTable.getColumnModel().getColumn(0)); // Hide ID
        add(new JScrollPane(itemsTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteButton = new JButton("Delete Item");
        JButton refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> loadItems());
        deleteButton.addActionListener(e -> deleteSelectedItem());

        loadItems();
    }

    private void loadItems() {
        tableModel.setRowCount(0);
        List<Item> items = itemDAO.getAllItems();
        for (Item item : items) {
            tableModel.addRow(new Object[]{item.getItemId(), item.getItemName(), item.getCategory(), item.getAvailability(), item.getUserId()});
        }
    }

    private void deleteSelectedItem() {
        int selectedRow = itemsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int itemId = (int) tableModel.getValueAt(itemsTable.convertRowIndexToModel(selectedRow), 0);
            itemDAO.deleteItem(itemId);
            loadItems();
        }
    }
}