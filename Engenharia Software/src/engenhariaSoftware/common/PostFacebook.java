package engenhariaSoftware.common;

import java.util.Date;

public class PostFacebook {
	
	private String user;
	private String text;
	private String id;
	private Date createdAt;
	
	public PostFacebook(String user, String text, String id, Date createdAt) {
		this.user = user;
		this.text = text;
		this.id = id;
		this.createdAt = createdAt;
	}
	
	public String getUserName() {
		return this.user;
	}
	
	
	public String getText() {
		return this.text;
	}
	
	
	
	public String getId() {
		return this.id;
	}
	
	
	
	public String postHeader () {
		return this.user + ": " + this.createdAt;
	}
	
	
	public Date getCreatedAt() {
		return this.createdAt;
	}
	
	@Override
	public String toString() {
		return this.user + " - " + this.text;
	}
	
}
