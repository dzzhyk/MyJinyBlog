package service.Impl;

import dao.Impl.JloggUserDaoImpl;
import dao.JloggUserDao;
import domain.JloggUser;
import service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public JloggUser login(JloggUser u) {
        JloggUserDao userDao = new JloggUserDaoImpl();
        return userDao.findUser(u.getUsername(), u.getPasswd());
    }

    @Override
    public boolean updateAccount(JloggUser user) {
        JloggUserDao userDao = new JloggUserDaoImpl();
        return userDao.updateAccount(user);
    }
}
