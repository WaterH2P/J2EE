package tickets.serviceImpl.mgr;

import org.springframework.stereotype.Service;
import tickets.dao.mgr.MgrVIPLevelDao;
import tickets.model.mgr.VIPLevelInfo;
import tickets.service.mgr.MgrVIPLevelService;

import javax.annotation.Resource;
import java.util.List;

@Service("mgrVIPLevelService")
public class MgrVIPLevelServiceImpl implements MgrVIPLevelService {
	
	@Resource(name = "mgrVIPLevelDao")
	private MgrVIPLevelDao mgrVIPLevelDao;
	
	@Override
	public void setVIPLevelInfo(List<VIPLevelInfo> vipLevelInfos){
		mgrVIPLevelDao.insertVIPLevelInfo(vipLevelInfos);
	}
	
	@Override
	public List<VIPLevelInfo> getAllVIPLevelInfos(){
		return mgrVIPLevelDao.selectAllVIPLevelInfos();
	}
	
}
