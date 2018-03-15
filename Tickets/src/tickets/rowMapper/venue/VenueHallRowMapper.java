package tickets.rowMapper.venue;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.venue.VenueHall;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueHallRowMapper implements RowMapper<VenueHall> {
	
	@Override
	public VenueHall mapRow(ResultSet rs, int i) throws SQLException{
		VenueHall venueHall = new VenueHall();
		venueHall.setHallID( rs.getString("hallID") );
		venueHall.setVenueID( rs.getString("venueID") );
		venueHall.setName( rs.getString("name") );
		venueHall.setNumOfRow( rs.getInt("numOfRow") );
		venueHall.setNumOfCol( rs.getInt("numOfCol") );
		venueHall.setSeatDist( rs.getString("seatDist") );
		return venueHall;
	}
	
}