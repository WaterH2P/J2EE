package tickets.service;

public interface MailService {
	
	boolean sendMail(String to, String subject, String content);
	
}
