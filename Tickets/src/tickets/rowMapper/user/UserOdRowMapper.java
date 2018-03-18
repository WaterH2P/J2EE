package tickets.rowMapper.user;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.user.UserOd;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOdRowMapper implements RowMapper {
	
	@Override
	public UserOd mapRow(ResultSet rs, int i) throws SQLException{
		UserOd userOd = new UserOd();
		userOd.setOdID( rs.getString("OdID") );
		userOd.setEmail( rs.getString("email") );
		userOd.setPlanID( rs.getString("planID") );
		userOd.setNumOfTicket( rs.getInt("numOfTicket") );
		userOd.setTotalPrice( rs.getDouble("totalPrice") );
		userOd.setVipDiscount( rs.getDouble("vipDiscount") );
		userOd.setCouponDiscount( rs.getInt("couponDiscount") );
		userOd.setTotalPay( rs.getDouble("totalPay") );
		userOd.setMakeTime( rs.getDate("makeTime") );
		userOd.setPaid( rs.getBoolean("isPaid") );
		userOd.setTimeout( rs.getBoolean("isTimeout") );
		userOd.setDeleted( rs.getBoolean("isDeleted") );
		userOd.setSeated( rs.getBoolean("isSeated") );
		userOd.setChecked( rs.getBoolean("isChecked") );
		return userOd;
	}
	
}