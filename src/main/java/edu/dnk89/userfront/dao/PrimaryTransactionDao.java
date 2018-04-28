package edu.dnk89.userfront.dao;

import edu.dnk89.userfront.domain.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {
}
