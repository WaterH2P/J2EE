package tickets.rowMapper.mgr;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.mgr.CouponInfo;
import tickets.model.mgr.VIPLevelInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CouponInfoRowMapper implements RowMapper {
	
	@Override
	public CouponInfo mapRow(ResultSet rs, int i) throws SQLException{
		CouponInfo couponInfo = new CouponInfo();
		couponInfo.setCouponID( rs.getString("couponID") );
		couponInfo.setName( rs.getString("name") );
		couponInfo.setDiscount( rs.getDouble("discount") );
		couponInfo.setPoint( rs.getInt("point") );
		return couponInfo;
	}
	
}