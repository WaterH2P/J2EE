package tickets.rowMapper.venue;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenuePlanIDRowMapper implements RowMapper<String> {
	
	@Override
	public String mapRow(ResultSet resultSet, int i) throws SQLException{
		return resultSet.getString("planID");
	}
	
}