package tickets.rowMapper.mgr;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.mgr.VIPLevelInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VIPLevelInfoRowMapper implements RowMapper {
	
	@Override
	public VIPLevelInfo mapRow(ResultSet rs, int i) throws SQLException{
		VIPLevelInfo vipLevelInfo = new VIPLevelInfo();
		vipLevelInfo.setVipLevel( rs.getString("vipLevel") );
		vipLevelInfo.setPercent( rs.getInt("percent") );
		vipLevelInfo.setPoint( rs.getInt("point") );
		return vipLevelInfo;
	}
	
}