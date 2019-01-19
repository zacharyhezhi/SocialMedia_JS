package web.app.eng.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.app.eng.common.DBConnectionFactory;
import web.app.eng.dao.AdminDAO;
import web.app.eng.dto.Admin;

public class AdminDAOImpl extends DBConnectionFactory implements AdminDAO {
	
	public static AdminDAOImpl instance;
	
	public static AdminDAOImpl getInstance() {
		if (instance == null) {
			instance = new AdminDAOImpl();
		}
		
		return instance;
	}
	
	private Admin convertAdmin(ResultSet resultSet) throws SQLException {
		Admin admin = new Admin();
		
		admin.setUsername(resultSet.getString("username"));
		admin.setPassword(resultSet.getString("password"));
		admin.setPassword(resultSet.getString("email"));
		
		return admin;
	}
	
	@Override
	public Admin selectAdmin(String username) {
		Connection connection = getConnection();
		
		String sql = "SELECT * FROM admin_profile WHERE username = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Admin admin = convertAdmin(resultSet);
				connection.close();
				return admin;
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Admin selectAdmin(String username, String password) {
		Connection connection = getConnection();
		
		String sql = "SELECT * FROM admin_profile WHERE username = ? AND password = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Admin admin = convertAdmin(resultSet);
				connection.close();
				return admin;
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
