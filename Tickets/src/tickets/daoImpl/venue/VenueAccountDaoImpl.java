package tickets.daoImpl.venue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.CommonAccountDao;
import tickets.dao.CommonUVAccountDao;
import tickets.dao.venue.VenueAccountDao;
import tickets.daoImpl.Common;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.rowMapper.VenueIDRowMapper;

import java.util.List;

@Repository("venueAccountDao")
public class VenueAccountDaoImpl implements CommonAccountDao, CommonUVAccountDao, VenueAccountDao {
	
	private JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public boolean loginCheck(String venueID, String password){
		boolean loginResult = false;
		boolean isConfirmed = accountIsConfirmed(venueID);
		if( isConfirmed ){
			String primaryKey = "venueID";
			loginResult = Common.loginCheckPassword(
					primaryKey, venueID, password, ParaName.Table_venueAccount);
			System.out.println("场馆登录 Dao");
		}
		return loginResult;
	}
	
	//	===================================================================================================  //
	
	@Override
	public boolean accountIsConfirmed(String emailOrID){
		String primaryKey = "venueID";
		boolean isConfirmed = Common.accountIsConfirmed(primaryKey, emailOrID, ParaName.Table_venueAccount);
		return isConfirmed;
	}
	
	@Override
	public boolean accountIsDeleted(String emailOrID){
		String primaryKey = "venueID";
		boolean isDeleted = Common.accountIsConfirmed(primaryKey, emailOrID, ParaName.Table_venueAccount);
		return isDeleted;
	}
	
	//	===================================================================================================  //
	
	@Override
	public List<String> getAllVenusIDs(){
		String sql = "SELECT venueID FROM " + ParaName.Table_venueAccount;
		List<String> venueIDs = jdbcTemplate.query(sql, new VenueIDRowMapper());
		return venueIDs;
	}
	
	@Override
	public void addAccount(String venueID, String password){
		String accountSql = "INSERT INTO " + ParaName.Table_venueAccount + " VALUES (?,?,?,?)";
		final boolean isConfirmed = false;
		final boolean isDeleted = false;
		jdbcTemplate.update(accountSql, venueID, password, isConfirmed, isDeleted);
	}
	
	@Override
	public boolean updateVenueIsConfirmedToTrue(String venueID){
		String sql = "UPDATE " + ParaName.Table_venueAccount + " SET isConfirmed=true WHERE venueID=?";
		jdbcTemplate.update(sql, venueID);
		return true;
	}
	
}
