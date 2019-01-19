package web.app.eng.dao;

import java.util.List;

import org.json.JSONObject;

import web.app.eng.dto.Log;
import web.app.eng.dto.Post;
import web.app.eng.dto.User;

public interface TripleStoreDAO {
	
	public void insertUser(User user);
	
	public void updateUser(User user);
	
	public void insertPost(Post post);
	
	public void insertRelation(Log log);
	
	public void deleteRelation(Log log);

	public List<JSONObject> getAllRelations(String username);
	
	public List<JSONObject> getFriendsOf(String username);
	
	public List<JSONObject> getPosted(String username);
	
	public List<JSONObject> getLiked(String username);
	
	public List<JSONObject> getPostedBy(int number);
	
	public List<JSONObject> getLikedBy(int number);
	
	public List<JSONObject> searchUsers(String searchValue);

	public List<JSONObject> searchPeople(String info);
	
	public List<JSONObject> searchPosts(String text);
	
	public List<JSONObject> getEntities(List<JSONObject> relations);
	
}
