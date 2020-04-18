package dao;

import domain.JloggUser;

public interface JloggUserDao {
    JloggUser findUser(String username, String passwd);

    boolean updateAccount(JloggUser user);
}
