package tickets.rowMapper.user;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.user.UserCoupon;
import tickets.model.user.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCouponRowMapper implements RowMapper {
	
	@Override
	public UserCoupon mapRow(ResultSet rs, int i) throws SQLException{
		UserCoupon userCoupon = new UserCoupon();
		userCoupon.setCouponID( rs.getString("couponID") );
		userCoupon.setName( rs.getString("name") );
		userCoupon.setDiscount( rs.getDouble("discount") );
		userCoupon.setNumber( rs.getInt("number") );
		return userCoupon;
	}
	
}
