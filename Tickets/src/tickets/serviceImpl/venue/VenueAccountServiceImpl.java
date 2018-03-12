package tickets.serviceImpl.venue;

import org.springframework.stereotype.Service;
import tickets.dao.CommonAccountDao;
import tickets.dao.CommonUVAccountDao;
import tickets.dao.venue.VenueAccountDao;
import tickets.model.VenueBaseInfo;
import tickets.service.CommonAccountService;
import tickets.service.venue.VenueAccountService;
import tickets.service.venue.VenueBaseInfoService;
import tickets.serviceImpl.CommonService;

import javax.annotation.Resource;
import java.util.List;

@Service("venueAccountService")
public class VenueAccountServiceImpl implements CommonAccountService, VenueAccountService {
	
	@Resource(name = "venueAccountDao")
	private VenueAccountDao venueAccountDao;
	
	@Resource(name = "venueAccountDao")
	private CommonAccountDao commonAccountDao;
	
	@Resource(name = "venueAccountDao")
	private CommonUVAccountDao commonUVAccountDao;
	
	@Resource(name = "venueBaseInfoService" )
	private VenueBaseInfoService venueBaseInfoService;
	
	@Override
	public boolean login(String venueID, String password){
		boolean loginResult = false;
		boolean isConfirmed = commonUVAccountDao.accountIsConfirmed(venueID);
		if( isConfirmed ){
			System.out.println("场馆登录 Service");
			return commonAccountDao.loginCheck(venueID, password);
		}
		return loginResult;
	}
	
	@Override
	public String preRegister(VenueBaseInfo venueBaseInfo, String password){
		List<String> venueIDs = venueAccountDao.getAllVenusIDs();
		
		System.out.println("VenueID is making !");
		
		String venueID = "";
		final int venueIDLength = 7;
		
		venueID = CommonService.getRandomString(venueIDLength, venueIDs);
		venueBaseInfo.setVenueID(venueID);
		
		System.out.println( venueID + " is register !");
		
		venueAccountDao.addAccount(venueID, password);
		venueBaseInfoService.preRegister(venueBaseInfo);
		return venueID;
	}
	
}
