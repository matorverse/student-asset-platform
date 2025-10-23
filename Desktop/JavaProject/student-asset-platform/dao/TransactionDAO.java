package com.studentassetsharing.dao;

import com.studentassetsharing.model.Transaction;
import com.studentassetsharing.model.TransactionDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void createTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (item_id, borrower_id, owner_id, request_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transaction.getItemId());
            pstmt.setInt(2, transaction.getBorrowerId());
            pstmt.setInt(3, transaction.getOwnerId());
            pstmt.setDate(4, new java.sql.Date(transaction.getRequestDate().getTime()));
            pstmt.setString(5, transaction.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTransactionStatus(int transactionId, String status) {
        String sql = "UPDATE transactions SET status = ?, approve_date = ? WHERE transc_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            if ("Approved".equalsIgnoreCase(status)) {
                pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            } else {
                pstmt.setNull(2, Types.DATE);
            }
            pstmt.setInt(3, transactionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TransactionDetail> getTransactionsAsOwner(int ownerId) {
        List<TransactionDetail> details = new ArrayList<>();
        String sql = "SELECT t.transc_id, t.item_id, i.item_name, u.name AS borrower_name, t.request_date, t.status " +
                "FROM transactions t " +
                "JOIN items i ON t.item_id = i.item_id " +
                "JOIN users u ON t.borrower_id = u.user_id " +
                "WHERE t.owner_id = ?";
        return getTransactionDetails(ownerId, details, sql);
    }

    public List<TransactionDetail> getTransactionsAsBorrower(int borrowerId) {
        List<TransactionDetail> details = new ArrayList<>();
        String sql = "SELECT t.transc_id, t.item_id, i.item_name, u.name AS owner_name, t.request_date, t.status " +
                "FROM transactions t " +
                "JOIN items i ON t.item_id = i.item_id " +
                "JOIN users u ON t.owner_id = u.user_id " +
                "WHERE t.borrower_id = ?";
        return getTransactionDetails(borrowerId, details, sql);
    }

    private List<TransactionDetail> getTransactionDetails(int userId, List<TransactionDetail> details, String sql) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TransactionDetail detail = new TransactionDetail();
                    detail.setTransactionId(rs.getInt("transc_id"));
                    detail.setItemId(rs.getInt("item_id"));
                    detail.setItemName(rs.getString("item_name"));
                    if (sql.contains("borrower_name")) {
                        detail.setBorrowerName(rs.getString("borrower_name"));
                    }
                    if (sql.contains("owner_name")) {
                        detail.setOwnerName(rs.getString("owner_name"));
                    }
                    detail.setRequestDate(rs.getDate("request_date"));
                    detail.setStatus(rs.getString("status"));
                    details.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }
    // Add this method to your existing TransactionDAO class
    public int getPendingTransactionCount() {
        String sql = "SELECT COUNT(*) FROM transactions WHERE status = 'Pending'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}