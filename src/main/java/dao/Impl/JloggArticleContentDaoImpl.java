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
}
