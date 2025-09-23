package model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name= "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="account_type")
public abstract class Account {
    @Id
    @Column(name = "account_num")
    private String accntNumber;

    @Column(name= "account_holder")
    private String accntHolderName;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name="created_at")
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name="account_type",insertable = false,updatable = false)
    private String accntType;

    public Account() {}

    public Account(String accntNumber, String accntHolderName, BigDecimal balance)
    {
        this.accntNumber = accntNumber;
        this.accntHolderName = accntHolderName;
        this.balance = balance;
    }

    public String getAccntType() { return accntType; }
    public void setAccntType(String accntType) { this.accntType = accntType; }

    public String getAccntNumber() { return accntNumber; }
    public void setAccntNumber(String accntNumber) { this.accntNumber = accntNumber; }

    public String getAccntHolderName() { return accntHolderName; }
    public void setAccntHolderName(String accntHolderName) { this.accntHolderName = accntHolderName; }

    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    public abstract String getType();

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

//    public Account accountDetails() { return Account;}

    public void deposit(BigDecimal amount)
    {
        balance = balance.add(amount);
    }

    public abstract String withdraw(BigDecimal input);
    public abstract BigDecimal calcInterest(int months);
}