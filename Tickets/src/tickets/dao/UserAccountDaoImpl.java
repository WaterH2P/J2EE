package tickets.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.rowMapper.EmailRowMapper;
import tickets.rowMapper.UserIsConfirmedRowMapper;
import tickets.rowMapper.PasswordRowMapper;

import java.util.List;

@Repository( "userAccountDao" )
public class UserAccountDaoImpl implements UserAccountDao {
	
	private JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public List<String> selectAllEmails(){
		String sql = "SELECT email FROM " + ParaName.Table_userAccount;
		List<String> emails = jdbcTemplate.query(sql, new EmailRowMapper());
		return emails;
	}
	
	@Override
	public boolean loginCheck(String email, String password){
		boolean loginResult = false;
		boolean isConfirmed = accountIsConfirmed( email );
		if( isConfirmed ){
			loginResult = codeCheck(email, password);
		}
		else {
			loginResult = false;
		}
		return loginResult;
	}
	
	@Override
	public boolean codeCheck(String email, String verificationCode){
		boolean loginResult = false;
		String passwordSql = "SELECT password FROM " + ParaName.Table_userAccount + " WHERE email=?";
		String password = jdbcTemplate.queryForObject(passwordSql, new PasswordRowMapper(), email);
		if( password.equals(verificationCode) ){
			loginResult = true;
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
			String userInfoSql = "INSERT INTO " + ParaName.Table_userInfo + " VALUES (?,?,?,?)";
			final int vipLevel = 0;
			final float balance = 0;
			jdbcTemplate.update(userInfoSql, email, name, vipLevel, balance);
			return true;
		}
	}
	
	@Override
	public boolean accountIsConfirmed(String email){
		String sql = "SELECT isConfirmed FROM " + ParaName.Table_userAccount + " WHERE email=?";
		boolean isConfirmed = false;
		isConfirmed = jdbcTemplate.queryForObject(sql, new UserIsConfirmedRowMapper(), email);
		return isConfirmed;
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
}
