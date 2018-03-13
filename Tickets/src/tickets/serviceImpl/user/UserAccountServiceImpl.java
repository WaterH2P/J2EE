package tickets.serviceImpl.user;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;
import tickets.dao.CommonAccountDao;
import tickets.dao.CommonUVAccountDao;
import tickets.dao.user.UserAccountDao;
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
	
	@Resource(name = "userAccountDao")
	private CommonUVAccountDao commonUVAccountDao;
	
	@Resource(name = "mailService")
	private MailService mailService;
	
	@Override
	public boolean login(String email, String password){
		boolean loginResult = false;
		boolean isConfirmed = commonUVAccountDao.accountIsConfirmed(email);
		if( isConfirmed ){
			System.out.println("用户登录 Service");
			loginResult = commonAccountDao.loginCheck(email, password);
		}
		return loginResult;
	}
	
	@Override
	public boolean accountExist(String email){
		return userAccountDao.accountIsExist(email);
	}
	
	@Override
	public boolean preRegister(String email, String name){
		boolean result = false;
		boolean isExist = userAccountDao.accountIsExist(email);
		if( isExist && commonUVAccountDao.accountIsConfirmed(email) ){
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
	public boolean register(String email, String password, String verificationCode){
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
