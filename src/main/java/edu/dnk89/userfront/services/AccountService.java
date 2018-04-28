package edu.dnk89.userfront.services;

import edu.dnk89.userfront.domain.PrimaryAccount;
import edu.dnk89.userfront.domain.SavingsAccount;

import java.security.Principal;

public interface AccountService {

    PrimaryAccount createPrimaryAccount();

    SavingsAccount createSavingsAccount();

    void deposit(String accountType, double amount, Principal principal);

    void withdraw(String accountType, double amount, Principal principal);
}
