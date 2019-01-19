package web.app.eng.dao;

import java.util.List;

import web.app.eng.dto.Log;

public interface LogDAO {
	
	public Log selectLog(Log log);
	
	/**
	 * Insert a new log into the log database.
	 * 
	 * @param	The Log to insert
	 * @return
	 */
	public void insertLog(Log log);
	
	public void deleteLog(Log log);
	
	public List<Log> getNotifications(String username);
	
	public List<Log> getActivities(String username);
	
}
