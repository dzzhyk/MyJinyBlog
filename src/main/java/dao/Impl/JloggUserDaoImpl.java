package dao.Impl;

import dao.JloggUserDao;
import domain.JloggUser;
import org.springframework.dao.DataAccessException;
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
            String sql = "SELECT * FROM blog_user WHERE username = ? and passwd = ?";
            jloggUser = template.queryForObject(sql, new BeanPropertyRowMapper<JloggUser>(JloggUser.class), username, passwd);

        }catch (EmptyResultDataAccessException e){
            return null;
        }
        return jloggUser;
    }

    @Override
    public boolean updateAccount(JloggUser user) {
        boolean flag = false;
        String sql =
                "UPDATE blog_user SET username=?, passwd=? WHERE uid=?";
        try {
            template.update(sql, user.getUsername(), user.getPasswd(),user.getUid());
            flag = true;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return flag;
    }
}
