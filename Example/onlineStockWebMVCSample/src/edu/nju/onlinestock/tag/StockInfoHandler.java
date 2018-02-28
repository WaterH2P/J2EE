package edu.nju.onlinestock.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import edu.nju.onlinestock.action.business.StockListBean;

public class StockInfoHandler extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		
		try {
			StockListBean listStock =  (StockListBean) getJspContext().findAttribute("listStock");
			JspWriter out = getJspContext().getOut();
			for (int i = 0; i < listStock.getStockList().size(); i++) {
				out.println("<tr><TD align='center'>"
						+ listStock.getStockList(i).getId() + "</TD>");
				out.println("<TD align='center'>"
						+ listStock.getStockList(i).getCompanyName() + "</TD>");
				out.println("<TD align='center'>"
						+ listStock.getStockList(i).getType() + "</TD>");
				out.println("<TD align='center'>"
						+ listStock.getStockList(i).getPrice() + "</TD>");
				out.println("<TD align='center'>"
						+ listStock.getStockList(i).getDate() + "</TD></tr>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException(e.getMessage());
		}
	}

}
