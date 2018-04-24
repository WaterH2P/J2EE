package tickets.rowMapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailRowMapper implements RowMapper<String> {
	
	@Override
	public String mapRow(ResultSet rs, int i) throws SQLException{
		// get results from ResultSet
		String email = rs.getString("email");
		return email;
	}
	
}
