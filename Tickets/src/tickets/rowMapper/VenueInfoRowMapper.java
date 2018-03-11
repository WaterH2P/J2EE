package tickets.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.VenueInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueInfoRowMapper implements RowMapper<VenueInfo>{
	
	@Override
	public VenueInfo mapRow(ResultSet rs, int i) throws SQLException{
		VenueInfo venueInfo = new VenueInfo();
		venueInfo.setVenueID( rs.getString("venueID") );
		venueInfo.setProvince( rs.getString("province") );
		venueInfo.setCity( rs.getString("city") );
		venueInfo.setAddress( rs.getString("address") );
		venueInfo.setTelephone( rs.getString("telephone") );
		venueInfo.setIsChanging(rs.getBoolean("isChanging"));
		return venueInfo;
	}
	
}
