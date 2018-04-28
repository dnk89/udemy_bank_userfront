package edu.dnk89.userfront.services.impl;

import edu.dnk89.userfront.dao.PrimaryAccountDao;
import edu.dnk89.userfront.dao.PrimaryTransactionDao;
import edu.dnk89.userfront.dao.SavingsAccountDao;
import edu.dnk89.userfront.dao.SavingsTransactionDao;
import edu.dnk89.userfront.domain.*;
import edu.dnk89.userfront.services.TransactionService;
import edu.dnk89.userfront.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;
    @Autowired
    private PrimaryTransactionDao primaryTransactionDao;
    @Autowired
    private SavingsTransactionDao savingsTransactionDao;
    @Autowired
    private PrimaryAccountDao primaryAccountDao;
    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Override
    public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<PrimaryTransaction> transactions = user.getPrimaryAccount().getTransactions();
        return transactions;
    }

    @Override
    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<SavingsTransaction> transactions = user.getSavingsAccount().getTransactions();
        return transactions;
    }

    @Override
    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionDao.save(primaryTransaction);
    }

    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionDao.save(savingsTransaction);
    }

    @Override
    public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionDao.save(primaryTransaction);
    }

    @Override
    public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionDao.save(savingsTransaction);
    }

    @Override
    @Transactional
    public void transferBetweenAccounts(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {
        if (transferFrom.equalsIgnoreCase("primary") && transferTo.equalsIgnoreCase("savings")) {
            primaryAccount.setBalance(primaryAccount.getBalance().subtract(new BigDecimal(amount)));
            savingsAccount.setBalance(savingsAccount.getBalance().add(new BigDecimal(amount)));
            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Transfer from Primary to Savings account", "Transfer", "Finished", Double.parseDouble(amount), primaryAccount.getBalance(), primaryAccount);
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Transfer to Savings from Primary account", "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getBalance(), savingsAccount);
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);
            primaryTransactionDao.save(primaryTransaction);
            savingsTransactionDao.save(savingsTransaction);

        } else if (transferFrom.equalsIgnoreCase("savings") && transferTo.equalsIgnoreCase("primary")) {
            primaryAccount.setBalance(primaryAccount.getBalance().add(new BigDecimal(amount)));
            savingsAccount.setBalance(savingsAccount.getBalance().subtract(new BigDecimal(amount)));
            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Transfer to Primary from Savings account", "Transfer", "Finished", Double.parseDouble(amount), primaryAccount.getBalance(), primaryAccount);
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Transfer from Savings to Primary account", "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getBalance(), savingsAccount);
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);
            primaryTransactionDao.save(primaryTransaction);
            savingsTransactionDao.save(savingsTransaction);

        } else {
            throw new Exception("Invalid transfer");
        }
    }
}
