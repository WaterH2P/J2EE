package tickets.rowMapper.venue;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.venue.VenuePlanSeat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenuePlanSeatRowMapper   implements RowMapper<VenuePlanSeat> {
	
	@Override
	public VenuePlanSeat mapRow(ResultSet rs, int i) throws SQLException{
		VenuePlanSeat venuePlanSeat = new VenuePlanSeat();
		venuePlanSeat.setHallID( rs.getString("hallID") );
		venuePlanSeat.setRow( rs.getInt("row") );
		venuePlanSeat.setCol( rs.getInt("col") );
		venuePlanSeat.setPercent( rs.getInt("percent") );
		return venuePlanSeat;
	}
	
}