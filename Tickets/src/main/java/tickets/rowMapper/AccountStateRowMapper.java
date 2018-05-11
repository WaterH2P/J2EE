package tickets.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import tickets.model.AccountState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountStateRowMapper implements RowMapper<AccountState> {
	
	@Override
	public AccountState mapRow(ResultSet rs, int i) throws SQLException{
		AccountState accountState = new AccountState();
		accountState.setPassword( rs.getString("password") );
		accountState.setConfirmed( rs.getBoolean("isConfirmed") );
		accountState.setDeleted( rs.getBoolean("isDeleted") );
		return accountState;
	}
	
}
