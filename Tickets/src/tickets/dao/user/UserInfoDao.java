package tickets.dao.user;

import tickets.model.user.UserInfo;

public interface UserInfoDao {
	
	UserInfo selectUserInfo(String email);
	
	void updateUserName(String email, String name);
	
	/**
	 * @param:
	 * @return:
	 * @description: add point: pointModifyValue > 0, minus point: pointModifyValue < 0
	 */
	boolean updateUserPoint(String email, int pointModifyValue);
	
	boolean updateUserBalance(String email, double balanceModifyValue);
	
	boolean updateOnlyUserBalance(String email, double balanceModifyValue);
	
	void updateUserVIPLevel(String email, String vipLevel);
	
}
