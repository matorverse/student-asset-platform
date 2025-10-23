package com.studentassetsharing.model;

import java.util.Date;

public class Transaction {
    private int transcId;
    private int itemId;
    private int borrowerId;
    private int ownerId;
    private Date requestDate;
    private Date approveDate;
    private String status;

    // Constructors
    public Transaction() {
    }

    // Getters and Setters for each field
    public int getTranscId() {
        return transcId;
    }

    public void setTranscId(int transcId) {
        this.transcId = transcId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}