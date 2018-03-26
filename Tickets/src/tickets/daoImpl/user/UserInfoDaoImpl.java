package tickets.daoImpl.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.user.UserInfoDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.user.UserInfo;
import tickets.rowMapper.user.UserInfoRowMapper;

import java.util.List;

@Repository( "userInfoDao" )
public class UserInfoDaoImpl implements UserInfoDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public UserInfo selectUserInfo(String email){
		String sql = "SELECT * FROM " + ParaName.Table_userInfo + " WHERE email=?";
		UserInfo userInfo = (UserInfo) jdbcTemplate.queryForObject(sql, new UserInfoRowMapper(), email);
		return userInfo;
	}
	
	@Override
	public void updateUserName(String email, String name){
		String sql = "UPDATE " + ParaName.Table_userInfo + " SET name=? WHERE email=?";
		jdbcTemplate.update(sql, name, email);
	}
	
	@Override
	public boolean updateUserPoint(String email, int pointModifyValue){
		UserInfo userInfo = selectUserInfo(email);
		if( userInfo.getPoint()+pointModifyValue>0 ){
			String sql = "UPDATE " + ParaName.Table_userInfo + " SET point=point+? WHERE email=?";
			jdbcTemplate.update(sql, pointModifyValue, email);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean updateUserBalance(String email, double balanceModifyValue){
		UserInfo userInfo = selectUserInfo(email);
		int pointModifyValue = (int)balanceModifyValue;
		if( userInfo.getBalance()-balanceModifyValue>0 ){
			String updateBalance = "UPDATE " + ParaName.Table_userInfo +
					" SET balance=balance+?, point=point-?, totalPoint=totalPoint-? WHERE email=?";
			jdbcTemplate.update(updateBalance, balanceModifyValue, pointModifyValue, pointModifyValue, email);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean updateOnlyUserBalance(String email, double balanceModifyValue){
		UserInfo userInfo = selectUserInfo(email);
		int pointModifyValue = (int)balanceModifyValue;
		if( userInfo.getBalance()-balanceModifyValue>0 ){
			String updateBalance = "UPDATE " + ParaName.Table_userInfo +
					" SET balance=balance+? WHERE email=?";
			jdbcTemplate.update(updateBalance, balanceModifyValue, email);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void updateUserVIPLevel(String email, String vipLevel){
		String sql = "UPDATE " + ParaName.Table_userInfo + " SET vipLevel=? WHERE email=?";
		jdbcTemplate.update(sql, vipLevel, email);
	}
	
	
	@Override
	public List<UserInfo> selectAllUserInfo(){
		String sql = "SELECT * FROM " + ParaName.Table_userInfo;
		List<UserInfo> userInfos = jdbcTemplate.query(sql, new UserInfoRowMapper());
		return userInfos;
	}
}
