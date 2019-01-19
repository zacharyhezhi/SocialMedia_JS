package web.app.eng.service;

import javax.servlet.http.HttpServletRequest;

import web.app.eng.dao.AdminDAO;
import web.app.eng.dao.support.AdminDAOImpl;
import web.app.eng.dto.Admin;

public class AdminService {
	
	private AdminDAO adminDAO = AdminDAOImpl.getInstance();
	
	public Admin create(HttpServletRequest request) {
		Admin admin = new Admin();
		
		if (request.getParameter("username") != null && !request.getParameter("username").equals("")) {
			admin.setUsername(request.getParameter("username"));
		}
		if (request.getParameter("password") != null && !request.getParameter("password").equals("")) {
			admin.setPassword(request.getParameter("password"));
		}
		
	    return admin;
	}
	
	public void login(String username, String password) throws ServiceException {
		Admin admin = adminDAO.selectAdmin(username);
		if (admin == null) {
			throw new ServiceException("Specified username does not exist!");
		}
		
		admin =  adminDAO.selectAdmin(username, password);
		if (admin == null) {
			throw new ServiceException("Incorrect password for " + username + "!");
		}
	}
	
}
