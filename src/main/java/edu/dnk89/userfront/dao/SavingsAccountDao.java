package edu.dnk89.userfront.dao;

import edu.dnk89.userfront.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {

    SavingsAccount findByNumber(int number);

}
