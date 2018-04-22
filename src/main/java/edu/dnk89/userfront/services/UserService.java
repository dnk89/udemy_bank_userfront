package edu.dnk89.userfront.services;

import edu.dnk89.userfront.domain.User;
import edu.dnk89.userfront.domain.security.UserRole;

import java.util.Set;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    User create(User user, Set<UserRole> userRoles);
}
