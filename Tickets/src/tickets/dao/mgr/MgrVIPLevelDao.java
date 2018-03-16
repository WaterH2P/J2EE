package tickets.dao.mgr;

import tickets.model.mgr.CouponInfo;
import tickets.model.mgr.VIPLevelInfo;

import java.util.List;

public interface MgrVIPLevelDao {

	void insertVIPLevelInfo(List<VIPLevelInfo> vipLevelInfos);

	List<VIPLevelInfo> selectAllVIPLevelInfos();

}
