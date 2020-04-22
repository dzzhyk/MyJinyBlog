package service.Impl;

import dao.Impl.JloggArticleContentDaoImpl;
import dao.Impl.JloggArticleProfileDaoImpl;
import dao.Impl.JloggTimeBarDaoImpl;
import dao.JloggArticleContentDao;
import dao.JloggArticleProfileDao;
import dao.JloggTimeBarDao;
import domain.JloggArticleContent;
import domain.JloggArticleProfile;
import domain.JloggTimeBar;
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

    @Override
    public boolean removeArticleByAid(int aid) {
        JloggArticleProfileDao dao = new JloggArticleProfileDaoImpl();
        return dao.removeProfileByAid(aid);
    }

    @Override
    public List<JloggTimeBar> flushArchives() {
        JloggTimeBarDao dao = new JloggTimeBarDaoImpl();
        return dao.findYearAndMonthList();
    }
}
