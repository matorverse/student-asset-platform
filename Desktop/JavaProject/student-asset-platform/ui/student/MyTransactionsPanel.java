package com.studentassetsharing.ui.student;

import com.studentassetsharing.dao.ItemDAO;
import com.studentassetsharing.dao.TransactionDAO;
import com.studentassetsharing.model.TransactionDetail;
import com.studentassetsharing.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class MyTransactionsPanel extends JPanel {

    private final User currentUser;
    private final TransactionDAO transactionDAO;
    private final ItemDAO itemDAO;
    private JTable requestsTable, borrowingTable;
    private DefaultTableModel requestsModel, borrowingModel;

    public MyTransactionsPanel(User currentUser) {
        this.currentUser = currentUser;
        this.transactionDAO = new TransactionDAO();
        this.itemDAO = new ItemDAO();

        setLayout(new GridLayout(2, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createRequestsPanel());
        add(createBorrowingPanel());

        loadAllTransactions();
    }

    private JPanel createRequestsPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Requests for My Items"));

        String[] columns = {"Trans ID", "Item ID", "Item Name", "Borrower", "Date", "Status"};
        requestsModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        requestsTable = new JTable(requestsModel);
        requestsTable.removeColumn(requestsTable.getColumnModel().getColumn(0)); // Hide Trans ID
        requestsTable.removeColumn(requestsTable.getColumnModel().getColumn(0)); // Hide Item ID
        panel.add(new JScrollPane(requestsTable), BorderLayout.CENTER);

        JButton approveButton = new JButton("Approve");
        JButton declineButton = new JButton("Decline");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(approveButton);
        buttonPanel.add(declineButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        approveButton.addActionListener(e -> handleRequest("Approved"));
        declineButton.addActionListener(e -> handleRequest("Declined"));

        return panel;
    }

    private JPanel createBorrowingPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Items I'm Borrowing"));

        String[] columns = {"Item Name", "Owner", "Date", "Status"};
        borrowingModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        borrowingTable = new JTable(borrowingModel);
        panel.add(new JScrollPane(borrowingTable), BorderLayout.CENTER);

        return panel;
    }

    private void handleRequest(String action) {
        int selectedRow = requestsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a request.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int transactionId = (int) requestsModel.getValueAt(requestsTable.convertRowIndexToModel(selectedRow), 0);
        int itemId = (int) requestsModel.getValueAt(requestsTable.convertRowIndexToModel(selectedRow), 1);
        String currentStatus = (String) requestsModel.getValueAt(requestsTable.convertRowIndexToModel(selectedRow), 5);

        if (!"Pending".equalsIgnoreCase(currentStatus)) {
            JOptionPane.showMessageDialog(this, "This request has already been actioned.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        transactionDAO.updateTransactionStatus(transactionId, action);

        if ("Approved".equals(action)) {
            itemDAO.updateItemAvailability(itemId, "Borrowed");
        } else if ("Declined".equals(action)) {
            itemDAO.updateItemAvailability(itemId, "Available");
        }

        JOptionPane.showMessageDialog(this, "Request has been " + action.toLowerCase() + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
        loadAllTransactions();
    }

    private void loadAllTransactions() {
        requestsModel.setRowCount(0);
        borrowingModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<TransactionDetail> ownerList = transactionDAO.getTransactionsAsOwner(currentUser.getUserId());
        for (TransactionDetail detail : ownerList) {
            requestsModel.addRow(new Object[]{
                    detail.getTransactionId(),
                    detail.getItemId(),
                    detail.getItemName(),
                    detail.getBorrowerName(),
                    sdf.format(detail.getRequestDate()),
                    detail.getStatus()
            });
        }

        List<TransactionDetail> borrowerList = transactionDAO.getTransactionsAsBorrower(currentUser.getUserId());
        for (TransactionDetail detail : borrowerList) {
            borrowingModel.addRow(new Object[]{
                    detail.getItemName(),
                    detail.getOwnerName(),
                    sdf.format(detail.getRequestDate()),
                    detail.getStatus()
            });
        }
    }
}