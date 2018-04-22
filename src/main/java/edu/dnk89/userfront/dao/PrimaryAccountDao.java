package edu.dnk89.userfront.dao;

import edu.dnk89.userfront.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {

    PrimaryAccount findByNumber(int number);

}
