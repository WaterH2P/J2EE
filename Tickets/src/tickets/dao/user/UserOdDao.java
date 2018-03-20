package tickets.dao.user;

import tickets.model.user.UserOd;
import tickets.model.user.UserOdSeat;

import java.util.List;

public interface UserOdDao {
	
	void insertNewUserOdSeated(UserOd userOd, List<UserOdSeat> userOdSeats);
	
	void insertNewUserOdUnseated(UserOd userOd);
	
	void insertUserOdSeat(List<UserOdSeat> userOdSeats);
	
	UserOd selectUserOdInfo(String OdID);
	
	List<String> selectAllUserOdIDs(String email);
	
	List<UserOdSeat> selectPlanAllOdSeat(String planID);
	
	List<UserOdSeat> selectUserOdAllSeatSelectedInfo(String OdID);
	
	List<UserOdSeat> selectPlanAllUserOdCheckedSeatInfo(String planID);
	
	List<UserOdSeat> selectPlanUserOdCheckedSeatInfo(String OdID);
	
	void deleteUserOdAllSeatSelectedInfo(String OdID);
	
	void updateUserOdIsPaid(String OdID, double vipDiscount, int couponDiscount, double totalPay);
	
	void updateUserOdIsDeleted(String OdID);
	
	void updateUserOdIsTimeout(String OdID);
	
	void updateUserOdIsSeated(String OdID);
	
	void updateUserOdIsSettled(String OdID);
	
	List<UserOd> selectAllHistoricalUserOd(String email);
	
	List<UserOd> selectAllUserOdTimeout(String email);
	
	List<UserOd> selectAllUserOdDeleted(String email);
	
	List<UserOd> selectAllFutureUserOd(String email);
	
	List<UserOd> selectAllUserOdUnfinished(String email);
	
	List<String> selectAllVenuePlanUserOd(String planID);
	
	List<UserOdSeat> selectAllVenuePlanUserOdIsCheckedSeat(String planID);
	
	List<String> selectAllVenuePlanUserOdIsNotChecked(String planID);
	
	List<String> selectAllVenuePlanUserOd(
			String planID, boolean isPaid, boolean isTimeout, boolean isDeleted,
			boolean isSeated, boolean isChecked);
	
	void updateUserOdIsChecked(String OdID);
	
	List<UserOd> selectAllOdUnfinished();
	
	List<UserOd> selectAllPlanUserOd_isPaid_isNotDeleted_isNotSeated(String planID);
	
	List<UserOd> selectAllPlanUserOd(String planID);
	
	List<UserOd> selectAllUserOd_isChecked_isNotSettled();
	
}
