package tickets.dao.user;

import tickets.model.user.UserOd;
import tickets.model.user.UserOdSeat;

import java.util.List;

public interface UserOdDao {
	
	void insertNewUserOdSeated(UserOd userOd, List<UserOdSeat> userOdSeats);
	
	void insertNewUserOdUnseated(UserOd userOd);
	
	UserOd selectUserOdInfo(String OdID);
	
	List<String> selectAllUserOdIDs(String email);
	
	List<UserOdSeat> selectPlanAllOdSeat(String planID);
	
	List<UserOdSeat> selectUserOdAllSeatSelectedInfo(String OdID);
	
	void deleteUserOdAllSeatSelectedInfo(String OdID);
	
	void updateUserOdIsPaid(String OdID, double vipDiscount, int couponDiscount, double totalPay);
	
	void updateUserOdIsDeleted(String OdID);
	
	List<UserOd> selectAllHistoricalUserOd(String email);
	
	List<UserOd> selectAllUserOdTimeout(String email);
	
	List<UserOd> selectAllUserOdDeleted(String email);
	
	List<UserOd> selectAllFutureUserOd(String email);
	
	List<UserOd> selectAllUserOdUnfinished(String email);
	
}
