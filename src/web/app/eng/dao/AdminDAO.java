package web.app.eng.dao;

import web.app.eng.dto.Admin;

public interface AdminDAO {
	
	public Admin selectAdmin(String username);
	
	public Admin selectAdmin(String username, String password);
	
}
