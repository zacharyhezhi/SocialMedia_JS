package web.app.eng.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;

import web.app.eng.common.RestPost;
import web.app.eng.dao.PostDAO;
import web.app.eng.dao.TripleStoreDAO;
import web.app.eng.dao.support.PostDAOImpl;
import web.app.eng.dao.support.TripleStoreDAOImpl;
import web.app.eng.dto.Log;
import web.app.eng.dto.Post;
import web.app.eng.dto.User;

public class PostService {
	
	private PostDAO postDAO = PostDAOImpl.getInstance();
	private TripleStoreDAO tripleStoreDAO = TripleStoreDAOImpl.getInstance();
	
	public Post createPost(HttpServletRequest request) {
		Post post = new Post();
		
		if (request.getParameter("poster") != null && !request.getParameter("poster").equals("")) {
			post.setPoster(request.getParameter("poster"));
		}
		if (request.getParameter("content") != null && !request.getParameter("content").equals("")) {
			post.setContent(request.getParameter("content"));
		}
		
		return post;
	}
	
	public void insertPost(Post post) throws ClientProtocolException, IOException, MessagingException {
		Log log = postDAO.insertPost(post);
		post.setId(log.getObject2());
		tripleStoreDAO.insertPost(post);
		
		LogService logService = new LogService();
		logService.insertLog(log);
		
		RestPost restPost = new RestPost();
		List<String> bullyingKeywords = restPost.ExtractBullyingKeywords(post.getContent());
		if (!bullyingKeywords.isEmpty()) {
			EmailService.sendBullyingNotification(post, bullyingKeywords);
			log.setPredicate(6);
			logService.insertLog(log);
		}
	}
	
	public List<Post> getPostList(String username) {
		return postDAO.getPostList(username);
	}
	
	public void likePost(User user, int id) {
		Log log = new Log();
		log.setSubject(user.getUsername());
		log.setPredicate(5);
		log.setObject2(id);
		
		LogService logService = new LogService();
		if (logService.selectLog(log) != null) {
			throw new ServiceException("You have already liked this post!");
		}
		else {
			logService.insertLog(log);
		}
	}
	
	public void unlikePost(User user, int id) {
		Log log = new Log();
		log.setSubject(user.getUsername());
		log.setPredicate(5);
		log.setObject2(id);
		
		LogService logService = new LogService();
		if (logService.selectLog(log) == null) {
			throw new ServiceException("You haven't liked this post!");
		}
		else {
			logService.deleteLog(log);
		}
	}
	
}
