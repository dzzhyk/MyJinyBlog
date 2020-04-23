package dao.Impl;

import dao.JloggArticleProfileDao;
import domain.JloggArticleProfile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class JloggArticleProfileDaoImpl implements JloggArticleProfileDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<JloggArticleProfile> findProfilesByLimit(int currentCount, int count) {
        List<JloggArticleProfile> list = null;
        String sql = "SELECT aid,time,title,author,views,shown FROM blog_articles_profile WHERE shown= ? Limit ?, ?";
        try {
             list = template.query(sql, new BeanPropertyRowMapper<>(JloggArticleProfile.class),"Y", currentCount, count);
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
    public JloggArticleProfile findProfileByTitle(String title) {
        JloggArticleProfile profile = null;
        String sql = "SELECT aid,time,title,author,views,shown FROM blog_articles_profile WHERE title=?";
        try {
            profile = template.queryForObject(sql, new BeanPropertyRowMapper<>(JloggArticleProfile.class), title);
        }catch (DataAccessException e){
            throw e;
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
    public List<JloggArticleProfile> listProfileByArchive(int year, int month, int currentCount, int count) {
        List<JloggArticleProfile> list = null;
        String sql =
                "SELECT aid,time,title,author,views,shown FROM blog_articles_profile WHERE shown=? AND YEAR(time)=? AND MONTH(time)=? Limit ?, ?";
        try {
            list = template.query(sql, new BeanPropertyRowMapper<>(JloggArticleProfile.class),"Y", year, month, currentCount, count);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public int createProfile(String title, String username, String shown) {
        try {
            // 先判断题目是否已经存在
            if (findProfileByTitle(title) != null){
                return -1;
            }
        }catch (EmptyResultDataAccessException e){
            String sql = "INSERT INTO blog_articles_profile(time, title, author, views, shown) VALUES(?,?,?,?,?)";
            final LocalDate now = LocalDate.now();
            template.update(sql, now.toString(), title, username, 0, shown);
            return findProfileByTitle(title).getAid();
        }
        return -1;
    }

    @Override
    public int updateProfile(int aid, String title, String username, String shown) {
        try {
            String sql = "UPDATE blog_articles_profile SET time=?,title=?, author=?, views=?, shown=? WHERE aid=?";
            final LocalDate now = LocalDate.now();
            int views = findViewsByAid(aid);
            int result = template.update(sql, now.toString(), title, username, views, shown, aid);
            if (result == 1){
                return aid;
            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int findViewsByAid(int aid) {
        Integer ans = 0;
        try {
            String sql = "SELECT views FROM blog_articles_profile WHERE aid=?";
            ans = template.queryForObject(sql, Integer.class, aid);
            if (ans!=null){
                return ans;
            }
        }catch (EmptyResultDataAccessException e){
            return 0;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return 0;
    }
}
