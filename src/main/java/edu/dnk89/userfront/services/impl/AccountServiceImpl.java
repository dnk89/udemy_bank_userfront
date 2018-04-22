package edu.dnk89.userfront.services.impl;

import edu.dnk89.userfront.dao.PrimaryAccountDao;
import edu.dnk89.userfront.dao.SavingsAccountDao;
import edu.dnk89.userfront.domain.PrimaryAccount;
import edu.dnk89.userfront.domain.SavingsAccount;
import edu.dnk89.userfront.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PrimaryAccountDao primaryAccountDao;
    
    @Autowired
    private SavingsAccountDao savingsAccountDao;

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

    private int accountGen() {
        return ++nextAccountNumber;
    }
}
