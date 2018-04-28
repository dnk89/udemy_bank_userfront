package edu.dnk89.userfront.services.impl;

import edu.dnk89.userfront.dao.PrimaryAccountDao;
import edu.dnk89.userfront.dao.SavingsAccountDao;
import edu.dnk89.userfront.domain.*;
import edu.dnk89.userfront.services.AccountService;
import edu.dnk89.userfront.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PrimaryAccountDao primaryAccountDao;
    
    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    private UserService userService;

    private static int nextAccountNumber = 123456;

    @Override
    public PrimaryAccount createPrimaryAccount() {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setBalance(new BigDecimal(0));
        primaryAccount.setNumber(accountGen());

        primaryAccountDao.save(primaryAccount);
        
        return primaryAccountDao.findByNumber(primaryAccount.getNumber());
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setBalance(new BigDecimal(0));
        savingsAccount.setNumber(accountGen());

        savingsAccountDao.save(savingsAccount);

        return savingsAccountDao.findByNumber(savingsAccount.getNumber());
    }

    public void deposit(String accountType, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("primary")) {
            PrimaryAccount account = user.getPrimaryAccount();
            account.setBalance(account.getBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(account);

            Date date = new Date();

            PrimaryTransaction transaction = new PrimaryTransaction(date, "Deposit to Primary Account", "Account", "Finished", amount, account.getBalance(), account);
        } else if (accountType.equalsIgnoreCase("savings")) {
            SavingsAccount account = user.getSavingsAccount();
            account.setBalance(account.getBalance().add(new BigDecimal(amount)));
            savingsAccountDao.save(account);

            Date date = new Date();

            SavingsTransaction transaction = new SavingsTransaction(date, "Deposit to Savings Account", "Account", "Finished", amount, account.getBalance(), account);
        }
    }

    public void withdraw(String accountType, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("primary")) {
            PrimaryAccount account = user.getPrimaryAccount();
            account.setBalance(account.getBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(account);

            Date date = new Date();

            PrimaryTransaction transaction = new PrimaryTransaction(date, "Withdraw from Primary Account", "Account", "Finished", amount, account.getBalance(), account);
        } else if (accountType.equalsIgnoreCase("savings")) {
            SavingsAccount account = user.getSavingsAccount();
            account.setBalance(account.getBalance().subtract(new BigDecimal(amount)));
            savingsAccountDao.save(account);

            Date date = new Date();

            SavingsTransaction transaction = new SavingsTransaction(date, "Withdraw from Savings Account", "Account", "Finished", amount, account.getBalance(), account);
        }
    }

    private int accountGen() {
        return ++nextAccountNumber;
    }
}
