package tickets.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.model.VenueInfo;
import tickets.rowMapper.VenueIDRowMapper;

import java.util.List;

@Repository("venueAccountDao")
public class VenueAccountDaoImpl implements VenueAccountDao {
	
	private JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public List<String> getAllVenusIDs(){
		String sql = "SELECT venueID FROM " + ParaName.Table_venueAccount;
		List<String> venueIDs = jdbcTemplate.query(sql, new VenueIDRowMapper());
		return venueIDs;
	}
	
	@Override
	public void addAccount(VenueInfo venueInfo, String password){
		String venueID = venueInfo.getVenueID();
		
		String accountSql = "INSERT INTO " + ParaName.Table_venueAccount + " VALUES (?,?,?)";
		final boolean isConfirmed = false;
		jdbcTemplate.update(accountSql, venueID, password, isConfirmed);
		
		String infoSql = "INSERT INTO " + ParaName.Table_venueInfo + " VALUES (?,?,?,?,?)";
		jdbcTemplate.update(infoSql, venueID, venueInfo.getProvince(),
				venueInfo.getCity(), venueInfo.getAddress(), venueInfo.getTelephone());
	}
	
}
