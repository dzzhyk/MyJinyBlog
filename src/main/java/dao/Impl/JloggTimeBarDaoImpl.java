package dao.Impl;

import dao.JloggTimeBarDao;
import domain.JloggTimeBar;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;

public class JloggTimeBarDaoImpl implements JloggTimeBarDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<JloggTimeBar> findYearAndMonthList() {
        List<JloggTimeBar> list = null;
        try {
            String sql1 = "SELECT DISTINCT YEAR(time) AS year, MONTH(time) AS month FROM blog_articles_profile ORDER BY year DESC, month DESC";
            list = template.query(sql1, new BeanPropertyRowMapper<>(JloggTimeBar.class));

            String sql2 = "SELECT COUNT(*) FROM blog_articles_profile WHERE YEAR(time)=? AND MONTH(time)=? AND shown=?";
            Integer temp = 0;
            for (int i = 0; i < list.size(); i++) {
                temp = template.queryForObject(sql2, Integer.class, list.get(i).getYear(), list.get(i).getMonth(),"Y");
                list.get(i).setCount(temp.intValue());
            }
        }catch (DataAccessException | NullPointerException e){
            e.printStackTrace();
        }
        return list;
    }
}
