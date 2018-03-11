package tickets.serviceImpl.mgr;

import org.springframework.stereotype.Service;
import tickets.dao.venue.VenueAccountDao;
import tickets.service.mgr.MgrExamineVenueRegisterService;

import javax.annotation.Resource;

@Service( "mgrExamineVenueRegisterService" )
public class MgrExamineVenueRegisterServiceImpl implements MgrExamineVenueRegisterService {
	
	@Resource(name = "venueAccountDao")
	private VenueAccountDao venueAccountDao;
	
	@Override
	public boolean agreeWithVenueRegister(String venueID){
		return venueAccountDao.updateVenueIsConfirmedToTrue(venueID);
	}
	
}
