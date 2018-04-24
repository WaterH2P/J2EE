package tickets.daoImpl.venue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.venue.VenueSeatLevelDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.venue.VenueSeatLevel;
import tickets.rowMapper.venue.VenueSeatLevelRowMapper;

import java.util.List;

@Repository( "venueSeatLevelDao" )
public class VenueSeatLevelDaoImpl implements VenueSeatLevelDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public boolean insertSeatLevel(VenueSeatLevel venueSeatLevel){
		String sql = "INSERT INTO " + ParaName.Table_venueSeatLevel + " VALUES (?,?,?,?,?)";
		String seatID = venueSeatLevel.getSeatID();
		String venueID = venueSeatLevel.getVenueID();
		String name = venueSeatLevel.getName();
		int percent = venueSeatLevel.getPercent();
		final boolean isDeleted = false;
		jdbcTemplate.update(sql, seatID, venueID, name, percent, isDeleted);
		return true;
	}
	
	@Override
	public void deleteSeatLevel(String seatID){
		String sql = "UPDATE " + ParaName.Table_venueSeatLevel + " SET isDeleted=TRUE WHERE seatID=?";
		jdbcTemplate.update(sql, seatID);
	}
	
	@Override
	public List<VenueSeatLevel> selectAllSeatLevels(String venueID){
		String sql = "SELECT * FROM " + ParaName.Table_venueSeatLevel + " WHERE venueID=? AND isDeleted=FALSE ";
		List<VenueSeatLevel> venueSeatLevels = jdbcTemplate.query(sql, new VenueSeatLevelRowMapper(), venueID);
		return venueSeatLevels;
	}
}
