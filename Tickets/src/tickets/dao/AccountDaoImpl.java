package tickets.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.rowMapper.EmailRowMapper;
import tickets.rowMapper.PasswordRowMapper;

import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
	
	private JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public List<String> selectAllEmails(){
		String sql = "SELECT email FROM " + ParaName.Table_accountInfo;
		List<String> emails = jdbcTemplate.query(sql, new EmailRowMapper());
		return emails;
	}
	
	@Override
	public boolean loginCheck(String email, String password){
		boolean loginResult = false;
		String sql = "SELECT password FROM " + ParaName.Table_accountInfo + " WHERE email=?";
		List<String> passwords = jdbcTemplate.query(sql, new PasswordRowMapper(), email);
		for( String passwd : passwords ){
			if( passwd.equals(password) ){
				loginResult = true;
				break;
			}
		}
		return loginResult;
	}
	
	@Override
	public boolean accountExist(String email){
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
	public boolean addAccount(String email, String password, String name){
		if( accountExist(email) ){
			return false;
		}
		else {
			String accountSql = "INSERT INTO " + ParaName.Table_accountInfo + " VALUES (?,?)";
			jdbcTemplate.update(accountSql, email, password);
			String userInfoSql = "INSERT INTO " + ParaName.Table_userInfo + " VALUES (?,?,?,?,?,?)";
			final int vipLevel = 0;
			final float balance = 0;
			final boolean isConfirmed = false;
			final boolean isDeleted = false;
			jdbcTemplate.update(userInfoSql, email, name, vipLevel, balance, isConfirmed, isDeleted);
			return true;
		}
	}
}
