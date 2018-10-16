package engenhariaSoftware.common;

import java.util.Date;

public class Tweet {

	private String user;
	private String text;
	private long id;
	private Date createdAt;
	
	public Tweet(String user, String text, long id, Date createdAt) {
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
	
	public long getId() {
		return this.id;
	}
	
	public String confirmTweet () {
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