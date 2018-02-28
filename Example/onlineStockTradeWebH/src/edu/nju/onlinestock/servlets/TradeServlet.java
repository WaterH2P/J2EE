package edu.nju.onlinestock.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;



import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import edu.nju.onlinestock.action.business.StockListBean;
//import edu.nju.onlinestock.dao.StockDao;

import edu.nju.onlinestock.factory.ServiceFactory;
//import edu.nju.onlinestock.dao.UserDao;
import edu.nju.onlinestock.model.Stock;
import edu.nju.onlinestock.model.Trade;
import edu.nju.onlinestock.model.User;
import edu.nju.onlinestock.service.StockManageService;
import edu.nju.onlinestock.service.TradeManageService;
import edu.nju.onlinestock.service.UserManageService;

/**
 * Servlet implementation class BuyStockServlet
 */
@WebServlet("/TradeServlet")
public class TradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserManageService userService=ServiceFactory.getUserManageService();
	private static StockManageService stockService=ServiceFactory.getStockManageService();
	private static TradeManageService tradeService=ServiceFactory.getTradeManageService();
	
	public TradeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = getServletContext();
	//	HttpSession session = req.getSession(false);
	//	String message="";
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();

		User user1 = new User();
		User user2 = new User();
		
	/*	 ʹ��trade�м��������2���
	    
	    stock1.setId("1");
		stock1.setCompanyName("NJU");
		stock2.setId("2");
		stock2.setCompanyName("SE");
		
		user1.setId("1");
		user1.setUserid("mary");
		user1.setPassword("123");
		
		user2.setId("11");
		user2.setUserid("john");
		user2.setPassword("111");
		
		
	    stock1.getUsers().add(user1);
		stock1.getUsers().add(user2);

	 
	  	stockService.InsertStock(stock1); //ͬʱ��user��������2�У��Լ���trade�������2�У�����user1,user2�Ĺ�ϵ
	 
		
		stockService.DeleteStock(stock1); //stock�����ط�����ͬʱɾ��trade��stock���users���еļ�¼��ע����user����������stock�޹أ��������
		
		stock2.getUsers().add(user1);
	
		stockService.InsertStock(stock2); //ͬʱ��user��������1�У�trade�������1��
		
	*/
		//ʹ��userstovk�����������ֶ�
		stock1 =stockService.getStockById("912"); //����stock("912")
		stock2 =stockService.getStockById("2"); //����stock("2")
		user1 =userService.getUserById("1");
		user2 =userService.getUserById("1514963996789");  
		
		Trade trade1=new Trade();
		Trade trade2=new Trade();

		trade1.setStock(stock1);
		trade1.setUser(user2);
		
		trade2.setStock(stock2);
		trade2.setUser(user2);

		tradeService.trade(trade1); 
		tradeService.trade(trade2); 


		context.getRequestDispatcher("/user/welcome.html").forward(req, resp);

	
	}
}
