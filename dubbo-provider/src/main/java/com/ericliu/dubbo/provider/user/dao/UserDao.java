package com.ericliu.dubbo.provider.user.dao;

import com.ericliu.dubbo.api.user.dto.UserDTO;
import com.ericliu.dubbo.provider.user.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.*;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/3
 */
@Component
public class UserDao extends JdbcDaoSupport {

    private static final String INSERT_SQL = "INSERT INTO user(name) VALUES (?);";

    @Resource(name="userJdbcTemplate")
    public void setJdbcTempalte( JdbcTemplate jdbcTemplate) {
        super.setJdbcTemplate(jdbcTemplate);
    }

    public User insertUser(User user) {
        KeyHolder key = new GeneratedKeyHolder();

        getJdbcTemplate().update(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getName());
            return pstmt;
        }, key);

        user.setId(key.getKey().longValue());
        return user;
//        return getJdbcTemplate().update("", new Object[]{user.getId(), user.getName()},
//                new int[]{Types.BIGINT, java.sql.Types.VARCHAR});
    }

    class UserRowMapper implements RowMapper<UserDTO> {
        //rs为返回结果集，以每行为单位封装着
        @Override
        public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            UserDTO user = new UserDTO();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            return user;
        }
    }

}
