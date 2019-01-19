package web.app.eng.control;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import web.app.eng.dto.Post;
import web.app.eng.dto.User;
import web.app.eng.service.PostService;
import web.app.eng.service.ServiceException;
import web.app.eng.service.TripleStoreService;
import web.app.eng.service.UserService;

/**
 * Servlet implementation class UserControlServlet
 */
@WebServlet(urlPatterns="/userControl", displayName="UserControlServlet")
public class UserControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserControlServlet() {
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
		
		HttpSession session = request.getSession();
		
		String action = new String("");
		if (request.getParameter("action") != null) {
			action = request.getParameter("action");
		}
		
		if (action.equals("registration")) {
			try {
				UserService userService = new UserService();
				User user = userService.create(request);
				userService.insertUser(user);
				
				session.setAttribute("user", null);
				session.setAttribute("display", "confirmEmail");
			}
			catch (Exception e) {
				request.setAttribute("error", e.getMessage());
			}
		}
		
		else if (action.equals("confirm")){
			try {
				UserService userService = new UserService();
				userService.confirmEmail(request.getParameter("username"));
				
				session.setAttribute("user", null);
				session.setAttribute("display", "registrationComplete");
			}
			catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
			}
		}
		
		else if (action.equals("login")) {
			try {
				UserService userService = new UserService();
				User user = userService.create(request);
				user = userService.login(user.getUsername(), user.getPassword());
				
				session.setAttribute("user", user);
				session.setAttribute("display", "wall");
				session.setMaxInactiveInterval(60*60);
			}
			catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
			}
		}
		
		else if (action.equals("acceptFriend")){
			try {
				UserService userService = new UserService();
				userService.acceptFriend(request);
				
				request.setAttribute("username", request.getParameter("subject"));
				if (session.getAttribute("user") != null && 
						!session.getAttribute("user").equals(request.getParameter("object1"))) {
					session.setAttribute("user", null);
				}
				session.setAttribute("display", "acceptFriend");
			}
			catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
			}
		}
		
		// All actions past this requires login
		else if (request.getSession().getAttribute("user") == null) {
			session.setAttribute("display", "registration");
		}
		
		else if (action.equals("search")) {
			String tagName = request.getParameter("tagName");
			String searchValue = request.getParameter("searchValue");
			
			UserService userService = new UserService();
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
			
			session.setAttribute("searchResults", searchResults);
			session.setAttribute("display", "result");
		}
		
		else if (action.equals("profile")) {
			String username = request.getParameter("username");
			
			UserService userService = new UserService();
			User otherUser = userService.selectUser(username);
			
			session.setAttribute("otherUser", otherUser);
			session.setAttribute("display", "profile");
		}
		
		else if (action.equals("addFriend")) {
			try {
				User user = (User) session.getAttribute("user");
				UserService userService = new UserService();
				userService.addFriend(user, request);
				
				session.setAttribute("display", "profile");
			}
			catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (action.equals("home")) {
			session.setAttribute("display", "wall");
		}
		
		else if (action.equals("userProfile")) {
			session.setAttribute("display", "userProfile");
		}
		
		else if (action.equals("editProfile")) {
			session.setAttribute("display", "editProfile");
		}
		
		else if (action.equals("update")) {
			User user = (User) session.getAttribute("user");
			
			UserService userService = new UserService();
			user = userService.create(user, request);
			userService.updateUser(user);
			
			session.setAttribute("user", user);
			session.setAttribute("display", "userProfile");
		}
		
		else if (action.equals("post")) {
			PostService postService = new PostService();
			Post post = postService.createPost(request);
			try {
				postService.insertPost(post);
			}
			catch (MessagingException e) {
				e.printStackTrace();
			}
			
			session.setAttribute("display", "wall");
		}
		
		else if (action.equals("like")) {
			try {
				User user = (User) session.getAttribute("user");
				
				PostService postService = new PostService();
				postService.likePost(user, Integer.parseInt(request.getParameter("id")));
				
				session.setAttribute("display", "wall");
			}
			catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
			}
		}
		
		else if (action.equals("unlike")) {
			try {
				User user = (User) session.getAttribute("user");
				
				PostService postService = new PostService();
				postService.unlikePost(user, Integer.parseInt(request.getParameter("id")));
				
				session.setAttribute("display", "wall");
			}
			catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
			}
		}
		
		else if (action.equals("graph")) {
			User user = (User) session.getAttribute("user");
			TripleStoreService tripleStoreService = new TripleStoreService();
			List<JSONObject> relations = tripleStoreService.getAllRelations(user.getUsername());
			List<JSONObject> entities = tripleStoreService.getEntities(relations);
			
			request.setAttribute("relations", relations);
			request.setAttribute("entities", entities);
			session.setAttribute("display", "graph");
		}
		
		else if (action.equals("graphSearch")) {
			List<JSONObject> relations = null;
			List<JSONObject> entities = null;
			
			String tagName = request.getParameter("tagName");
			String searchValue = request.getParameter("searchValue");
			
			TripleStoreService tripleStoreService = new TripleStoreService();
			if (tagName.equals("user")) {
				relations = tripleStoreService.searchPeople(searchValue);
				entities = tripleStoreService.getEntities(relations);
			}
			else if (tagName.equals("post")) {
				relations = tripleStoreService.searchPosts(searchValue);
				entities = tripleStoreService.getEntities(relations);
			}
			else if (tagName.equals("friendsOf")) {
				relations = tripleStoreService.getFriendsOf(searchValue);
				entities = tripleStoreService.getEntities(relations);
			}
			
			request.setAttribute("relations", relations);
			request.setAttribute("entities", entities);
			session.setAttribute("display", "graph");
		}
		
		else if (action.equals("logout")) {
			session.invalidate();
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/userHome.jsp");
		requestDispatcher.forward(request, response);
	}
	
}
