package tickets.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Spring Mail
 * API都在org.springframework.mail及其子包org.springframework.mail.javamail中封装，
 * 且只提供了邮件发送的封装。 SimpleMailMessage: 对邮件的一个简单封装，只能用于表示一个纯文本的邮件，也不能包含附件等。
 * JavaMailSenderImpl: 邮件发送器，主要提供了邮件发送接口、透明创建Java
 * Mail的MimeMessage、及邮件发送的配置(如:host/port/username/password...)。
 * MimeMailMessage、MimeMessageHelper：对MimeMessage进行了封装。
 * Spring还提供了一个回调接口MimeMessagePreparator, 用于准备JavaMail的MIME信件
 * 一下代码转载自:http://www.blogjava.net/tangzurui/archive/2008/12/08/244953.html
 */
public class SendEmailUtils {
	
	/**
	 *
	 * @description: 实例化JavaMailSender。
	 * @author: skyler
	 * @time: 2016年7月7日 下午4:56:53
	 */
	public static JavaMailSender initJavaMailSender() {
		/*
		 * //从网上找的参考，说要添加这么多，实际测试了下，不需要这么多个
		 * Properties properties = new Properties();
		 * properties.setProperty("mail.debug", "true");
		 * properties.setProperty("mail.smtp.socketFactory.class",
		 * "javax.net.ssl.SSLSocketFactory");
		 * properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		 * properties.setProperty("mail.smtp.socketFactory.port", "465");
		 * properties.setProperty("mail.smtp.port", "465");
		 * properties.setProperty("mail.smtp.auth", "true");
		 */
		Properties properties = new Properties();
		// 是否显示调试信息(可选)
		properties.setProperty("mail.debug", "true");
		// 身份验证设置
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.auth", "true");
		properties.put(" mail.smtp.timeout ", " 25000 ");
		
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setJavaMailProperties(properties);
		// 发件人邮箱主机名
		javaMailSender.setHost("smtp.163.com");
		javaMailSender.setProtocol("smtp");
		javaMailSender.setUsername("z110a110@126.com"); // 根据自己的情况,设置username
		javaMailSender.setPassword("wy.hzp2617"); // 根据自己的情况, 设置password
		javaMailSender.setPort(465);
		javaMailSender.setDefaultEncoding("UTF-8");
		
		return javaMailSender;
	}
	
	public static void main(String[] args) throws Exception {
		
		JavaMailSender sender = initJavaMailSender();
		sendText(sender);
		// sendHtml(sender);
	}
	
	
	public static void sendText(JavaMailSender sender) {
		// 建立邮件消息
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// 设置收件人，寄件人 用数组发送多个邮件
		// String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
		// mailMessage.setTo(array);
		mailMessage.setTo("151250066@smail.nju.edu.cn");
		// 可选的，可以用来修改显示给接收者的名字
		mailMessage.setFrom("z110a110@126.com");
		mailMessage.setSubject(" 测试简单文本邮件发送！ ");
		mailMessage.setText(" 测试我的简单邮件发送机制！！ ");
		
		// 发送邮件
		sender.send(mailMessage);
		
		System.out.println(" 邮件发送成功.. ");
	}
	
	
	public static void sendHtml(JavaMailSender sender) throws Exception {
		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = sender.createMimeMessage();
		// MimeMessageHelper messageHelper = new
		// MimeMessageHelper(mailMessage);这个构造函数会出现中文乱码的问题
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
		
		// 设置收件人，寄件人
		messageHelper.setTo("111@163.com");
		messageHelper.setFrom("abc@163.com");
		messageHelper.setSubject("测试HTML邮件！");
		// true 表示启动HTML格式的邮件
		String name = "欢迎";
		messageHelper.setText(
				name + "：<a href='http://10.125.72.40/admin/login.html'>http://10.125.72.40/admin/login.html</a>",
				true);
		
		// 发送邮件
		sender.send(mailMessage);
		
		System.out.println("邮件发送成功..");
	}
	
}
