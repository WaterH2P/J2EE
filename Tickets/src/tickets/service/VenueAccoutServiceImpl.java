package tickets.service;

import org.springframework.stereotype.Service;
import tickets.dao.VenueAccountDao;
import tickets.model.VenueInfo;

import javax.annotation.Resource;
import java.util.List;

@Service("venueAccountService")
public class VenueAccoutServiceImpl implements VenueAccountService{
	
	@Resource(name = "venueAccountDao")
	private VenueAccountDao venueAccountDao;
	
	@Override
	public void preRegister(VenueInfo venueInfo, String password){
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
		
		venueAccountDao.addAccount(venueInfo, password);
	}
	
}
