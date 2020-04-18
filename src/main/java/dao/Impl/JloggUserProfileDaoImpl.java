package dao.Impl;

import dao.JloggUserProfileDao;
import domain.JloggUser;
import domain.JloggUserProfile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

public class JloggUserProfileDaoImpl implements JloggUserProfileDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * admin information display
     * @return JloggUserProfile
     */
    @Override
    public JloggUserProfile findAdminProfile() {
        JloggUserProfile jloggUserProfile = null;
        try {
            String sql = "SELECT * FROM blog_user_profile WHERE uid = ?";
            jloggUserProfile = template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(JloggUserProfile.class), "admin");

        }catch (EmptyResultDataAccessException e){
            return null;
        }
        return jloggUserProfile;
    }
}
