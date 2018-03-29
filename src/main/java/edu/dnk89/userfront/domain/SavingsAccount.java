package edu.dnk89.userfront.domain;

import java.math.BigDecimal;
import java.util.List;

public class SavingsAccount {

    private Long id;
    private int number;
    private BigDecimal balance;
    private List<SavingsTransaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<SavingsTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<SavingsTransaction> transactions) {
        this.transactions = transactions;
    }
}
