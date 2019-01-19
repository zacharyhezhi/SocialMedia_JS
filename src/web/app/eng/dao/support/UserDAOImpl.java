package web.app.eng.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.app.eng.common.DBConnectionFactory;
import web.app.eng.dao.UserDAO;
import web.app.eng.dto.User;

public class UserDAOImpl extends DBConnectionFactory implements UserDAO {
	
	public static UserDAOImpl instance;
	
	public static UserDAOImpl getInstance() {
		if (instance == null) {
			instance = new UserDAOImpl();
		}
		
		return instance;
	}
	
	private User convertUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		
		user.setFirstname(resultSet.getString("firstname"));
		user.setSurname(resultSet.getString("surname"));
		user.setEmail(resultSet.getString("email"));
		user.setUsername(resultSet.getString("username"));
		user.setPassword(resultSet.getString("password"));
		user.setBirthdate(resultSet.getInt("birthdate"));
		user.setBirthmonth(resultSet.getInt("birthmonth"));
		user.setBirthyear(resultSet.getInt("birthyear"));
		user.setGender(resultSet.getString("gender"));
		user.setBanned(resultSet.getBoolean("banned"));
		user.setVerified(resultSet.getBoolean("verified"));
		
		return user;
	}
	
	@Override
	public User selectUser(String username) {
		Connection connection = getConnection();
		
		String sql = "SELECT * FROM user_profile WHERE username=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User user = convertUser(resultSet);
				connection.close();
				return user;
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public User selectUserByUsernameAndEmail(String username, String email) {
		Connection connection = getConnection();
		
		String sql = "SELECT * FROM user_profile WHERE username=? AND email=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User user = convertUser(resultSet);
				connection.close();
				return user;
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public User selectUser(String username, String password) {
		Connection connection = getConnection();
		
		String sql = "SELECT * FROM user_profile WHERE username=? AND password=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User user = convertUser(resultSet);
				connection.close();
				return user;
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void insertUser(User user) {
		Connection connection = getConnection();
		
		String sql = "INSERT INTO user_profile (firstname, surname, email, username, password, birthdate, birthmonth, birthyear, gender, banned, verified) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getUsername());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setInt(6, user.getBirthdate());
			preparedStatement.setInt(7, user.getBirthmonth());
			preparedStatement.setInt(8, user.getBirthyear());
			preparedStatement.setString(9, user.getGender());
			preparedStatement.setBoolean(10, user.isBanned());
			preparedStatement.setBoolean(11, user.isVerified());
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateUser(User user) {
		Connection connection = getConnection();
		
		String sql = "UPDATE user_profile SET firstname=?, surname=?, email=?, password=?, birthdate=?, birthmonth=?, birthyear=?, gender=?, banned=?, verified=? WHERE username=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setInt(5, user.getBirthdate());
			preparedStatement.setInt(6, user.getBirthmonth());
			preparedStatement.setInt(7, user.getBirthyear());
			preparedStatement.setString(8, user.getGender());
			preparedStatement.setBoolean(9, user.isBanned());
			preparedStatement.setBoolean(10, user.isVerified());
			preparedStatement.setString(11, user.getUsername());
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<User> searchUsers(String firstname, String surname) {
		Connection connection = getConnection();
		
		List<User> users = new ArrayList<User>();
		
		String sql = "SELECT * FROM user_profile WHERE ";
		int count = 0;
		if (firstname != null && firstname != "") {
			sql = sql + "firstname='" + firstname + "'";
			count++;
		}
		if (surname != null && surname != "") {
			if (count > 0) {
				sql = sql + " AND surname='" + surname + "'";
			}
			else {
				sql = sql + "surname='" + surname + "'";
			}
			count++;
		}
		sql = sql + ";";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
                User user = convertUser(resultSet);
                users.add(user);
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	@Override
	public List<User> searchUsers(int birthdate, int birthmonth, int birthyear) {
		Connection connection = getConnection();
		
		List<User> users = new ArrayList<User>();
		
		String sql = "SELECT * FROM user_profile WHERE ";
		int count = 0;
		if (birthdate > 0) {
			sql = sql + "birthdate=" + birthdate;
			count++;
		}
		if (birthmonth > 0) {
			if (count > 0) {
				sql = sql + " AND birthmonth=" + birthmonth;
			}
			else {
				sql = sql + "birthmonth=" + birthmonth;
			}
			count++;
		}
		if (birthyear > 0) {
			if (count > 0) {
				sql = sql + " AND birthyear=" + birthyear;
			}
			else {
				sql = sql + "birthyear=" + birthyear;
			}
			count++;
		}
		sql = sql + ";";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = convertUser(resultSet);
				users.add(user);
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	@Override
	public List<User> searchUsers(String gender) {
		Connection connection = getConnection();
		
		List<User> users = new ArrayList<User>();
		
		String sql = "SELECT * FROM user_profile WHERE gender='" + gender + "';";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = convertUser(resultSet);
				users.add(user);
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	@Override
	public List<User> getFriendList(String username) {
		Connection connection = getConnection();
		
		List<User> users = new ArrayList<User>();
		
		String sql = "SELECT t2.* FROM (SELECT object1 FROM log WHERE predicate=3 AND subject=? "
				+ "UNION SELECT subject FROM log WHERE predicate=3 AND object1=?) AS t1 "
				+ "INNER JOIN (SELECT * FROM user_profile) AS t2 ON t1.object1 = t2.username;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = convertUser(resultSet);
				users.add(user);
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void banUser(String username) {
		Connection connection = getConnection();
		
		String sql = "UPDATE user_profile SET banned=? WHERE username=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void unbanUser(String username) {
		Connection connection = getConnection();
		
		String sql = "UPDATE user_profile SET banned=? WHERE username=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean isFriend(String username1, String username2) {
		Boolean friend = false;
		
		Connection connection = getConnection();
		
		String sql = "SELECT * FROM log WHERE predicate=3 AND ((subject=? AND object1=?) OR (subject=? AND object1=?));";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username1);
			preparedStatement.setString(2, username2);
			preparedStatement.setString(3, username2);
			preparedStatement.setString(4, username1);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				friend = true;
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return friend;
	}
	
}
