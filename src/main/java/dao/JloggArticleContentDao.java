package dao;

import domain.JloggArticleContent;

public interface JloggArticleContentDao {
    JloggArticleContent findContentByAid(int aid);

    boolean createContent(int aid, String description, String path);

    boolean updateContent(int aid, String description, String path);
}
