package tickets.daoImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import tickets.rowMapper.IsConfirmedRowMapper;
import tickets.rowMapper.IsDeletedRowMapper;
import tickets.rowMapper.PasswordRowMapper;

public class Common {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	public static boolean loginCheckPassword(String primaryKey, String primaryKeyValue, String password, String tableName){
		boolean loginResult = false;
		String passwordSql = "SELECT password FROM " + tableName + " WHERE " + primaryKey +"=?";
		String passwd = jdbcTemplate.queryForObject(passwordSql, new PasswordRowMapper(), primaryKeyValue);
		if( passwd.equals(password) ){
			loginResult = true;
		}
		return loginResult;
	}
	
	public static boolean accountIsConfirmed(String primaryKey, String primaryKeyValue, String tableName){
		String sql = "SELECT isConfirmed FROM " + tableName + " WHERE " + primaryKey + "=?";
		boolean isConfirmed = false;
		isConfirmed = jdbcTemplate.queryForObject(sql, new IsConfirmedRowMapper(), primaryKeyValue);
		return isConfirmed;
	}
	
	public static boolean accountIsDeleted(String primaryKey, String primaryKeyValue, String tableName){
		String sql = "SELECT isDeleted FROM " + tableName + " WHERE " + primaryKey + "=?";
		boolean isDeleted = false;
		isDeleted = jdbcTemplate.queryForObject(sql, new IsDeletedRowMapper(), primaryKeyValue);
		return isDeleted;
	}
	
}
