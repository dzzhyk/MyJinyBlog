package service;

import domain.JloggUserProfile;

public interface UserProfileService {
    JloggUserProfile flushAdminProfile();

    boolean updateProfile(JloggUserProfile profile);
}
