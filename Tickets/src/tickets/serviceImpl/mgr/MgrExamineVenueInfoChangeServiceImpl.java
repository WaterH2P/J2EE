package tickets.serviceImpl.mgr;

import org.springframework.stereotype.Service;
import tickets.dao.venue.VenueBaseInfoDao;
import tickets.service.mgr.MgrExamineVenueInfoChangeService;

import javax.annotation.Resource;

@Service("mgrExamineVenueInfoChangeService")
public class MgrExamineVenueInfoChangeServiceImpl implements MgrExamineVenueInfoChangeService {
	
	@Resource(name = "venueBaseInfoDao" )
	private VenueBaseInfoDao venueBaseInfoDao;
	
	@Override
	public boolean agreeWithVenueInfoChange(String venueID){
		return venueBaseInfoDao.updateVenueInfo(venueID);
	}
	
}
