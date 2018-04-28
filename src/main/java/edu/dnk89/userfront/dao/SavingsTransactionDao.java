package edu.dnk89.userfront.dao;

import edu.dnk89.userfront.domain.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long> {
}
