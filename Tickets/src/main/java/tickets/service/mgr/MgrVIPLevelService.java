package tickets.service.mgr;

import tickets.model.mgr.VIPLevelInfo;

import java.util.List;

public interface MgrVIPLevelService {
	
	void setVIPLevelInfo(List<VIPLevelInfo> vipLevelInfos);
	
	List<VIPLevelInfo> getAllVIPLevelInfos();
	
}
