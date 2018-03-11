package tickets.serviceImpl.mgr;

import org.springframework.stereotype.Service;
import tickets.dao.venue.VenueInfoDao;
import tickets.service.mgr.MgrExamineVenueInfoChangeService;

import javax.annotation.Resource;

@Service("mgrExamineVenueInfoChangeService")
public class MgrExamineVenueInfoChangeServiceImpl implements MgrExamineVenueInfoChangeService {
	
	@Resource(name = "venueInfoDao")
	private VenueInfoDao venueInfoDao;
	
	@Override
	public boolean agreeWithVenueInfoChange(String venueID){
		return venueInfoDao.updateVenueInfo(venueID);
	}
	
}
