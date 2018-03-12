package tickets.daoImpl.venue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.venue.VenueSeatLevelDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.VenueSeatLevel;
import tickets.rowMapper.VenueSeatLevelRowMapper;

import java.util.List;

@Repository( "venueSeatLevelDao" )
public class VenueSeatLevelDaoImpl implements VenueSeatLevelDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public boolean insertSeatLevel(VenueSeatLevel venueSeatLevel){
		String sql = "INSERT INTO " + ParaName.Table_venueSeatLevel + " VALUES (?,?,?,?)";
		String seatID = venueSeatLevel.getSeatID();
		String venueID = venueSeatLevel.getVenueID();
		String name = venueSeatLevel.getName();
		double price = venueSeatLevel.getPrice();
		jdbcTemplate.update(sql, seatID, venueID, name, price);
		return true;
	}
	
	@Override
	public boolean deleteSeatLevel(String seatID){
		String sql = "DELETE FROM " + ParaName.Table_venueSeatLevel + " WHERE seatID=?";
		jdbcTemplate.update(sql, seatID);
		return true;
	}
	
	@Override
	public List<VenueSeatLevel> selectAllSeatLevels(String venueID){
		String sql = "SELECT * FROM " + ParaName.Table_venueSeatLevel + " WHERE venueID=?";
		List<VenueSeatLevel> venueSeatLevels = jdbcTemplate.query(sql, new VenueSeatLevelRowMapper(), venueID);
		return venueSeatLevels;
	}
}
