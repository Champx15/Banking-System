package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.util.Scanner;

@Entity
@DiscriminatorValue("SAVINGS")
public class SavingAccount extends Account {

    @Transient
    public String type = "SAVINGS";

    public SavingAccount() {}

    public SavingAccount(String accntNumber, String accntHolderName, BigDecimal balance)
    {
        super(accntNumber, accntHolderName, balance);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String withdraw(BigDecimal input)
    {
        BigDecimal current = getBalance();
        if (current.compareTo(BigDecimal.ZERO)>0) {
            if (input.compareTo(current)>0) {
                return "Not enough balance";
            } else {
                setBalance(current.subtract(input));
                return ("Remaining amount is ₹" + (current.subtract(input)));
            }
        } else {
            return ("Not enough balance");

        }
    }

    @Override
    public BigDecimal calcInterest(int months)
    {
        float interestRate = 7f / 100 / 12;
        return  getBalance().multiply(BigDecimal.valueOf(months).multiply(BigDecimal.valueOf(interestRate)));
    }
}
