package tickets.daoImpl.mgr;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.mgr.MgrCouponDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.mgr.CouponInfo;
import tickets.rowMapper.mgr.CouponIDRowMapper;
import tickets.rowMapper.mgr.CouponInfoRowMapper;

import java.util.ArrayList;
import java.util.List;

@Repository("mgrCouponDao")
public class MgrCouponDaoImpl implements MgrCouponDao{
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public void insertNewCoupon(CouponInfo couponInfo){
		String couponID = couponInfo.getCouponID();
		String name = couponInfo.getName();
		double discount = couponInfo.getDiscount();
		int point = couponInfo.getPoint();
		final boolean isDeleted = false;
		String sql = "INSERT INTO " + ParaName.Table_couponInfo + " VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sql, couponID, name, discount, point, isDeleted);
	}
	
	@Override
	public void deleteCoupon(String couponID){
		String sql = "UPDATE " + ParaName.Table_couponInfo + " SET isDeleted=TRUE WHERE couponID=?";
		jdbcTemplate.update(sql, couponID);
	}
	
	@Override
	public List<String> selectAllCouponIDs(){
		String sql = "SELECT couponID FROM " + ParaName.Table_couponInfo;
		List<String> couponIDs = jdbcTemplate.query(sql, new CouponIDRowMapper());
		return couponIDs;
	}
	
	@Override
	public List<CouponInfo> selectAllCouponInfosExchanged(){
		String sql = "SELECT * FROM " + ParaName.Table_couponInfo + " WHERE isDeleted=FALSE ";
		List<CouponInfo> couponInfos = jdbcTemplate.query(sql, new CouponInfoRowMapper());
		return couponInfos;
	}
	
	@Override
	public CouponInfo selectCouponInfo(String couponID){
		String sql = "SELECT * FROM " + ParaName.Table_couponInfo + " WHERE couponID=? ";
		CouponInfo couponInfo = (CouponInfo) jdbcTemplate.queryForObject(sql, new CouponInfoRowMapper(), couponID);
		return couponInfo;
	}
	
}
