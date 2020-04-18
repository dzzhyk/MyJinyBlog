package dao.Impl;

import dao.JloggGeneralDao;
import domain.JloggGeneral;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

public class JloggGeneralDaoImpl implements JloggGeneralDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public JloggGeneral findGeneral() {

        JloggGeneral general = null;
        String sql = "SELECT * FROM blog_general";
        try {
            general = template.queryForObject(sql, new BeanPropertyRowMapper<>(JloggGeneral.class));
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return general;
    }
}
