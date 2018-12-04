package engenhariaSoftware.common;

import java.util.Date;

public class TweetPostEmail {
	
	private String type;
	private String user;
	private String text;
	private String id;
	private String normalDate;
	private Date createdAt;
	
	/**
	 * TweetPostEmail constructor ,
	 * initialization type, user, text, id, createAt.
	 * @param type this is the post type
	 * @param user this is the username 
	 * @param text this is what the username wrote
	 * @param id this is the username id
	 * @param createdAt this is the date of the text
	 */
	
	public TweetPostEmail(String type, String user, String text, String id, Date createdAt) {
		this.type = type;
		this.user = user;
		this.text = text;
		this.id = id;
		this.createdAt = createdAt;
	}
	
	public String getNormalDate() {
		return this.normalDate;
	}
	
	public void setNormalDate(String date) {
		this.normalDate = date;
	}
	
	/*
	 * Getter for the type
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * getUserName,
	 * Getter for user name.
	 * @return the user
	 */
	
	public String getUserName() {
		return this.user;
	}
	
	/**
	 * getText,
	 * Getter for the text of the facebook Post.
	 * @return the text
	 */
	
	public String getText() {
		return this.text;
	}
	
	/**
	 * getId,
	 * Getter for the Post id.
	 * @return id
	 */
	
	public String getId() {
		return this.id;
	}
	
	/**
	 * postHeader,
	 * Getter for the post header.
	 * @return user and date of creation
	 */
	
	public String postHeader () {
		return type + ": " + this.user + ": " + this.createdAt;
	}
	
	/**
	 * getCreatedAt,
	 * Getter for the date of creation.
	 * @return date of creation
	 */
	
	public Date getCreatedAt() {
		return this.createdAt;
	}
	
	@Override
	public String toString() {
		return this.user + " - " + this.text;
	}
	
}
