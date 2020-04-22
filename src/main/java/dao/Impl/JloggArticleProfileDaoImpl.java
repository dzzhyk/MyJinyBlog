package dao.Impl;

import dao.JloggArticleProfileDao;
import domain.JloggArticleProfile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;

public class JloggArticleProfileDaoImpl implements JloggArticleProfileDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<JloggArticleProfile> findProfilesByLimit(int currentCount, int count) {
        List<JloggArticleProfile> list = null;
        String sql = "SELECT aid,time,title,author,views,shown FROM blog_articles_profile Limit ?, ?";
        try {
             list = template.query(sql, new BeanPropertyRowMapper<>(JloggArticleProfile.class), currentCount, count);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public JloggArticleProfile findProfileByAid(int aid) {
        JloggArticleProfile profile = null;
        String sql = "SELECT aid,time,title,author,views,shown FROM blog_articles_profile WHERE aid=?";
        try {
            profile = template.queryForObject(sql, new BeanPropertyRowMapper<>(JloggArticleProfile.class), aid);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return profile;
    }

    @Override
    public List<JloggArticleProfile> findAllProfiles() {
        List<JloggArticleProfile> list = null;
        String sql = "SELECT * FROM blog_articles_profile";
        try {
            list = template.query(sql, new BeanPropertyRowMapper<>(JloggArticleProfile.class));
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean removeProfileByAid(int aid) {
        boolean flag = false;
        String sql = "DELETE FROM blog_articles_profile WHERE aid=?";
        try {
            template.update(sql, aid);
            flag = true;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<JloggArticleProfile> removeProfileByArchive(int year, int month, int currentCount, int count) {
        List<JloggArticleProfile> list = null;
        String sql =
                "SELECT aid,time,title,author,views,shown FROM blog_articles_profile WHERE YEAR(time)=? AND MONTH(time)=? Limit ?, ?";
        try {
            list = template.query(sql, new BeanPropertyRowMapper<>(JloggArticleProfile.class), year, month, currentCount, count);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return list;
    }
}
