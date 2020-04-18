package dao.Impl;

import dao.JloggUserProfileDao;
import domain.JloggUser;
import domain.JloggUserProfile;
import org.springframework.dao.DataAccessException;
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

    @Override
    public boolean updateProfile(JloggUserProfile profile) {
        boolean flag = false;
        String sql =
                "UPDATE blog_user_profile SET selfdes=?, email=?, github=?, csdn=? WHERE uid=?";
        try {
            template.update(sql,
                    profile.getSelfdes(),
                    profile.getEmail(),
                    profile.getGithub(),
                    profile.getCsdn(),
                    profile.getUid());
            flag = true;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return flag;
    }
}
