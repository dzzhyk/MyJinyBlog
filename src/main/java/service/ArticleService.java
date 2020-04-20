package service;

import domain.JloggArticleContent;
import domain.JloggArticleProfile;

import java.util.List;

public interface ArticleService {
    List<JloggArticleProfile> flushProfileByLimit(int currentCount, int count);

    JloggArticleContent findContentByAid(int aid);

    JloggArticleProfile findProfileByAid(int aid);

    List<JloggArticleProfile> flushAllProfile();
}
