package service;

import domain.JloggUser;

public interface UserService {
    JloggUser login(JloggUser u);

    boolean updateAccount(JloggUser user);
}
