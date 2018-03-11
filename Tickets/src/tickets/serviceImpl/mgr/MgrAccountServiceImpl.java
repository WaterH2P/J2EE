package tickets.serviceImpl.mgr;

import org.springframework.stereotype.Service;
import tickets.dao.CommonAccountDao;
import tickets.dao.mgr.MgrAccountDao;
import tickets.service.CommonAccountService;

import javax.annotation.Resource;

@Service( "mgrAccountService" )
public class MgrAccountServiceImpl implements CommonAccountService {
	
	@Resource(name = "mgrAccountDao")
	private CommonAccountDao commonAccountDao;
	
	@Override
	public boolean login(String mgrID, String password){
		return commonAccountDao.loginCheck(mgrID, password);
	}
	
}
