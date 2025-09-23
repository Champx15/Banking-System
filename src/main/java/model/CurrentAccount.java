package model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.util.Scanner;

@Entity
@DiscriminatorValue("CURRENT")
public class CurrentAccount extends Account {

    @Column(name="overdraft_limit")
    private  BigDecimal overDraftLim = BigDecimal.valueOf(50000);

    @Transient
    public String type = "CURRENT";

    public BigDecimal getOverDraftLim() {
        return overDraftLim;
    }

    public void setOverDraftLim(BigDecimal overDraftLim) {
        this.overDraftLim = overDraftLim;
    }

    public CurrentAccount() {}

    public CurrentAccount(String accNum, String accHolder, BigDecimal balance)
    {
        super(accNum, accHolder, balance);
    }


    @Override
    public String getType() { return type; }

    @Override
    public String withdraw(BigDecimal input)
    {
        BigDecimal current = getBalance();
        if (current.compareTo(BigDecimal.ZERO)<=0 || input.compareTo(current)>0) {
            if (input.compareTo(overDraftLim)<=0) {
                overDraftLim=overDraftLim.subtract(input);
                return "Remaining overdraft amount is ₹" + (overDraftLim.subtract(input));
            } else {
               return "Input amount is more than overdraft limit";
            }
        } else {
            if (input.compareTo(current)>0) {
                return "Not enough balance";
            } else {
                setBalance(current.subtract(input));
                return ("Remaining amount is ₹" + (current.subtract(input)));
            }
        }
    }

    @Override
    public BigDecimal calcInterest( int months)
    {
        float interestRate = 2f / 100 / 12;
        return getBalance().multiply(BigDecimal.valueOf(months).multiply(BigDecimal.valueOf(interestRate)));
    }
}