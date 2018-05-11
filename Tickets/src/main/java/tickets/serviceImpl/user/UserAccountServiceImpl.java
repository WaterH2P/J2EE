package tickets.serviceImpl.user;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;
import tickets.dao.CommonAccountDao;
import tickets.dao.user.UserAccountDao;
import tickets.daoImpl.ParaName;
import tickets.exception.AccountAccessException;
import tickets.model.AccountState;
import tickets.service.CommonAccountService;
import tickets.service.MailService;
import tickets.service.user.UserAccountService;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

@Service( "userAccountService" )
public class UserAccountServiceImpl implements CommonAccountService, UserAccountService {
	
	@Resource(name = "userAccountDao")
	private UserAccountDao userAccountDao;
	
	@Resource(name = "userAccountDao")
	private CommonAccountDao commonAccountDao;
	
	@Resource(name = "mailService")
	private MailService mailService;
	
	@Override
	public boolean login(String email, String password) throws AccountAccessException{
		AccountState accountState = commonAccountDao.selectAccountSateInfo(email, password);
		if( !accountState.isConfirmed() ){
			throw new AccountAccessException(ParaName.exception_accountUnconfirmed);
		}
		if( accountState.isDeleted() ){
			throw new AccountAccessException(ParaName.exception_accountDeleted);
		}
		if( !accountState.isPasswordRight() ){
			throw new AccountAccessException(ParaName.exception_accountOrPasswordWrong);
		}
		return true;
	}
	
	@Override
	public boolean accountExist(String email){
		return userAccountDao.accountIsExist(email);
	}
	
	@Override
	public boolean preRegister(String email, String name) throws AccountAccessException{
		boolean result = false;
		boolean isExist = userAccountDao.accountIsExist(email);
		if( isExist && commonAccountDao.selectAccountIsConfirmed(email) ){
			result = false;
		}
		else{
			String code = "";
			String string = email + Calendar.getInstance();
			final int codeLength = 5;
			code = randomString(string, codeLength);
			
			System.out.println(code);
			if( !isExist ){
				userAccountDao.addAccount(email, code, name);
			}
			else{
				userAccountDao.updateAccountCode(email, code);
			}
				
			String subject = "Verification";
			String content = "Hello, your Verification Code is : " + code;
			result = mailService.sendMail(email, subject, content);
		}
		return result;
	}
	
	@Override
	public boolean register(String email, String password, String verificationCode) throws AccountAccessException{
		if( userAccountDao.codeCheck(email, verificationCode) ){
			userAccountDao.updateRegisterAccount(email, password);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void cancelAccountVIP(String email){
		userAccountDao.deleteAccountVIP(email);
	}
	
	private static String randomString(String baseStr, int strLen){
		String result = "";
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			String newStr = base64en.encode(md5.digest(baseStr.getBytes("utf-8")));
			for( int i = 0; i < strLen; i++ ){
				int index = (int) (Math.random() * newStr.length());
				result += newStr.charAt(index);
			}
		}catch( NoSuchAlgorithmException e ){
			e.printStackTrace();
		}catch( UnsupportedEncodingException e ){
			e.printStackTrace();
		}
		return result;
	}
	
}
