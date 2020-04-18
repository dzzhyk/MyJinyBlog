package dao.Impl;

import dao.JloggGeneralDao;
import domain.JloggGeneral;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

public class JloggGeneralDaoImpl implements JloggGeneralDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

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

    @Override
    public boolean updateGeneral(JloggGeneral general) {
        boolean flag = false;
        String sql =
                "UPDATE blog_general SET title=?, name=?, description=?, htmlDescription=?, htmlCopyright=?, beian=?";
        try {
            template.update(sql,
                    general.getTitle(),
                    general.getName(),
                    general.getDescription(),
                    general.getHtmlDescription(),
                    general.getHtmlCopyright(),
                    general.getBeian());
            flag = true;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return flag;
    }
}
