package tickets.serviceImpl.venue;

import org.springframework.stereotype.Service;
import tickets.dao.venue.VenueInfoDao;
import tickets.model.VenueInfo;
import tickets.model.VenueInfoChange;
import tickets.model.VenueInfoRedundancy;
import tickets.service.venue.VenueInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service("venueInfoService")
public class VenueInfoServiceImpl implements VenueInfoService {
	
	@Resource(name = "venueInfoDao")
	private VenueInfoDao venueInfoDao;
	
	@Override
	public void preRegister(VenueInfo venueInfo){
		venueInfoDao.addVenueInfo(venueInfo);
	}
	
	@Override
	public List<VenueInfo> getAllUnconfirmedVenueInfos(){
		return venueInfoDao.selectAllUnconfirmedVenues();
	}
	
	@Override
	public VenueInfo getVenueInfo(String venueID){
		return venueInfoDao.selectVenueInfo(venueID);
	}
	
	@Override
	public boolean preChangeVenueInfo(VenueInfoChange venueInfo){
		return venueInfoDao.preUpdateVenueInfo(venueInfo);
	}
	
	@Override
	public List<VenueInfoRedundancy> getAllVenueInfoRedundancies(){
		return venueInfoDao.selectAllVenueInfoRedundancies();
	}
	
	@Override
	public boolean ChangeVenueInfo(String venueID){
		return venueInfoDao.updateVenueInfo(venueID);
	}
	
}
