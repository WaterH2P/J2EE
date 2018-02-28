package edu.nju.userfile.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.userfile.dao.UserDao;
import edu.nju.userfile.model.File;
import edu.nju.userfile.model.User;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/UserFileServlet")
public class UserFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB UserDao dao;      
	private User user= new User();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request,response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request,response);
	}

	private void execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub

		//修改User Name
		User user=dao.getUserByID(Long.valueOf("1514963996789"));
		user.setName("Mary Wang");
		dao.updateUser(user);
		
		//查找所有User的Files		
		response.setContentType("text/html;charset=utf-8");
		List list=dao.getUserList();
		String userID=null;
	
		PrintWriter out = response.getWriter();
		out.println("<html><body>");

		out.println("User File List:  ");

		for(int i=0; i<list.size();i++){
			User u=(User) list.get(i);
			System.out.println("用户ID："+u.getId());

			if(u.getUserid()!=null) {
				if(!u.getUserid().equals(userID)) { //去除重复项
					userID=u.getUserid();
					out.println("用户UserID："+userID);
					Iterator iterator=(Iterator) u.getFiles().iterator();
					while(iterator.hasNext()){
						File f=(File)iterator.next();
						out.println("拥有的文件 "+f.getFileName());
					}
				}
			}
		}

		out.println("</p>");
	}
}
