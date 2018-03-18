package tickets.daoImpl.venue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.venue.VenueHallDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.venue.VenueHall;
import tickets.model.venue.VenueHallSeat;
import tickets.model.venue.VenuePlanSeat;
import tickets.rowMapper.venue.VenueHallIDRowMapper;
import tickets.rowMapper.venue.VenueHallRowMapper;
import tickets.rowMapper.venue.VenuePlanSeatRowMapper;

import java.util.List;

@Repository("venueHallDao")
public class VenueHallDaoImpl implements VenueHallDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public boolean insertNewVenueHall(VenueHall venueHall, List<VenueHallSeat> venueHallSeats){
		String hallID = venueHall.getHallID();
		String venueID = venueHall.getVenueID();
		String name = venueHall.getName();
		int numOfRow = venueHall.getNumOfRow();
		int numOfCol = venueHall.getNumOfCol();
		String seatDist = venueHall.getSeatDist();
		boolean isDeleted = false;
		String insertNewHallSql = "INSERT INTO " + ParaName.Table_venueHall + " VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(insertNewHallSql, hallID, venueID, name, numOfRow, numOfCol, seatDist, isDeleted);
		
		for( VenueHallSeat venueHallSeat : venueHallSeats ){
			hallID = venueHallSeat.getHallID();
			int row = venueHallSeat.getRow();
			int col = venueHallSeat.getCol();
			String seatID = venueHallSeat.getSeatID();
			String state = venueHallSeat.getState();
			String insertHallSeatSql = "INSERT INTO " + ParaName.Table_venueHallSeat + " VALUES (?,?,?,?,?)";
			jdbcTemplate.update(insertHallSeatSql, hallID, row, col, seatID, state);
		}
		return true;
	}
	
	@Override
	public List<String> selectAllVenueHallIDs(String venueID){
		String sql = "SELECT hallID FROM " + ParaName.Table_venueHall + " WHERE venueID=?";
		List<String> hallIDs = jdbcTemplate.query(sql, new VenueHallIDRowMapper(), venueID);
		return hallIDs;
	}
	
	@Override
	public List<VenueHall> selectAllVenueHalls(String venueID){
		String sql = "SELECT * FROM " + ParaName.Table_venueHall + " WHERE venueID=? AND isDeleted=FALSE ";
		List<VenueHall> venueHalls = jdbcTemplate.query(sql, new VenueHallRowMapper(), venueID);
		return venueHalls;
	}
	
	@Override
	public void deleteVenueHall(String hallID){
		String sql = "UPDATE " + ParaName.Table_venueHall + " SET isDeleted=TRUE WHERE hallID=?";
		jdbcTemplate.update(sql, hallID);
	}
	
	@Override
	public VenueHall selectVenueHall(String hallID){
		String sql = "SELECT * FROM " + ParaName.Table_venueHall + " WHERE hallID=?";
		VenueHall venueHall = jdbcTemplate.queryForObject(sql, new VenueHallRowMapper(), hallID);
		return venueHall;
	}
	
	@Override
	public List<VenuePlanSeat> selectPlanHallSeatInfo(String hallID){
		String sql = "SELECT hallID, row, col, percent FROM " + ParaName.Table_venueHallSeat + " AS hall, " +
				ParaName.Table_venueSeatLevel + " AS seat WHERE hallID=? AND hall.seatID=seat.seatID";
		List<VenuePlanSeat> venuePlanSeats = jdbcTemplate.query(sql, new VenuePlanSeatRowMapper(), hallID);
		return venuePlanSeats;
	}
}
