package service.Impl;

import dao.Impl.JloggArticleContentDaoImpl;
import dao.Impl.JloggArticleProfileDaoImpl;
import dao.JloggArticleContentDao;
import dao.JloggArticleProfileDao;
import domain.JloggArticleContent;
import domain.JloggArticleProfile;
import service.ArticleService;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    @Override
    public List<JloggArticleProfile> flushProfileByLimit(int currentCount, int count) {
        JloggArticleProfileDao dao = new JloggArticleProfileDaoImpl();
        return dao.findProfilesByLimit(currentCount, count);
    }

    @Override
    public JloggArticleContent findContentByAid(int aid) {
        JloggArticleContentDao dao = new JloggArticleContentDaoImpl();
        return dao.findContentByAid(aid);
    }

    @Override
    public JloggArticleProfile findProfileByAid(int aid) {
        JloggArticleProfileDao dao = new JloggArticleProfileDaoImpl();
        return dao.findProfileByAid(aid);
    }

    @Override
    public List<JloggArticleProfile> flushAllProfile() {
        JloggArticleProfileDao dao = new JloggArticleProfileDaoImpl();
        return dao.findAllProfiles();
    }
}
