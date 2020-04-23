package dao;

import domain.JloggArticleContent;
import domain.JloggArticleProfile;

import java.util.List;

public interface JloggArticleProfileDao {
    List<JloggArticleProfile> findProfilesByLimit(int currentCount, int count);

    JloggArticleProfile findProfileByAid(int aid);

    JloggArticleProfile findProfileByTitle(String title);

    List<JloggArticleProfile> findAllProfiles();

    boolean removeProfileByAid(int aid);

    List<JloggArticleProfile> listProfileByArchive(int year, int month, int currentCount, int count);

    int createProfile(String title, String username, String shown);

    int updateProfile(int aid, String title, String username, String shown);

    int findViewsByAid(int aid);
}
