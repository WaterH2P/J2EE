package tickets.serviceImpl.user;

import org.springframework.stereotype.Service;
import tickets.dao.venue.VenuePlanDao;
import tickets.model.venue.VenuePlan;
import tickets.service.user.UserTicketService;
import tickets.serviceImpl.CommonService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("userTicketService")
public class UserTicketServiceImpl implements UserTicketService {
	
	@Resource(name = "venuePlanDao")
	private VenuePlanDao venuePlanDao;
	
	@Override
	public List<VenuePlan> searchPlanByPlanName(String planName){
		List<VenuePlan> venuePlans = venuePlanDao.selectAllVenuePlansByPlanName(planName);
		List<VenuePlan> venuePlanUnifyBack = CommonService.venuePlanUnifyBack(venuePlans);
		return venuePlanUnifyBack;
	}
	
}
