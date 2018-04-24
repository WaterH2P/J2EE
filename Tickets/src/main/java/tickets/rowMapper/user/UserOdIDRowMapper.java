package tickets.rowMapper.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOdIDRowMapper implements RowMapper<String> {
	
	@Override
	public String mapRow(ResultSet resultSet, int i) throws SQLException{
		return resultSet.getString("OdID");
	}
	
}
