package edu.dnk89.userfront.services.impl;

import edu.dnk89.userfront.dao.PrimaryTransactionDao;
import edu.dnk89.userfront.dao.SavingsTransactionDao;
import edu.dnk89.userfront.domain.PrimaryTransaction;
import edu.dnk89.userfront.domain.SavingsTransaction;
import edu.dnk89.userfront.domain.User;
import edu.dnk89.userfront.services.TransactionService;
import edu.dnk89.userfront.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;
    @Autowired
    private PrimaryTransactionDao primaryTransactionDao;
    @Autowired
    private SavingsTransactionDao savingsTransactionDao;

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
}
