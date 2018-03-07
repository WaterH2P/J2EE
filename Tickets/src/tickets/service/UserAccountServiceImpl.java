package tickets.service;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;
import tickets.dao.UserAccountDao;
import tickets.dao.UserDao;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

@Service( "userAccountService" )
public class UserAccountServiceImpl implements UserAccountService {
	
	@Resource(name = "userAccountDao" )
	private UserAccountDao userAccountDao;
	
	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Resource(name = "mailService")
	private MailService mailService;
	
	@Override
	public boolean accountExist(String email){
		return userAccountDao.accountIsExist(email);
	}
	
	@Override
	public boolean preRegister(String email, String name){
		boolean result =false;
		boolean isExist = userAccountDao.accountIsExist(email);
		if( isExist && userAccountDao.accountIsConfirmed(email) ){
			result = false;
		}
		else{
			String code = "";
			try{
				String string = email + Calendar.getInstance();
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				BASE64Encoder base64en = new BASE64Encoder();
				String newstr = base64en.encode(md5.digest(string.getBytes("utf-8")));
				final int codeLength = 5;
				for( int i = 0; i < codeLength; i++ ){
					int index = (int) (Math.random() * newstr.length());
					code += newstr.charAt(index);
				}
				System.out.println(code);
				if( !isExist ){
					userAccountDao.addAccount(email, code, name);
				} else{
					userAccountDao.updateAccountCode(email, code);
				}
				
				String subject = "Verification";
				String content = "Hello, your Verification Code is : " + code;
				result = mailService.sendMail(email, subject, content);
			}catch( NoSuchAlgorithmException e ){
				e.printStackTrace();
			}catch( UnsupportedEncodingException e ){
				e.printStackTrace();
			}
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
	
}
