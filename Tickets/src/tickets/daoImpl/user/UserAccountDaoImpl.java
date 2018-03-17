package tickets.daoImpl.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.CommonAccountDao;
import tickets.dao.CommonUVAccountDao;
import tickets.dao.user.UserAccountDao;
import tickets.daoImpl.Common;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.rowMapper.EmailRowMapper;
import tickets.rowMapper.IsDeletedRowMapper;

import java.util.List;

@Repository( "userAccountDao" )
public class UserAccountDaoImpl implements CommonAccountDao, CommonUVAccountDao, UserAccountDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public boolean loginCheck(String email, String password){
		boolean loginResult = false;
		boolean isConfirmed = accountIsConfirmed(email);
		boolean isDeleted = accountIsDeleted(email);
		if( isConfirmed && !isDeleted ){
			System.out.println("用户登录 Dao");
			String primaryKey = "email";
			loginResult = Common.loginCheckPassword(
					primaryKey, email, password, ParaName.Table_userAccount);
		}
		return loginResult;
	}
	
	//	===================================================================================================  //
	
	@Override
	public boolean accountIsConfirmed(String emailOrID){
		String primaryKey = "email";
		boolean isConfirmed = Common.accountIsConfirmed(primaryKey, emailOrID, ParaName.Table_userAccount);
		return isConfirmed;
	}
	
	@Override
	public boolean accountIsDeleted(String emailOrID){
		String primaryKey = "email";
		boolean isDeleted = Common.accountIsDeleted(primaryKey, emailOrID, ParaName.Table_userAccount);
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
	public boolean codeCheck(String email, String verificationCode){
		boolean loginResult = false;
		boolean isConfirmed = accountIsConfirmed(email);
		if( !isConfirmed ){
			System.out.println("用户验证码 Dao");
			String primaryKey = "email";
			loginResult = Common.loginCheckPassword(
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
			final int vipLevel = 0;
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
