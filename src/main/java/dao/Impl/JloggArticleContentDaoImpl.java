package dao.Impl;

import dao.JloggArticleContentDao;
import domain.JloggArticleContent;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

public class JloggArticleContentDaoImpl implements JloggArticleContentDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public JloggArticleContent findContentByAid(int aid) {
        JloggArticleContent content = null;
        String sql = "SELECT aid,description,path FROM blog_articles_content WHERE aid=?";
        try {
            content = template.queryForObject(sql, new BeanPropertyRowMapper<>(JloggArticleContent.class), aid);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return content;
    }

    @Override
    public boolean createContent(int aid, String description, String path) {
        boolean flag = false;
        try {
            String sql = "INSERT INTO blog_articles_content VALUES (?,?,?)";
            template.update(sql, aid, description, path);
            flag = true;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateContent(int aid, String description, String path) {
        boolean flag = false;
        try {
            String sql = "UPDATE blog_articles_content SET description=?, path=? WHERE aid=?";
            template.update(sql, description, path, aid);
            flag = true;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return flag;
    }
}
