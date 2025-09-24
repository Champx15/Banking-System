package model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @Column(name="transaction_id")
    private String transId;
    @Column(name="transaction_type")
    private String transType;
    @Column(name="amount")
    private BigDecimal amount;
    @Column(name = "transaction_time")
    @CreationTimestamp
    private Timestamp timestamp;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_num",nullable = false)
    private Account account;

    public Transaction() {}

    public Transaction(Account account,BigDecimal amount, String transType, String transId)
    {
        this.account=account;
        this.amount = amount;
        this.transType = transType;
        this.transId = transId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }
}
