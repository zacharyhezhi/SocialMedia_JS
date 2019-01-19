package web.app.eng.dao;

import java.util.List;

import web.app.eng.dto.User;

public interface UserDAO {
	
	/**
	 * Tries to locate a user with the given username
	 * 
	 * @param	The username to use to find the user
	 * @return	The User if there is a user with the username, null otherwise.
	 */
	public User selectUser(String username);
	
	public User selectUserByUsernameAndEmail(String username, String email);
	
	/**
	 * Tries to locate a user with the given username and password
	 * 
	 * @param	The username to use to find the user
	 * @param	The password to use to find the user
	 * @return	The User if there is a user with the username, null otherwise.
	 */
	public User selectUser(String username, String password);
	
	/**
	 * Insert a new user into the user_profile database
	 * 
	 * @param	The User to insert
	 * @return
	 */
	public void insertUser(User user);
	
	/**
	 * Update an existing user in the user_profile database
	 * 
	 * @param	The User to update
	 * @return
	 */
	public void updateUser(User user);
	
	public List<User> searchUsers(String firstname, String surname);
	
	public List<User> searchUsers(int birthdate, int birthmonth, int birthyear);
	
	public List<User> searchUsers(String gender);
	
	/**
	 * Get all friends of a user
	 * 
	 * @param	The username of the User
	 * @return	The list of User the User is friends with
	 */
	public List<User> getFriendList(String username);
	
	public void banUser(String username);
	
	public void unbanUser(String username);
	
	public Boolean isFriend(String username1, String username2);
	
}
