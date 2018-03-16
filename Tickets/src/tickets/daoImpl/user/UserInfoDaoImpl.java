package tickets.daoImpl.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.user.UserInfoDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.user.UserInfo;
import tickets.rowMapper.user.UserInfoRowMapper;

@Repository( "userInfoDao" )
public class UserInfoDaoImpl implements UserInfoDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
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
