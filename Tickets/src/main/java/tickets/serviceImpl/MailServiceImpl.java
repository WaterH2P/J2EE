package tickets.serviceImpl;

import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import tickets.service.MailService;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl implements MailService {
	
	@Resource
	private JavaMailSender javaMailSender;
	
	@Resource
	private SimpleMailMessage simpleMailMessage;
	
	/**
	 *  发送邮件
	 * @param to         收件人邮箱
	 * @param subject    邮件主题
	 * @param content    邮件内容
	 */
	
	@Override
	public boolean sendMail(String to, String subject, String content) {
		
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(simpleMailMessage.getFrom());
			if (subject != null) {
				messageHelper.setSubject(subject);
			} else {
				messageHelper.setSubject(simpleMailMessage.getSubject());
			}
			messageHelper.setTo(to);
			messageHelper.setText(content, true);
			javaMailSender.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
