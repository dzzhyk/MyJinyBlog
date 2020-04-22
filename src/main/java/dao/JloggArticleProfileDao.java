package dao;

import domain.JloggArticleContent;
import domain.JloggArticleProfile;

import java.util.List;

public interface JloggArticleProfileDao {
    List<JloggArticleProfile> findProfilesByLimit(int currentCount, int count);

    JloggArticleProfile findProfileByAid(int aid);

    List<JloggArticleProfile> findAllProfiles();

    boolean removeProfileByAid(int aid);

    List<JloggArticleProfile> removeProfileByArchive(int year, int month, int currentCount, int count);
}
