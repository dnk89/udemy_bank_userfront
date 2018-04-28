package edu.dnk89.userfront.services;

import edu.dnk89.userfront.domain.PrimaryAccount;
import edu.dnk89.userfront.domain.PrimaryTransaction;
import edu.dnk89.userfront.domain.SavingsAccount;
import edu.dnk89.userfront.domain.SavingsTransaction;

import java.util.List;

public interface TransactionService {

    List<PrimaryTransaction> findPrimaryTransactionList(String username);

    List<SavingsTransaction> findSavingsTransactionList(String username);

    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);

    void transferBetweenAccounts(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception;
}
