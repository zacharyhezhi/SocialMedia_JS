package web.app.eng.service;

import java.util.List;

import web.app.eng.dao.LogDAO;
import web.app.eng.dao.TripleStoreDAO;
import web.app.eng.dao.support.LogDAOImpl;
import web.app.eng.dao.support.TripleStoreDAOImpl;
import web.app.eng.dto.Log;

public class LogService {
	
	private LogDAO logDAO = LogDAOImpl.getInstance();
	private TripleStoreDAO tripleStoreDAO = TripleStoreDAOImpl.getInstance();
	
	public Log selectLog(Log log) {
		return logDAO.selectLog(log);
	}
	
	public void insertLog(Log log) {
		logDAO.insertLog(log);
		tripleStoreDAO.insertRelation(log);
	}
	
	public void deleteLog(Log log) {
		logDAO.deleteLog(log);
		tripleStoreDAO.deleteRelation(log);
	}
	
	public List<Log> getNotifications(String username) {
		return logDAO.getNotifications(username);
	}
	
	public List<Log> getActivities(String username) {
		return logDAO.getActivities(username);
	}
	
}
