package tickets.serviceImpl.venue;

import org.springframework.stereotype.Service;
import tickets.dao.CommonAccountDao;
import tickets.dao.CommonUVAccountDao;
import tickets.dao.venue.VenueAccountDao;
import tickets.model.VenueInfo;
import tickets.service.CommonAccountService;
import tickets.service.venue.VenueAccountService;
import tickets.service.venue.VenueInfoService;

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
	
	@Resource(name = "venueInfoService")
	private VenueInfoService venueInfoService;
	
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
	public String preRegister(VenueInfo venueInfo, String password){
		List<String> venueIDs = venueAccountDao.getAllVenusIDs();
		
		System.out.println("VenueID is making !");
		
		String venueID = "";
		final int venueIDLength = 7;
		for( int i=0; i<venueIDLength; i++ ){
			int random = (int)(Math.random()*10.5-1);
			if( random<0 ){
				random = 0;
			}
			venueID += random;
		}
		if( venueID.length()>7 ){
			venueID = venueID.substring(0, 7);
		}
		while( venueIDs.contains(venueID) ){
			int random = (int)(Math.random()*10.99-1);
			if( random<0 ){
				random = 0;
			}
			venueID.substring(1);
			venueID += random;
		}
		venueInfo.setVenueID(venueID);
		
		System.out.println( venueID + " is register !");
		
		venueAccountDao.addAccount(venueID, password);
		venueInfoService.preRegister(venueInfo);
		return venueID;
	}
}
