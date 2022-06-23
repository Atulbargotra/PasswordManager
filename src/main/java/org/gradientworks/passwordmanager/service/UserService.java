package org.gradientworks.passwordmanager.service;

import org.gradientworks.passwordmanager.domain.Authority;
import org.gradientworks.passwordmanager.domain.User;

public interface UserService {
    User saveUser(User user);
    User getUser(String username);

    Authority saveAuthority(Authority role);

}
