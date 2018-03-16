package tickets.daoImpl.venue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.venue.VenuePlanDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.venue.VenuePlan;
import tickets.rowMapper.venue.VenuePlanIDRowMapper;
import tickets.rowMapper.venue.VenuePlanRowMapper;

import java.util.List;

@Repository("venuePlanDao")
public class VenuePlanDaoImpl implements VenuePlanDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public List<String> selectAllVenuePlanIDs(String venueID){
		String sql = "SELECT planID FROM " + ParaName.Table_venuePlan + " WHERE venueID=?";
		List<String> planIDs = jdbcTemplate.query(sql, new VenuePlanIDRowMapper(), venueID);
		return planIDs;
	}
	
	@Override
	public void insertNewVenuePlan(VenuePlan venuePlan){
		String sql = "INSERT INTO " + ParaName.Table_venuePlan + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, venuePlan.getPlanID(), venuePlan.getVenueID(), venuePlan.getName(),
				venuePlan.getType(), venuePlan.getBeginTime(), venuePlan.getEndTime(),
				venuePlan.getHallID(), venuePlan.getHallName(), venuePlan.getNumOfTicket(),
				venuePlan.getNumOfTLeft(), venuePlan.getNumOfTSeated(), venuePlan.getNumOfTUnallocated(),
				venuePlan.getPrice(), venuePlan.getDescription(), venuePlan.getSeatDist());
	}
	
	@Override
	public List<VenuePlan> selectAllVenuePlansByVenueID(String venueID){
		String sql = "SELECT * FROM " + ParaName.Table_venuePlan + " as plan," +
				" (SELECT hallID, numOfRow, numOfCol FROM " + ParaName.Table_venueHall + " WHERE venueID=?)as hall" +
				" WHERE plan.hallID=hall.hallID";
		List<VenuePlan> venuePlans = jdbcTemplate.query(sql, new VenuePlanRowMapper(), venueID);
		return venuePlans;
	}
	
	@Override
	public List<VenuePlan> selectAllVenuePlansByPlanName(String planName){
		String sql = "SELECT * FROM " + ParaName.Table_venuePlan + " as plan," +
				" (SELECT hallID, numOfRow, numOfCol FROM " + ParaName.Table_venueHall + ")as hall" +
				" WHERE plan.name LIKE '%" + planName + "%' AND plan.hallID=hall.hallID";
		List<VenuePlan> venuePlans = jdbcTemplate.query(sql, new VenuePlanRowMapper());
		return venuePlans;
	}
	
}
