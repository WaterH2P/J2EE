package tickets.daoImpl.mgr;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.CommonAccountDao;
import tickets.daoImpl.Common;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;

@Repository( "mgrAccountDao" )
public class MgrAccountDaoImpl implements CommonAccountDao{
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public boolean loginCheck(String managerID, String password){
		String primaryKey = "mgrID";
		boolean loginResult = Common.loginCheckPassword(
				primaryKey, managerID, password, ParaName.Table_mgrAccount);
		return loginResult;
	}
	
}
