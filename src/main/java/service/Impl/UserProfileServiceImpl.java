package service.Impl;

import dao.Impl.JloggUserProfileDaoImpl;
import dao.JloggUserProfileDao;
import domain.JloggUserProfile;
import service.UserProfileService;

public class UserProfileServiceImpl implements UserProfileService {
    @Override
    public JloggUserProfile flushAdminProfile() {
        JloggUserProfileDao dao = new JloggUserProfileDaoImpl();
        return dao.findAdminProfile();
    }
}
