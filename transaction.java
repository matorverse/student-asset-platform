import java.util.Date;

public class Transaction {
    private int transactionId;
    private int itemId;
    private int lenderId;
    private int borrowerId;
    private Date lendDate;
    private Date dueDate;
    private Date returnDate;
    private double fee;

    public Transaction(int transactionId, int itemId, int lenderId, int borrowerId, Date lendDate, Date dueDate, Date returnDate, double fee) {
        this.transactionId = transactionId;
        this.itemId = itemId;
        this.lenderId = lenderId;
        this.borrowerId = borrowerId;
        this.lendDate = lendDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fee = fee;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getLenderId() {
        return lenderId;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", itemId=" + itemId +
                ", lenderId=" + lenderId +
                ", borrowerId=" + borrowerId +
                ", lendDate=" + lendDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", fee=" + fee +
                '}';
    }
}
