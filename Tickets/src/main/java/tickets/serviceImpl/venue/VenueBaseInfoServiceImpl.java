package tickets.serviceImpl.venue;

import org.springframework.stereotype.Service;
import tickets.dao.venue.VenueBaseInfoDao;
import tickets.model.venue.VenueBaseInfo;
import tickets.model.venue.VenueBaseInfoChange;
import tickets.model.venue.VenueBaseInfoRedundancy;
import tickets.service.venue.VenueBaseInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service( "venueBaseInfoService" )
public class VenueBaseInfoServiceImpl implements VenueBaseInfoService {
	
	@Resource(name = "venueBaseInfoDao" )
	private VenueBaseInfoDao venueBaseInfoDao;
	
	@Override
	public void preRegister(VenueBaseInfo venueBaseInfo){
		venueBaseInfoDao.addVenueInfo(venueBaseInfo);
	}
	
	@Override
	public List<VenueBaseInfo> getAllUnconfirmedVenueInfos(){
		return venueBaseInfoDao.selectAllUnconfirmedVenues();
	}
	
	@Override
	public VenueBaseInfo getVenueInfo(String venueID){
		return venueBaseInfoDao.selectVenueInfo(venueID);
	}
	
	@Override
	public boolean preChangeVenueInfo(VenueBaseInfoChange venueInfo){
		return venueBaseInfoDao.preUpdateVenueInfo(venueInfo);
	}
	
	@Override
	public List<VenueBaseInfoRedundancy> getAllVenueInfoRedundancies(){
		return venueBaseInfoDao.selectAllVenueInfoRedundancies();
	}
	
	@Override
	public void ChangeVenueInfo(String venueID){
		venueBaseInfoDao.updateVenueInfo(venueID);
	}
	
}
