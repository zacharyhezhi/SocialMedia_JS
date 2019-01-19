package web.app.eng.dto;

public class Log {
	private String datetime;
	private String subject;
	private int predicate;
	private String object1;
	private int object2;
	
	public String getDatetime() {
		return datetime;
	}
	
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public int getPredicate() {
		return predicate;
	}
	
	public void setPredicate(int predicate) {
		this.predicate = predicate;
	}
	
	public String getObject1() {
		return object1;
	}
	
	public void setObject1(String object1) {
		this.object1 = object1;
	}
	
	public int getObject2() {
		return object2;
	}
	
	public void setObject2(int object2) {
		this.object2 = object2;
	}
}
