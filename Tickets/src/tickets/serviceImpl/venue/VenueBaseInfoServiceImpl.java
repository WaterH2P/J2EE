package tickets.serviceImpl.venue;

import org.springframework.stereotype.Service;
import tickets.dao.venue.VenueBaseInfoDao;
import tickets.model.VenueBaseInfo;
import tickets.model.VenueBaseInfoChange;
import tickets.model.VenueBaseInfoRedundancy;
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
	public boolean ChangeVenueInfo(String venueID){
		return venueBaseInfoDao.updateVenueInfo(venueID);
	}
	
}
