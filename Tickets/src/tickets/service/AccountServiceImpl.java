package tickets.service;

import org.springframework.stereotype.Service;
import tickets.controller.Common;
import tickets.dao.ParaName;
import tickets.dao.UserAccountDao;

import javax.annotation.Resource;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	@Resource(name = "userAccountDao" )
	private UserAccountDao userAccountDao;
	
	@Override
	public boolean login(String EmailORVenueID, String password){
		if( EmailORVenueID.length()==7 && Common.regCheck(ParaName.venueIDRegex, EmailORVenueID) ){
			System.out.println("场馆登录");
			return false;
		}
		else {
			System.out.println("邮箱登录");
			return userAccountDao.loginCheck(EmailORVenueID, password);
		}
	}
	
}
