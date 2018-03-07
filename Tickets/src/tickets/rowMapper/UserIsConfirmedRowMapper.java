package tickets.rowMapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIsConfirmedRowMapper implements RowMapper<Boolean> {
	
	@Override
	public Boolean mapRow(ResultSet resultSet, int i) throws SQLException{
		return resultSet.getBoolean("isConfirmed");
	}
	
}
