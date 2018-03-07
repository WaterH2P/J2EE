package tickets.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.model.UserInfo;
import tickets.rowMapper.UserInfoRowMapper;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	private JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public UserInfo selectUserInfo(String email){
		String sql = "SELECT * FROM " + ParaName.Table_userInfo + " WHERE email=?";
		UserInfo userInfo = (UserInfo) jdbcTemplate.queryForObject(sql, new UserInfoRowMapper(), email);
		return userInfo;
	}
	
	@Override
	public void updateUserName(String email, String name){
		String sql = "UPDATE " + ParaName.Table_userInfo + " SET name=? WHERE email=?";
		jdbcTemplate.update(sql, name, email);
	}
	
}
