package web.app.eng.dto;

public class Post {
	private int id;
	private String datetime;
	private String poster;
	private String content;
	private int likes;
	private boolean liked;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDatetime() {
		return datetime;
	}
	
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public String getPoster() {
		return poster;
	}
	
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public boolean isLiked() {
		return liked;
	}
	
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	
}
