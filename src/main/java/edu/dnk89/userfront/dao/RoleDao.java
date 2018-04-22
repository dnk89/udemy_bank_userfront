package edu.dnk89.userfront.dao;

import edu.dnk89.userfront.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Integer> {

    Role findByName(String name);

}
