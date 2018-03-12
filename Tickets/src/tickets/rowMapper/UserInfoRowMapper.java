package tickets.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoRowMapper implements RowMapper {
	
	@Override
	public UserInfo mapRow(ResultSet rs, int i) throws SQLException{
		UserInfo userInfo = new UserInfo();
		userInfo.setEmail( rs.getString("email") );
		userInfo.setName( rs.getString("name") );
		userInfo.setVipLevel( rs.getString("vipLevel") );
		userInfo.setBalance( rs.getInt("balance") );
		userInfo.setPoint( rs.getInt("point") );
		return userInfo;
	}
	
}
