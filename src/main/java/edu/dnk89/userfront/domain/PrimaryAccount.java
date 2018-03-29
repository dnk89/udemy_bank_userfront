package edu.dnk89.userfront.domain;

import java.math.BigDecimal;
import java.util.List;

public class PrimaryAccount {

    private Long id;
    private int number;
    private BigDecimal balance;
    private List<PrimaryTransaction> transactions;

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

    public List<PrimaryTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<PrimaryTransaction> transactions) {
        this.transactions = transactions;
    }
}
