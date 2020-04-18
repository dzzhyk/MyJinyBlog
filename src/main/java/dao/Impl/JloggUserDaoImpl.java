package dao.Impl;

import dao.JloggUserDao;
import domain.JloggUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import javax.sql.DataSource;

public class JloggUserDaoImpl implements JloggUserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public JloggUser findUser(String username, String passwd) {
        JloggUser jloggUser = null;
        try {
            String sql = "SELECT username, passwd FROM blog_user WHERE username = ? and passwd = ?";
            jloggUser = template.queryForObject(sql, new BeanPropertyRowMapper<JloggUser>(JloggUser.class), username, passwd);

        }catch (EmptyResultDataAccessException e){
            return null;
        }
        return jloggUser;
    }
}
