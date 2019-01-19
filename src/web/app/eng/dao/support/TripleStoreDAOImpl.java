package web.app.eng.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

import web.app.eng.common.DBConnectionFactory;
import web.app.eng.dao.TripleStoreDAO;
import web.app.eng.dto.Log;
import web.app.eng.dto.Post;
import web.app.eng.dto.User;

public class TripleStoreDAOImpl extends DBConnectionFactory implements TripleStoreDAO {
	
	public static TripleStoreDAOImpl instance;
	
	public static TripleStoreDAOImpl getInstance() {
		if (instance == null) {
			instance = new TripleStoreDAOImpl();
		}
		
		return instance;
	}
	
	private int getNextID() {
		int id = 0;
		
		Connection connection = getConnection();
		
		String sql = "SELECT max(subject) + 1 AS id FROM entity_store;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void insertUser(User user) {
		int id = getNextID();
		
		Connection connection = getConnection();
		
		String sql = "INSERT INTO entity_store VALUES (?, ?, ?);";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "type");
			preparedStatement.setString(3, "user");
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "class");
			preparedStatement.setString(3, "node");
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "firstname");
			preparedStatement.setString(3, user.getFirstname());
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "surname");
			preparedStatement.setString(3, user.getSurname());
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "email");
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "username");
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "birthdate");
			preparedStatement.setString(3, Integer.toString(user.getBirthdate()));
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "birthmonth");
			preparedStatement.setString(3, Integer.toString(user.getBirthmonth()));
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "birthyear");
			preparedStatement.setString(3, Integer.toString(user.getBirthyear()));
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "gender");
			preparedStatement.setString(3, user.getGender());
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateUser(User user) {
		int id;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject AS id FROM entity_store WHERE predicate=? AND object=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "username");
			preparedStatement.setString(2, user.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("id");
				
				sql = "UPDATE entity_store SET object=? WHERE subject=? AND predicate=?;";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, user.getFirstname());
				preparedStatement.setInt(2, id);
				preparedStatement.setString(3, "firstname");
				preparedStatement.executeUpdate();
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, user.getSurname());
				preparedStatement.setInt(2, id);
				preparedStatement.setString(3, "surname");
				preparedStatement.executeUpdate();
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, user.getEmail());
				preparedStatement.setInt(2, id);
				preparedStatement.setString(3, "email");
				preparedStatement.executeUpdate();
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, Integer.toString(user.getBirthdate()));
				preparedStatement.setInt(2, id);
				preparedStatement.setString(3, "birthdate");
				preparedStatement.executeUpdate();
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, Integer.toString(user.getBirthmonth()));
				preparedStatement.setInt(2, id);
				preparedStatement.setString(3, "birthmonth");
				preparedStatement.executeUpdate();
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, Integer.toString(user.getBirthyear()));
				preparedStatement.setInt(2, id);
				preparedStatement.setString(3, "birthyear");
				preparedStatement.executeUpdate();
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, user.getGender());
				preparedStatement.setInt(2, id);
				preparedStatement.setString(3, "gender");
				preparedStatement.executeUpdate();
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertPost(Post post) {
		int id = getNextID();
		
		Connection connection = getConnection();
		
		String sql = "INSERT INTO entity_store VALUES (?, ?, ?);";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "type");
			preparedStatement.setString(3, "post");
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "class");
			preparedStatement.setString(3, "node");
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "number");
			preparedStatement.setString(3, Integer.toString(post.getId()));	// CHECK!!!
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, "content");
			preparedStatement.setString(3, post.getContent());
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void insertFriendOfRelation(Log log) {
		int subject, object;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject AS id FROM entity_store WHERE predicate=? AND object=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "username");
			preparedStatement.setString(2, log.getSubject());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				subject = resultSet.getInt("id");
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "username");
				preparedStatement.setString(2, log.getObject1());
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					object = resultSet.getInt("id");
					
					sql = "INSERT INTO triple_store VALUES (?, ?, ?);";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, subject);
					preparedStatement.setInt(2, 1);
					preparedStatement.setInt(3, object);
					preparedStatement.executeUpdate();
				}
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void insertPostedRelation(Log log) {
		int subject, object;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject AS id FROM entity_store WHERE predicate=? AND object=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "username");
			preparedStatement.setString(2, log.getSubject());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				subject = resultSet.getInt("id");
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "number");
				preparedStatement.setString(2, Integer.toString(log.getObject2()));
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					object = resultSet.getInt("id");
					
					sql = "INSERT INTO triple_store VALUES (?, ?, ?);";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, subject);
					preparedStatement.setInt(2, 2);
					preparedStatement.setInt(3, object);
					preparedStatement.executeUpdate();
				}
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void insertLikedRelation(Log log) {
		int subject, object;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject AS id FROM entity_store WHERE predicate=? AND object=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "username");
			preparedStatement.setString(2, log.getSubject());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				subject = resultSet.getInt("id");
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "number");
				preparedStatement.setString(2, Integer.toString(log.getObject2()));
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					object = resultSet.getInt("id");
					
					sql = "INSERT INTO triple_store VALUES (?, ?, ?);";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, subject);
					preparedStatement.setInt(2, 3);
					preparedStatement.setInt(3, object);
					preparedStatement.executeUpdate();
				}
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertRelation(Log log) {
		switch (log.getPredicate()) {
		case 3:
			insertFriendOfRelation(log);
			break;
		case 4:
			insertPostedRelation(log);
			break;
		case 5:
			insertLikedRelation(log);;
			break;
		}
	}
	
	private void deleteFriendOfRelation(Log log) {
		
	}
	
	private void deletePostedRelation(Log log) {
		
	}
	
	private void deleteLikedRelation(Log log) {
		int subject, object;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject AS id FROM entity_store WHERE predicate=? AND object=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "username");
			preparedStatement.setString(2, log.getSubject());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				subject = resultSet.getInt("id");
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "number");
				preparedStatement.setString(2, Integer.toString(log.getObject2()));
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					object = resultSet.getInt("id");
					
					sql = "DELETE FROM triple_store WHERE subject=? AND predicate=? AND object=?;";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, subject);
					preparedStatement.setInt(2, 3);
					preparedStatement.setInt(3, object);
					preparedStatement.executeUpdate();
				}
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteRelation(Log log) {
		switch (log.getPredicate()) {
		case 3:
			deleteFriendOfRelation(log);
			break;
		case 4:
			deletePostedRelation(log);
			break;
		case 5:
			deleteLikedRelation(log);;
			break;
		}
	}
	
	private int getId(String username) {
		int id = 0;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject AS id FROM entity_store "
				+ "WHERE predicate='username' AND object=? "
				+ "AND subject IN (SELECT subject FROM entity_store "
				+ "WHERE predicate='class' AND object='node');";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public List<JSONObject> getAllRelations(String username) {
		List<JSONObject> relations = null;
		int subject = getId(username);
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject, object FROM entity_store "
				+ "WHERE predicate='label' AND subject IN (SELECT subject FROM entity_store "
				+ "WHERE predicate='class' AND object='edge');";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			HashMap<Integer,String> hm = new HashMap<Integer,String>();
			while (rs.next()) {
				hm.put(rs.getInt("subject"), rs.getString("object"));
			}
			
			sql = "SELECT * FROM triple_store WHERE subject=? OR object=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, subject);
			preparedStatement.setInt(2, subject);
			rs = preparedStatement.executeQuery();
			
			relations = new ArrayList<JSONObject>();
			while (rs.next()) {
				JSONObject relation = new JSONObject();
				relation.put("from", rs.getInt("subject"));
				relation.put("to", rs.getInt("object"));
				relation.put("label", hm.get(rs.getInt("predicate")));
				
				relations.add(relation);
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(relations);
		return relations;
	}
	
	@Override
	public List<JSONObject> getFriendsOf(String username) {
		int subject = getId(username), predicate = 0;
		
		List<JSONObject> relations = null;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject FROM entity_store "
				+ "WHERE predicate='label' AND object='friendOf' "
				+ "AND subject IN (SELECT subject FROM entity_store "
				+ "WHERE predicate='class' AND object='edge');";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				predicate = rs.getInt("subject");
				
				sql = "SELECT subject, object FROM triple_store "
						+ "WHERE predicate=? AND (subject=? OR object=?);";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, predicate);
				preparedStatement.setInt(2, subject);
				preparedStatement.setInt(3, subject);
				rs = preparedStatement.executeQuery();
				relations = new ArrayList<JSONObject>();
				while (rs.next()) {
					JSONObject relation = new JSONObject();
					relation.put("from", rs.getInt("subject"));
					relation.put("to", rs.getInt("object"));
					relation.put("label", "friendOf");
					
					relations.add(relation);
				}
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(relations);
		return relations;
	}

	@Override
	public List<JSONObject> getPosted(String username) {
		int subject = getId(username), predicate = 0;
		
		List<JSONObject> relations = null;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject FROM entity_store "
				+ "WHERE predicate='label' AND object='posted' "
				+ "AND subject IN (SELECT subject FROM entity_store "
				+ "WHERE predicate='class' AND object='edge');";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				predicate = rs.getInt("subject");
				
				sql = "SELECT object FROM triple_store WHERE subject=? AND predicate=?;";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, subject);
				preparedStatement.setInt(2, predicate);
				rs = preparedStatement.executeQuery();
				relations = new ArrayList<JSONObject>();
				while (rs.next()) {
					JSONObject relation = new JSONObject();
					relation.put("from", subject);
					relation.put("to", rs.getInt("object"));
					relation.put("label", "posted");
					
					relations.add(relation);
				}
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(relations);
		return relations;
	}

	@Override
	public List<JSONObject> getLiked(String username) {
		int subject = getId(username), predicate = 0;
		
		List<JSONObject> relations = null;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject FROM entity_store "
				+ "WHERE predicate='label' AND object='liked' "
				+ "AND subject IN (SELECT subject FROM entity_store "
				+ "WHERE predicate='class' AND object='edge');";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				predicate = rs.getInt("subject");
				
				sql = "SELECT object FROM triple_store WHERE subject=? AND predicate=?;";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, subject);
				preparedStatement.setInt(2, predicate);
				rs = preparedStatement.executeQuery();
				relations = new ArrayList<JSONObject>();
				while (rs.next()) {
					JSONObject relation = new JSONObject();
					relation.put("from", subject);
					relation.put("to", rs.getInt("object"));
					relation.put("label", "liked");
					
					relations.add(relation);
				}
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(relations);
		return relations;
	}
	
	private int getId(int number) {
		int id = 0;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject AS id FROM entity_store "
				+ "WHERE predicate='number' AND object=? "
				+ "AND subject IN (SELECT subject FROM entity_store "
				+ "WHERE predicate='class' AND object='node');";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public List<JSONObject> getPostedBy(int number) {
		int object = getId(number), predicate = 0;
		
		List<JSONObject> relations = null;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject FROM entity_store "
				+ "WHERE predicate='label' AND object='posted' "
				+ "AND subject IN (SELECT subject FROM entity_store "
				+ "WHERE predicate='class' AND object='edge');";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				predicate = rs.getInt("subject");
				
				sql = "SELECT subject FROM triple_store WHERE predicate=? AND object=?;";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, predicate);
				preparedStatement.setInt(2, object);
				rs = preparedStatement.executeQuery();
				relations = new ArrayList<JSONObject>();
				while (rs.next()) {
					JSONObject relation = new JSONObject();
					relation.put("from", rs.getInt("subject"));
					relation.put("to", object);
					relation.put("label", "posted");
					
					relations.add(relation);
				}
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(relations);
		return relations;
	}

	@Override
	public List<JSONObject> getLikedBy(int number) {
		int object = getId(number), predicate = 0;
		
		List<JSONObject> relations = null;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject FROM entity_store "
				+ "WHERE predicate='label' AND object='liked' "
				+ "AND subject IN (SELECT subject FROM entity_store "
				+ "WHERE predicate='class' AND object='edge');";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				predicate = rs.getInt("subject");
				
				sql = "SELECT subject FROM triple_store WHERE predicate=? AND object=?;";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, predicate);
				preparedStatement.setInt(2, object);
				rs = preparedStatement.executeQuery();
				relations = new ArrayList<JSONObject>();
				while (rs.next()) {
					JSONObject relation = new JSONObject();
					relation.put("from", rs.getInt("subject"));
					relation.put("to", object);
					relation.put("label", "liked");
					
					relations.add(relation);
				}
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(relations);
		return relations;
	}
	
	@Override
	public List<JSONObject> searchUsers(String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<JSONObject> searchPeople(String info) {
		List<JSONObject> relations = null;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject, object FROM entity_store "
				+ "WHERE predicate='label' AND subject IN (SELECT subject FROM entity_store "
				+ "WHERE predicate='class' AND object='edge');";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			HashMap<Integer,String> hm = new HashMap<Integer,String>();
			while (rs.next()) {
				hm.put(rs.getInt("subject"), rs.getString("object"));
			}
			
			String[] DoB = info.split("/");
			boolean isDoB = true;
			try {
				for (String number : DoB) {
					Integer.parseInt(number);
				}
			}
			catch (Exception e) {
				isDoB = false;
			}
			
			if (DoB.length == 3 && isDoB) {
				sql = "SELECT * FROM triple_store "
						+ "WHERE subject IN (SELECT a.subject FROM entity_store a, entity_store b, entity_store c "
						+ "WHERE (a.predicate='birthdate' AND a.object="+DoB[0]+") "
						+ "AND (b.predicate='birthmonth' AND b.object="+DoB[1]+") "
						+ "AND (c.predicate='birthyear' AND c.object="+DoB[2]+") "
						+ "AND a.subject=b.subject "
						+ "AND a.subject=c.subject) "
						+ "OR object IN (SELECT a.subject FROM entity_store a, entity_store b, entity_store c "
						+ "WHERE (a.predicate='birthdate' AND a.object="+DoB[0]+") "
						+ "AND (b.predicate='birthmonth' AND b.object="+DoB[1]+") "
						+ "AND (c.predicate='birthyear' AND c.object="+DoB[2]+") "
						+ "AND a.subject=b.subject "
						+ "AND a.subject=c.subject);";
			}
			else {
				sql = "SELECT * FROM triple_store "
						+ "WHERE subject IN (SELECT subject FROM entity_store "
						+ "WHERE (predicate='firstname' AND object LIKE '%"+info+"%') "
						+ "OR (predicate='surname' AND object LIKE '%"+info+"%') "
						+ "OR (predicate='gender' AND object='"+info+"')) "
						+ "OR object IN (SELECT subject FROM entity_store "
						+ "WHERE (predicate='firstname' AND object LIKE '%"+info+"%') "
						+ "OR (predicate='surname' AND object LIKE '%"+info+"%') "
						+ "OR (predicate='gender' AND object='"+info+"'));";
			}
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			relations = new ArrayList<JSONObject>();
			while (rs.next()) {
				JSONObject relation = new JSONObject();
				relation.put("from", rs.getInt("subject"));
				relation.put("to", rs.getInt("object"));
				relation.put("label", hm.get(rs.getInt("predicate")));
				
				relations.add(relation);
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(relations);
		return relations;
	}
	
	@Override
	public List<JSONObject> searchPosts(String text) {
		List<JSONObject> relations = null;
		
		Connection connection = getConnection();
		
		String sql = "SELECT subject, object FROM entity_store "
				+ "WHERE predicate='label' AND subject IN (SELECT subject FROM entity_store "
				+ "WHERE predicate='class' AND object='edge');";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			HashMap<Integer,String> hm = new HashMap<Integer,String>();
			while (rs.next()) {
				hm.put(rs.getInt("subject"), rs.getString("object"));
			}
			
			sql = "SELECT * FROM triple_store "
					+ "WHERE object IN (SELECT subject FROM entity_store "
					+ "WHERE predicate='content' AND object LIKE '%"+text+"%' "
					+ "AND subject IN (SELECT subject FROM entity_store "
					+ "WHERE predicate='type' AND object='post'));";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			relations = new ArrayList<JSONObject>();
			while (rs.next()) {
				JSONObject relation = new JSONObject();
				relation.put("from", rs.getInt("subject"));
				relation.put("to", rs.getInt("object"));
				relation.put("label", hm.get(rs.getInt("predicate")));
				
				relations.add(relation);
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(relations);
		return relations;
	}
	
	@Override
	public List<JSONObject> getEntities(List<JSONObject> relations) {
		List<JSONObject> entities = new ArrayList<JSONObject>();
		
		Connection connection = getConnection();
		
		Set<Integer> subjects = new HashSet<>();
		int subject;
		String sql = "", predicate, object;
		try {
			for (JSONObject relation : relations) {
				for (String key : Arrays.asList("from", "to")) {
					subject = relation.getInt(key);
					if (!subjects.contains(subject)) {
						subjects.add(subject);
						sql = "SELECT object FROM entity_store WHERE subject=? AND predicate=?;";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setInt(1, subject);
						preparedStatement.setString(2, "type");
						ResultSet rs = preparedStatement.executeQuery();
						if (!rs.next()) return null;
						object = rs.getString("object");
						if (object.equals("user"))
							predicate = "username";
						else
							predicate = "number";
						
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setInt(1, subject);
						preparedStatement.setString(2, predicate);
						rs = preparedStatement.executeQuery();
						if (!rs.next()) return null;
						JSONObject entity = new JSONObject();
						entity.put("id", subject);
						entity.put("label", rs.getString("object"));
						entity.put("shape", "image");
						entity.put("image", "visjs/img/"+object+".png");
						
						entities.add(entity);
					}
				}
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(entities);
		return entities;
	}

	
}
