package tickets.rowMapper.user;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.user.UserOdSeat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOdSeatRowMapper implements RowMapper<UserOdSeat> {
	
	@Override
	public UserOdSeat mapRow(ResultSet rs, int i) throws SQLException{
		UserOdSeat userOdSeat = new UserOdSeat();
		userOdSeat.setOdID( rs.getString("OdID") );
		userOdSeat.setPlanID( rs.getString("planID") );
		userOdSeat.setRow( rs.getInt("row") );
		userOdSeat.setCol( rs.getInt("col") );
		userOdSeat.setPrice( rs.getDouble("price") );
		return userOdSeat;
	}
	
}
