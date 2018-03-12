package tickets.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.VenueBaseInfoChange;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueInfoChangeRowMapper implements RowMapper<VenueBaseInfoChange> {
	
	@Override
	public VenueBaseInfoChange mapRow(ResultSet rs, int i) throws SQLException{
		VenueBaseInfoChange venueInfo = new VenueBaseInfoChange();
		venueInfo.setVenueID( rs.getString("venueID") );
		venueInfo.setProvince( rs.getString("province") );
		venueInfo.setCity( rs.getString("city") );
		venueInfo.setAddress( rs.getString("address") );
		venueInfo.setTelephone( rs.getString("telephone") );
		return venueInfo;
	}
	
}
