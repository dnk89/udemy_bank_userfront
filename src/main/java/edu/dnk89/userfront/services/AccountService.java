package edu.dnk89.userfront.services;

import edu.dnk89.userfront.domain.PrimaryAccount;
import edu.dnk89.userfront.domain.SavingsAccount;

public interface AccountService {

    PrimaryAccount createPrimaryAccount();

    SavingsAccount createSavingsAccount();
}
