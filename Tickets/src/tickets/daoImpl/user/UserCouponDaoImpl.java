package tickets.daoImpl.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.user.UserCouponDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.user.UserCoupon;
import tickets.rowMapper.mgr.CouponIDRowMapper;
import tickets.rowMapper.user.UserCouponNumRowMapper;
import tickets.rowMapper.user.UserCouponRowMapper;

import java.util.List;

@Repository("userCouponDao")
public class UserCouponDaoImpl implements UserCouponDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public List<UserCoupon> selectAllUserCoupon(String email){
		String sql = "SELECT uc.couponID, c.name, c.discount, uc.number" +
				" FROM " + ParaName.Table_userCoupon + " AS uc," + ParaName.Table_couponInfo + " AS c" +
				" WHERE uc.email=? AND uc.couponID=c.couponID AND uc.number>0";
		List<UserCoupon> userCoupons = jdbcTemplate.query(sql, new UserCouponRowMapper(), email);
		return userCoupons;
	}
	
	@Override
	public void insertUserCoupon(String email, String couponID){
		String selectCouponIDSql = "SELECT couponID FROM " + ParaName.Table_userCoupon + " WHERE email=?";
		List<String> couponIDs = jdbcTemplate.query(selectCouponIDSql, new CouponIDRowMapper(), email);
		if( couponIDs.contains(couponID) ){
			String sql = "UPDATE " + ParaName.Table_userCoupon + " SET number=number+1 WHERE email=? AND couponID=?";
			jdbcTemplate.update(sql, email, couponID);
		}
		else {
			final int number = 1;
			String sql = "INSERT INTO " + ParaName.Table_userCoupon + " VALUES (?,?,?)";
			jdbcTemplate.update(sql, email, couponID, number);
		}
	}
	
	@Override
	public boolean deleteUserCoupon(String email, String couponID){
		String selectCouponNumSql = "SELECT number FROM " + ParaName.Table_userCoupon + " WHERE email=? AND couponID=?";
		int couponNum = jdbcTemplate.queryForObject(selectCouponNumSql, new UserCouponNumRowMapper(), email, couponID);
		if( couponNum>0 ){
			String sql = "UPDATE " + ParaName.Table_userCoupon + " SET number=number-1 WHERE email=? AND couponID=?";
			jdbcTemplate.update(sql, email, couponID);
			return true;
		}
		else {
			return false;
		}
	}
	
}
