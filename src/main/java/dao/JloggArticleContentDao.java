package dao;

import domain.JloggArticleContent;

public interface JloggArticleContentDao {
    JloggArticleContent findContentByAid(int aid);
}
