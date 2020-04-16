package service.Impl;

import dao.Impl.JloggUserDaoImpl;
import dao.JloggUserDao;
import domain.JloggUser;
import service.LoginService;

public class LoginServiceImpl implements LoginService {

    @Override
    public JloggUser login(JloggUser u) {
        JloggUserDao userDao = new JloggUserDaoImpl();
        return userDao.findUser(u.getUsername(), u.getPasswd());
    }
}
