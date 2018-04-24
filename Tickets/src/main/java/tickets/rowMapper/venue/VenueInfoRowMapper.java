package tickets.rowMapper.venue;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.venue.VenueBaseInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueInfoRowMapper implements RowMapper<VenueBaseInfo>{
	
	@Override
	public VenueBaseInfo mapRow(ResultSet rs, int i) throws SQLException{
		VenueBaseInfo venueBaseInfo = new VenueBaseInfo();
		venueBaseInfo.setVenueID( rs.getString("venueID") );
		venueBaseInfo.setProvince( rs.getString("province") );
		venueBaseInfo.setCity( rs.getString("city") );
		venueBaseInfo.setAddress( rs.getString("address") );
		venueBaseInfo.setTelephone( rs.getString("telephone") );
		venueBaseInfo.setIsChanging(rs.getBoolean("isChanging"));
		return venueBaseInfo;
	}
	
}
