package service;

import domain.JloggArticleContent;
import domain.JloggArticleProfile;
import domain.JloggTimeBar;

import java.util.List;

public interface ArticleService {
    List<JloggArticleProfile> flushProfileByLimit(int currentCount, int count);

    JloggArticleContent findContentByAid(int aid);

    JloggArticleProfile findProfileByAid(int aid);

    List<JloggArticleProfile> flushAllProfile();

    boolean removeArticleByAid(int aid);

    List<JloggTimeBar> flushArchives();

    List<JloggArticleProfile> flushProfileByArchive(int year, int month, int currentCount, int count);

    int createArticleProfile(String title, String username, String shown);

    boolean createArticleContent(int aid, String description,String path);

    int updateArticleProfile(int aid, String title, String username, String shown);

    boolean updateArticleContent(int aid, String description, String path);

    void addOneView(int aid);
}
