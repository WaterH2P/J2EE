package tickets.daoImpl.user;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.CommonAccountDao;
import tickets.dao.user.UserAccountDao;
import tickets.daoImpl.CommonDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.exception.AccountAccessException;
import tickets.model.AccountState;
import tickets.rowMapper.AccountStateRowMapper;
import tickets.rowMapper.EmailRowMapper;
import tickets.rowMapper.IsConfirmedRowMapper;
import tickets.rowMapper.IsDeletedRowMapper;

import java.util.List;

@Repository( "userAccountDao" )
public class UserAccountDaoImpl implements CommonAccountDao, UserAccountDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public AccountState selectAccountSateInfo(String emailOrID, String password) throws AccountAccessException{
		AccountState accountState = new AccountState();
		String sql = "SELECT password, isConfirmed, isDeleted FROM " + ParaName.Table_userAccount + " WHERE email=?";
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
		String sql = "SELECT isConfirmed FROM " + ParaName.Table_userAccount + " WHERE email=?";
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
		String sql = "SELECT isDeleted FROM " + ParaName.Table_userAccount + " WHERE email=?";
		try{
			isDeleted = jdbcTemplate.queryForObject(sql, new IsDeletedRowMapper(), emailOrID);
		}catch( DataAccessException e ){
			throw new AccountAccessException(ParaName.exception_accountInvalid);
		}
		return isDeleted;
	}
	
	//	===================================================================================================  //
	
	@Override
	public List<String> selectAllEmails(){
		String sql = "SELECT email FROM " + ParaName.Table_userAccount;
		List<String> emails = jdbcTemplate.query(sql, new EmailRowMapper());
		return emails;
	}
	
	@Override
	public boolean codeCheck(String email, String verificationCode) throws AccountAccessException{
		boolean loginResult = false;
		boolean isConfirmed = selectAccountIsConfirmed(email);
		if( !isConfirmed ){
			System.out.println("用户验证码 Dao");
			String primaryKey = "email";
			loginResult = CommonDao.loginCheckPassword(
					primaryKey, email, verificationCode, ParaName.Table_userAccount);
		}
		return loginResult;
	}
	
	@Override
	public boolean accountIsExist(String email){
		List<String> emails = selectAllEmails();
		boolean isExist = false;
		for( String emailTemp : emails ){
			if( emailTemp.equals(email) ){
				isExist = true;
			}
		}
		return isExist;
	}
	
	@Override
	public boolean addAccount(String email, String verificationCode, String name){
		if( accountIsExist(email) ){
			return false;
		}
		else {
			String accountSql = "INSERT INTO " + ParaName.Table_userAccount + " VALUES (?,?,?,?)";
			final boolean isConfirmed = false;
			final boolean isDeleted = false;
			jdbcTemplate.update(accountSql, email, verificationCode, isConfirmed, isDeleted);
			String userInfoSql = "INSERT INTO " + ParaName.Table_userInfo + " VALUES (?,?,?,?,?,?)";
			final int vipLevel = 1;
			final float balance = 0;
			final int point = 0;
			final int totalPoint = 0;
			jdbcTemplate.update(userInfoSql, email, name, vipLevel, balance, point, totalPoint);
			return true;
		}
	}
	
	@Override
	public void updateAccountCode(String email, String verificationCode){
		String sql = "UPDATE " + ParaName.Table_userAccount + " SET password=? WHERE email=?";
		jdbcTemplate.update(sql, verificationCode, email);
	}
	
	@Override
	public void updateRegisterAccount(String email, String password){
		String accountSql = "UPDATE " + ParaName.Table_userAccount + " SET password=? WHERE email=?";
		jdbcTemplate.update(accountSql, password, email);
		String isConfirmedSql = "UPDATE  " + ParaName.Table_userAccount + " SET isConfirmed=true WHERE email=?";
		jdbcTemplate.update(isConfirmedSql, email);
	}
	
	@Override
	public void deleteAccountVIP(String email){
		String sql = "UPDATE " + ParaName.Table_userAccount + " SET isDeleted=true WHERE email=?";
		jdbcTemplate.update(sql, email);
	}
}
