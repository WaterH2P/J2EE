package tickets.daoImpl.user;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.user.UserOdDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.user.UserOd;
import tickets.model.user.UserOdSeat;
import tickets.rowMapper.user.UserOdIDRowMapper;
import tickets.rowMapper.user.UserOdRowMapper;
import tickets.rowMapper.user.UserOdSeatRowMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("userOdDao")
public class UserOdDaoImpl implements UserOdDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void insertNewUserOdSeated(UserOd userOd, List<UserOdSeat> userOdSeats){
		final boolean isSeated = true;
		userOd.setSeated(isSeated);
		insertNewUserOd(userOd);
		
		String insertOdSeatSql = "INSERT INTO " + ParaName.Table_userOdSeat + " VALUES (?,?,?,?,?)";
		jdbcTemplate.batchUpdate(insertOdSeatSql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				UserOdSeat userOdSeat = userOdSeats.get(i);
				ps.setString(1, userOdSeat.getOdID());
				ps.setString(2, userOdSeat.getPlanID());
				ps.setInt(3, userOdSeat.getRow());
				ps.setInt(4, userOdSeat.getCol());
				ps.setDouble(5, userOdSeat.getPrice());
			}

			@Override
			public int getBatchSize(){
				return userOdSeats.size();
			}
		});
	}
	
	@Override
	public void insertNewUserOdUnseated(UserOd userOd){
		final boolean isSeated = false;
		userOd.setSeated(isSeated);
		insertNewUserOd(userOd);
	}
	
	private void insertNewUserOd(UserOd userOd){
		String OdID = userOd.getOdID();
		String email = userOd.getEmail();
		String planID = userOd.getPlanID();
		int numOfTicket = userOd.getNumOfTicket();
		double totalPrice = userOd.getTotalPrice();
		final double vipDiscount = 0.0;
		final int couponDiscount = 0;
		final double pay = 0;
		Date makeTime = userOd.getMakeTime();
		final boolean isPaid = false;
		final boolean isTimeout = false;
		final boolean isDeleted = false;
		boolean isSeated = userOd.isSeated();
		final boolean isChecked = false;
		boolean isOnline = userOd.isOnline();
		
		String insertNewOdSql = "INSERT INTO " + ParaName.Table_userOd + " VALUES (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)";
		jdbcTemplate.update(insertNewOdSql, OdID, email, planID, numOfTicket, totalPrice, vipDiscount, couponDiscount,
				pay, makeTime, isPaid, isTimeout, isDeleted, isSeated, isChecked, isOnline);
	}
	
	@Override
	public UserOd selectUserOdInfo(String OdID){
		String sql = "SELECT * FROM " + ParaName.Table_userOd + " WHERE OdID=?";
		UserOd userOd = (UserOd) jdbcTemplate.queryForObject(sql, new UserOdRowMapper(), OdID);
		return userOd;
	}
	
	@Override
	public List<String> selectAllUserOdIDs(String email){
		String sql = "SELECT OdID FROM " + ParaName.Table_userOd + " WHERE email=?";
		List<String> OdIDs = jdbcTemplate.query(sql, new UserOdIDRowMapper(), email);
		return OdIDs;
	}
	
	@Override
	public List<UserOdSeat> selectPlanAllOdSeat(String planID){
		String sql = "SELECT * FROM " + ParaName.Table_userOdSeat + " WHERE planID=?";
		List<UserOdSeat> userOdSeats = jdbcTemplate.query(sql, new UserOdSeatRowMapper(), planID);
		return userOdSeats;
	}
	
	@Override
	public List<UserOdSeat> selectUserOdAllSeatSelectedInfo(String OdID){
		String sql = "SELECT * FROM " + ParaName.Table_userOdSeat + " WHERE OdID=?";
		List<UserOdSeat> userOdSeats = jdbcTemplate.query(sql, new UserOdSeatRowMapper(), OdID);
		return userOdSeats;
	}
	
	@Override
	public void updateUserOdIsPaid(String OdID, double vipDiscount, int couponDiscount, double totalPay){
		final boolean isPaid = true;
		String sql = "UPDATE " + ParaName.Table_userOd +
				" SET vipDiscount=?, couponDiscount=?, totalPay=?, isPaid=? WHERE OdID=?";
		jdbcTemplate.update(sql, vipDiscount, couponDiscount, totalPay, isPaid, OdID);
	}
	
	@Override
	public List<UserOd> selectAllHistoricalUserOd(String email){
		Date date = new Date();
		String now = sdf.format(date);
		String sql = "SELECT * FROM " + ParaName.Table_userOd + " AS Od," +
				"(SELECT planID, beginTime FROM " + ParaName.Table_venuePlan + ")AS plan" +
				" WHERE email=? AND Od.planID=plan.planID" +
					" AND isTimeOut=FALSE AND isDeleted=FALSE" +
					" AND beginTime<?";
		List<UserOd> userOds = jdbcTemplate.query(sql, new UserOdRowMapper(), email, now);
		return userOds;
	}
	
	@Override
	public List<UserOd> selectAllUserOdTimeout(String email){
		String sql = "SELECT * FROM " + ParaName.Table_userOd + " WHERE email=? AND isTimeOut=TRUE ";
		List<UserOd> userOds = jdbcTemplate.query(sql, new UserOdRowMapper(), email);
		return userOds;
	}
	
	@Override
	public List<UserOd> selectAllUserOdDeleted(String email){
		String sql = "SELECT * FROM " + ParaName.Table_userOd + " WHERE email=? AND isDeleted=TRUE ";
		List<UserOd> userOds = jdbcTemplate.query(sql, new UserOdRowMapper(), email);
		return userOds;
	}
	
	@Override
	public List<UserOd> selectAllFutureUserOd(String email){
		Date date = new Date();
		String now = sdf.format(date);
		String sql = "SELECT * FROM " + ParaName.Table_userOd + " AS Od," +
				"(SELECT planID, beginTime FROM " + ParaName.Table_venuePlan + ")AS plan" +
				" WHERE email=? AND Od.planID=plan.planID" +
				" AND isPaid=TRUE AND isDeleted=FALSE" +
				" AND beginTime>?";
		List<UserOd> userOds = jdbcTemplate.query(sql, new UserOdRowMapper(), email, now);
		return userOds;
	}
	
	@Override
	public List<UserOd> selectAllUserOdUnfinished(String email){
		String sql = "SELECT * FROM " + ParaName.Table_userOd + " WHERE email=?" +
				" AND isPaid=FALSE AND isTimeOut=FALSE AND isDeleted=FALSE";
		List<UserOd> userOds = jdbcTemplate.query(sql, new UserOdRowMapper(), email);
		return userOds;
	}
	
}
