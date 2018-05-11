package tickets.daoImpl.mgr;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.CommonAccountDao;
import tickets.daoImpl.CommonDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.exception.AccountAccessException;
import tickets.model.AccountState;
import tickets.rowMapper.AccountStateRowMapper;
import tickets.rowMapper.IsConfirmedRowMapper;
import tickets.rowMapper.IsDeletedRowMapper;

@Repository( "mgrAccountDao" )
public class MgrAccountDaoImpl implements CommonAccountDao{
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	public boolean loginCheck(String managerID, String password) throws AccountAccessException{
		String primaryKey = "mgrID";
		boolean loginResult = CommonDao.loginCheckPassword(
				primaryKey, managerID, password, ParaName.Table_mgrAccount);
		return loginResult;
	}
	
	@Override
	public AccountState selectAccountSateInfo(String emailOrID, String password) throws AccountAccessException{
		AccountState accountState = new AccountState();
		String sql = "SELECT password, isConfirmed, isDeleted FROM " + ParaName.Table_mgrAccount + " WHERE mgrID=?";
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
		String sql = "SELECT isConfirmed FROM " + ParaName.Table_mgrAccount + " WHERE mgrID=?";
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
		String sql = "SELECT isDeleted FROM " + ParaName.Table_mgrAccount + " WHERE mgrID=?";
		try{
			isDeleted = jdbcTemplate.queryForObject(sql, new IsDeletedRowMapper(), emailOrID);
		}catch( DataAccessException e ){
			throw new AccountAccessException(ParaName.exception_accountInvalid);
		}
		return isDeleted;
	}
}
