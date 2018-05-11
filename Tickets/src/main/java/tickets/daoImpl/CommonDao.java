package tickets.daoImpl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import tickets.exception.AccountAccessException;
import tickets.model.AccountState;
import tickets.rowMapper.AccountStateRowMapper;
import tickets.rowMapper.IsConfirmedRowMapper;
import tickets.rowMapper.IsDeletedRowMapper;
import tickets.rowMapper.PasswordRowMapper;

public class CommonDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	public static boolean loginCheckPassword(String primaryKey, String primaryKeyValue, String password, String tableName) throws AccountAccessException{
		boolean loginResult = false;
		String passwordSql = "SELECT password FROM " + tableName + " WHERE " + primaryKey +"=?";
		String passwd = "";
		try{
			passwd = jdbcTemplate.queryForObject(passwordSql, new PasswordRowMapper(), primaryKeyValue);
		}catch( DataAccessException e ){
			System.out.println(primaryKey + " : " + primaryKeyValue + "  doesn't exist!");
			throw new AccountAccessException(ParaName.exception_accountInvalid);
		}
		
		if( passwd.equals(password) ){
			loginResult = true;
		}
		return loginResult;
	}
	
	public static boolean selectAccountIsConfirmed(String primaryKey, String primaryKeyValue, String tableName) throws AccountAccessException{
		boolean isConfirmed = false;
		String sql = "SELECT isConfirmed FROM " + tableName + " WHERE " + primaryKey + "=?";
		try{
			isConfirmed = jdbcTemplate.queryForObject(sql, new IsConfirmedRowMapper(), primaryKeyValue);
		}catch( DataAccessException e ){
			System.out.println(primaryKey + " : " + primaryKeyValue + "  doesn't exist!");
			throw new AccountAccessException(ParaName.exception_accountInvalid);
		}
		return isConfirmed;
	}
	
	public static boolean selectAccountIsDeleted(String primaryKey, String primaryKeyValue, String tableName) throws AccountAccessException{
		boolean isDeleted = false;
		String sql = "SELECT isDeleted FROM " + tableName + " WHERE " + primaryKey + "=?";
		try{
			isDeleted = jdbcTemplate.queryForObject(sql, new IsDeletedRowMapper(), primaryKeyValue);
		}catch( DataAccessException e ){
			System.out.println(primaryKey + " : " + primaryKeyValue + "  doesn't exist!");
			throw new AccountAccessException(ParaName.exception_accountInvalid);
		}
		return isDeleted;
	}
	
}
