package tickets.daoImpl.venue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.venue.VenueHallDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.VenueHall;
import tickets.rowMapper.VenueHallIDRowMapper;

import java.util.List;
import java.util.Map;

@Repository("venueHallDao")
public class VenueHallDaoImpl implements VenueHallDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public boolean insertNewHall(VenueHall venueHall, Map<Integer, String> seatRowLevel){
		String hallID = venueHall.getHallID();
		String venueID = venueHall.getVenueID();
		String name = venueHall.getName();
		int numOfRow = venueHall.getNumOfRow();
		int numOfCol = venueHall.getNumOfCol();
		String seatDist = venueHall.getSeatDist();
		String insertNewHallSql = "INSERT INTO " + ParaName.Table_venueHall + " VALUES (?,?,?,?,?,?)";
		jdbcTemplate.update(insertNewHallSql, hallID, venueID, name, numOfRow, numOfCol, seatDist);
		return true;
	}
	
	@Override
	public List<String> selectAllVenueHallIDs(String venueID){
		String sql = "SELECT hallID FROM " + ParaName.Table_venueHall + " WHERE venueID=?";
		List<String> hallIDs = jdbcTemplate.query(sql, new VenueHallIDRowMapper(), venueID);
		return hallIDs;
	}
}
