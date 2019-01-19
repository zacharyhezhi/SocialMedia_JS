package web.app.eng.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.app.eng.dto.Admin;
import web.app.eng.dto.User;
import web.app.eng.service.ServiceException;
import web.app.eng.service.UserService;
import web.app.eng.service.AdminService;

/**
 * Servlet implementation class AdminControlServlet
 */
@WebServlet(urlPatterns="/adminControl", displayName="AdminControlServlet")
public class AdminControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	* @see HttpServlet#HttpServlet()
	*/
	public AdminControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = new String("");
		if (request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		
		String nextPage = "adminHome.jsp";
		if (action.equals("login")) {
			AdminService adminService = new AdminService();
			Admin admin = adminService.create(request);
			try {
				adminService.login(admin.getUsername(), admin.getPassword());
				
				HttpSession session = request.getSession();
				session.setAttribute("login", "true");
				session.setAttribute("admin", admin);
				session.setMaxInactiveInterval(60*60);
			}
			catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
			}
		}
		
		else if (action.equals("search")) {
			UserService userService = new UserService();
			String tagName = request.getParameter("tagName");
			String searchValue = request.getParameter("searchValue");
			List<User> searchResults;
			if (tagName.equals("firstname")) {
				searchResults = userService.searchUsers(searchValue, "");
			}
			else if (tagName.equals("surname")) {
				searchResults = userService.searchUsers("", searchValue);
			}
			else if (tagName.equals("gender")) {
				searchResults = userService.searchUsers(searchValue);
			}
			else {
				try {
					int birthdate = Integer.parseInt(searchValue.substring(0, 2));
					int birthmonth = Integer.parseInt(searchValue.substring(3, 5));
					int birthyear = Integer.parseInt(searchValue.substring(6, 10));
					searchResults = userService.searchUsers(birthdate, birthmonth, birthyear);
				}
				catch (Exception e) {
					searchResults = null;
				}
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("display", "result");
			session.setAttribute("searchResults", searchResults);
		}
		
		else if (action.equals("userActivityReport")) {
			UserService userService = new UserService();
			User user = userService.selectUser(request.getParameter("username"));
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("display", "userActivityReport");
		}
		
		else if (action.equals("ban")) {
			HttpSession session = request.getSession();
			
			UserService userService = new UserService();
			User user = (User) session.getAttribute("user");
			userService.banUser(user.getUsername());
			user = userService.selectUser(user.getUsername());
			
			session.setAttribute("user", user);
		}
		
		else if (action.equals("unban")) {			
			HttpSession session = request.getSession();
			
			UserService userService = new UserService();
			User user = (User) session.getAttribute("user");
			userService.unbanUser(user.getUsername());
			user = userService.selectUser(user.getUsername());
			
			session.setAttribute("user", user);
		}
		
		else if (action.equals("logout")){
			HttpSession session = request.getSession();
			session.invalidate();
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/" + nextPage);
		requestDispatcher.forward(request, response);
	}
	
}
