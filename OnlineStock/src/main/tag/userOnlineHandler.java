package main.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class userOnlineHandler extends BodyTagSupport {
	
	private String username = "";
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public int doAfterBody(){
		if( username.equals("") ){
			username = bodyContent.getString();
		}
		return SKIP_BODY;
	}
	
	public int doEndTag(){
		if( username==null || username.equals("") ){
			JspWriter out = pageContext.getOut();
			try {
				out.println("<meta http-equiv='Refresh' content='0;url=/LoginServlet'>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return SKIP_PAGE;
		}
		return EVAL_PAGE;
	}
}
