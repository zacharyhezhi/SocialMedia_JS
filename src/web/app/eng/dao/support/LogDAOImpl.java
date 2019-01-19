package web.app.eng.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.app.eng.common.DBConnectionFactory;
import web.app.eng.dao.LogDAO;
import web.app.eng.dto.Log;

public class LogDAOImpl extends DBConnectionFactory implements LogDAO {
	
	public static LogDAOImpl instance;
	
	public static LogDAOImpl getInstance() {
		if (instance == null) {
			instance = new LogDAOImpl();
		}
		
		return instance;
	}
	
	private Log convertLog(ResultSet resultSet) throws SQLException {
		Log log = new Log();
		
		log.setDatetime(resultSet.getString("datetime"));
		log.setSubject(resultSet.getString("subject"));
		log.setPredicate(resultSet.getInt("predicate"));
		log.setObject1(resultSet.getString("object1"));
		log.setObject2(resultSet.getInt("object2"));
		
		return log;
	}
	
	@Override
	public Log selectLog(Log log) {
		Connection connection = getConnection();
		
		String sql = "";
		switch (log.getPredicate()) {
		case 1:	// 'subject' joined UNSWBook
			sql = "SELECT * FROM log WHERE subject='" + log.getSubject() + "' AND predicate=1;";
			break;
		case 2:	// 'subject' sent a friend request to 'object1'
		case 3:	// 'subject's friend request is accepted by 'object1'
			sql = "SELECT * FROM log WHERE subject='" + log.getSubject() + "' AND predicate=" + log.getPredicate() + " AND object1='" + log.getObject1() + "';";
			break;
		case 4:	// 'subject' create a post with post id 'object2'
		case 5:	// 'subject' likes a post with post id 'object2'
			sql = "SELECT * FROM log WHERE subject='" + log.getSubject() + "' AND predicate=" + log.getPredicate() + " AND object2=" + log.getObject2() + ";";
			break;
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				log = convertLog(resultSet);
				connection.close();
				return log;
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void deleteLog(Log log) {
		Connection connection = getConnection();
		
		String sql = "";
		switch (log.getPredicate()) {
		case 1:
			sql = "DELETE FROM log WHERE subject='" + log.getSubject() + "' AND predicate=1;";
			break;
		case 2:
		case 3:
			sql = "DELETE FROM log WHERE subject='" + log.getSubject() + "' AND predicate=" + log.getPredicate() + " AND object1='" + log.getObject1() + "';";
			break;
		case 4:
		case 5:
			sql = "DELETE FROM log WHERE subject='" + log.getSubject() + "' AND predicate=" + log.getPredicate() + " AND object2=" + log.getObject2() + ";";
			break;
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertLog(Log log) {
		Connection connection = getConnection();
		
		String sql = "";
		switch (log.getPredicate()) {
		case 1:	// 'subject' joined UNSWBook
			sql = "INSERT INTO log (subject, predicate) VALUES ('" + log.getSubject() + "', " + Integer.toString(log.getPredicate()) + ");";
			break;
		case 2:	// 'subject' sent a friend request to 'object1'
		case 3:	// 'subject's friend request is accepted by 'object1'
			sql = "INSERT INTO log (subject, predicate, object1) VALUES ('" + log.getSubject() + "', " + Integer.toString(log.getPredicate()) + ", '" + log.getObject1() + "');";
			break;
		case 4:	// 'subject' create a post with post id 'object2'
		case 5:	// 'subject' likes a post with post id 'object2'
			sql = "INSERT INTO log (subject, predicate, object2) VALUES ('" + log.getSubject() + "', " + Integer.toString(log.getPredicate()) + ", '" + Integer.toString(log.getObject2()) + "');";
			break;
		case 6:	// 'subject's post id 'object2' contains reference to bullying
			sql = "INSERT INTO log (subject, predicate, object2) VALUES ('" + log.getSubject() + "', " + Integer.toString(log.getPredicate()) + ", '" + Integer.toString(log.getObject2()) + "');";
			break;
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Log> getNotifications(String username) {
		Connection connection = getConnection();
		
		List<Log> logs = new ArrayList<Log>();
		
		// received friend request
		// friend request gets accepted
		// post gets liked
		String sql = "(SELECT * FROM log WHERE predicate=2 AND object1='" + username + "') "
				+ "UNION (SELECT * FROM log WHERE predicate=3 AND subject='" + username + "') "
				+ "UNION (SELECT * FROM log WHERE predicate=5 AND subject!='" + username + "' AND object2 IN (SELECT object2 FROM log WHERE predicate=4 AND subject='" + username + "')) "
				+ "ORDER BY datetime DESC;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Log log = convertLog(resultSet);
				logs.add(log);
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return logs;
	}
	
	@Override
	public List<Log> getActivities(String username) {
		Connection connection = getConnection();
		
		List<Log> logs = new ArrayList<Log>();
		
		String sql = "SELECT * FROM log WHERE subject=? ORDER BY datetime DESC;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Log log = convertLog(resultSet);
				logs.add(log);
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return logs;
	}
	
}
