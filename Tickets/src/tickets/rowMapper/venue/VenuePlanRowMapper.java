package tickets.rowMapper.venue;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.venue.VenuePlan;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VenuePlanRowMapper implements RowMapper<VenuePlan> {
	
	@Override
	public VenuePlan mapRow(ResultSet rs, int i) throws SQLException{
		VenuePlan venuePlan = new VenuePlan();
		venuePlan.setPlanID( rs.getString("planID") );
		venuePlan.setVenueID( rs.getString("venueID") );
		venuePlan.setName( rs.getString("name") );
		venuePlan.setType( rs.getString("type") );
		venuePlan.setBeginTime( rs.getTimestamp("beginTime" ) );
		venuePlan.setEndTime( rs.getTimestamp("endTime" ) );
		venuePlan.setHallID( rs.getString("hallID") );
		venuePlan.setHallName( rs.getString("hallName") );
		venuePlan.setNumOfRow(rs.getInt("numOfRow"));
		venuePlan.setNumOfCol(rs.getInt("numOfCol"));
		venuePlan.setNumOfTicket(rs.getInt("numOfTicket"));
		venuePlan.setNumOfTLeft(rs.getInt("numOfTLeft"));
		venuePlan.setNumOfTSeated(rs.getInt("numOfTSeated"));
		venuePlan.setNumOfTUnallocated(rs.getInt("numOfTUnallocated"));
		venuePlan.setPrice(rs.getDouble("price"));
		venuePlan.setDescription( rs.getString("description") );
		venuePlan.setSeatDist( rs.getString("seatDist") );
		return venuePlan;
	}
	
}
