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
	
	void updateUserOdIsPaid(String OdID, double vipDiscount, int couponDiscount, double totalPay);
}
