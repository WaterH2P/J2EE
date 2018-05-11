package tickets.daoImpl.venue;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.CommonAccountDao;
import tickets.dao.venue.VenueAccountDao;
import tickets.daoImpl.CommonDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.exception.AccountAccessException;
import tickets.model.AccountState;
import tickets.rowMapper.AccountStateRowMapper;
import tickets.rowMapper.IsConfirmedRowMapper;
import tickets.rowMapper.IsDeletedRowMapper;
import tickets.rowMapper.venue.VenueIDRowMapper;

import java.util.List;

@Repository("venueAccountDao")
public class VenueAccountDaoImpl implements CommonAccountDao, VenueAccountDao {
	
	private JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	public boolean loginCheck(String venueID, String password) throws AccountAccessException{
		boolean loginResult = false;
		boolean isConfirmed = selectAccountIsConfirmed(venueID);
		if( isConfirmed ){
			String primaryKey = "venueID";
			loginResult = CommonDao.loginCheckPassword(
					primaryKey, venueID, password, ParaName.Table_venueAccount);
			System.out.println("场馆登录 Dao");
		}
		return loginResult;
	}
	
	@Override
	public AccountState selectAccountSateInfo(String emailOrID, String password) throws AccountAccessException{
		AccountState accountState = new AccountState();
		String sql = "SELECT password, isConfirmed, isDeleted FROM " + ParaName.Table_venueAccount + " WHERE venueID=?";
		try{
			accountState = jdbcTemplate.queryForObject(sql, new AccountStateRowMapper(), emailOrID);
			accountState.checkPassword(password);
		}catch( DataAccessException e ){
			throw new AccountAccessException(ParaName.exception_accountInvalid);
		}
		return accountState;
	}
	
	@Override
	public boolean selectAccountIsConfirmed(String emailOrID) throws AccountAccessException{
		boolean isConfirmed = false;
		String sql = "SELECT isConfirmed FROM " + ParaName.Table_venueAccount + " WHERE venueID=?";
		try{
			isConfirmed = jdbcTemplate.queryForObject(sql, new IsConfirmedRowMapper(), emailOrID);
		}catch( DataAccessException e ){
			throw new AccountAccessException(ParaName.exception_accountInvalid);
		}
		return isConfirmed;
	}
	
	@Override
	public boolean selectAccountIsDeleted(String emailOrID) throws AccountAccessException{
		boolean isDeleted = false;
		String sql = "SELECT isDeleted FROM " + ParaName.Table_venueAccount + " WHERE venueID=?";
		try{
			isDeleted = jdbcTemplate.queryForObject(sql, new IsDeletedRowMapper(), emailOrID);
		}catch( DataAccessException e ){
			throw new AccountAccessException(ParaName.exception_accountInvalid);
		}
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
