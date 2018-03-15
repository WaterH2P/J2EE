package tickets.rowMapper.venue;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.venue.VenueHallSeat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueHallSeatRowMapper implements RowMapper<VenueHallSeat> {
	
	@Override
	public VenueHallSeat mapRow(ResultSet rs, int i) throws SQLException{
		VenueHallSeat venueHall = new VenueHallSeat();
		venueHall.setHallID( rs.getString("hallID") );
		venueHall.setRow( rs.getInt("row") );
		venueHall.setCol( rs.getInt("col") );
		venueHall.setSeatID( rs.getString("seatID") );
		venueHall.setState( rs.getString("state") );
		return venueHall;
	}
	
}
