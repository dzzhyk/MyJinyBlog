package dao;

import domain.JloggUserProfile;

public interface JloggUserProfileDao {

    JloggUserProfile findAdminProfile();

    boolean updateProfile(JloggUserProfile profile);
}
