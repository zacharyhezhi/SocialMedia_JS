package web.app.eng.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.app.eng.common.DBConnectionFactory;
import web.app.eng.dao.PostDAO;
import web.app.eng.dto.Log;
import web.app.eng.dto.Post;

public class PostDAOImpl extends DBConnectionFactory implements PostDAO {
	
	public static PostDAOImpl instance;
	
	public static PostDAOImpl getInstance() {
		if (instance == null) {
			instance = new PostDAOImpl();
		}
		
		return instance;
	}
	
	private Post convertPost(ResultSet resultSet) throws SQLException {
		Post post = new Post();
		
		post.setId(resultSet.getInt("id"));
		post.setDatetime(resultSet.getString("datetime"));
		post.setPoster(resultSet.getString("subject"));
		post.setContent(resultSet.getString("content"));
		
		return post;
	}
	
	@Override
	public Post selectPost(int id) {
		Connection connection = getConnection();
		
		String sql = "SELECT * FROM post WHERE id=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Post post = convertPost(resultSet);
				connection.close();
				return post;
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Log insertPost(Post post) {
		Log log = null;
		
		Connection connection = getConnection();
		
		String sql = "INSERT INTO post (content) VALUES (?);";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, post.getContent());
			preparedStatement.executeUpdate();
			
			sql = "SELECT max(id) AS id FROM post;";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				log = new Log();
				log.setSubject(post.getPoster());
				log.setPredicate(4);
				log.setObject2(resultSet.getInt("id"));
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return log;
	}
	
	@Override
	public List<Post> getPostList(String username) {
		Connection connection = getConnection();
		
		List<Post> posts = new ArrayList<Post>();
		
		String sql = "SELECT t1.id, datetime, subject, content FROM (post AS t1) "
				+ "INNER JOIN ((SELECT t2.datetime, subject, object2 AS id FROM log AS t2, "
				+ "(SELECT datetime, object1 AS friend FROM log WHERE predicate=3 AND subject=? "
				+ "UNION SELECT datetime, subject AS friend FROM log WHERE predicate=3 AND object1=?) AS t3 "
				+ "WHERE t2.subject=t3.friend AND predicate=4) "
				+ "UNION (SELECT datetime, subject, object2 AS id FROM log WHERE subject=? AND predicate=4)) "
				+ "AS t4 ON t1.id = t4.id ORDER BY t1.id DESC;";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Post post = convertPost(resultSet);
				
				sql = "SELECT count(*) AS likes FROM log WHERE predicate=5 AND object2=?;";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, post.getId());
				ResultSet resultSet2 = preparedStatement.executeQuery();
				if (resultSet2.next()) {
					post.setLikes(resultSet2.getInt("likes"));
				}
				
				sql = "SELECT * FROM log WHERE predicate=5 AND subject=? AND object2=?;";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setInt(2, post.getId());
				ResultSet resultSet3 = preparedStatement.executeQuery();
				if (resultSet3.next()) {
					post.setLiked(true);
				}
				
				posts.add(post);
			}
			
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return posts;
	}
	
}
