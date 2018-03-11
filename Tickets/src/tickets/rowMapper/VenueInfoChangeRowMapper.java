package tickets.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.VenueInfoChange;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueInfoChangeRowMapper implements RowMapper<VenueInfoChange> {
	
	@Override
	public VenueInfoChange mapRow(ResultSet rs, int i) throws SQLException{
		VenueInfoChange venueInfo = new VenueInfoChange();
		venueInfo.setVenueID( rs.getString("venueID") );
		venueInfo.setProvince( rs.getString("province") );
		venueInfo.setCity( rs.getString("city") );
		venueInfo.setAddress( rs.getString("address") );
		venueInfo.setTelephone( rs.getString("telephone") );
		return venueInfo;
	}
	
}
