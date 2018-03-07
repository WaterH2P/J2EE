package tickets.service;

import org.springframework.stereotype.Service;
import tickets.dao.AccountDao;
import tickets.dao.UserDao;

import javax.annotation.Resource;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	@Resource(name = "accountDao")
	private AccountDao accountDao;
	
	@Resource(name = "mailService")
	private MailService mailService;
	
	@Override
	public boolean login(String email, String password){
		return accountDao.loginCheck(email, password);
	}
	
	@Override
	public boolean register(String email, String password, String name){
		if( !accountDao.accountExist(email) ){
			String subject = "test";
			String content = "Hello " + email;
			return mailService.sendMail(email, subject, content);
		}
		else{
			return false;
		}
	}
	
}
