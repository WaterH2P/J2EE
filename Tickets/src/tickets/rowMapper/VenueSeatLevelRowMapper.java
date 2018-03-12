package tickets.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.VenueBaseInfo;
import tickets.model.VenueSeatLevel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueSeatLevelRowMapper  implements RowMapper<VenueSeatLevel> {
	
	@Override
	public VenueSeatLevel mapRow(ResultSet rs, int i) throws SQLException{
		VenueSeatLevel venueSeatLevel = new VenueSeatLevel();
		venueSeatLevel.setSeatID( rs.getString("seatID") );
		venueSeatLevel.setVenueID( rs.getString("venueID") );
		venueSeatLevel.setName( rs.getString("name") );
		venueSeatLevel.setPrice( rs.getDouble("price") );
		return venueSeatLevel;
	}
	
}