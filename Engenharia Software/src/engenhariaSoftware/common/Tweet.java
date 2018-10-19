package engenhariaSoftware.common;

import java.util.Date;

public class Tweet {

	private String user;
	private String text;
	private long id;
	private Date createdAt;
	
	/**
	 * Tweet constructor
	 * @param user
	 * @param text
	 * @param id
	 * @param createdAt
	 */
	
	
	public Tweet(String user, String text, long id, Date createdAt) {
		this.user = user;
		this.text = text;
		this.id = id;
		this.createdAt = createdAt;
	}
	
	/**
	 * Getter for user name
	 * @return
	 */

	public String getUserName() {
		return this.user;
	}
	
	/**
	 * Getter for the tweet text
	 * @return
	 */
	
	public String getText() {
		return this.text;
	}
	
	/**
	 * Getter for the tweet id
	 * @return
	 */
	
	public long getId() {
		return this.id;
	}
	
	/**
	 * Getter for the tweet header (user name + date of creation)
	 * @return
	 */
	
	public String tweetHeader () {
		return this.user + ": " + this.createdAt;
	}
	
	/**
	 * Getter for the date of creation
	 * @return
	 */
	
	public Date getCreatedAt() {
		return this.createdAt;
	}
	
	@Override
	public String toString() {
		return this.user + " - " + this.text;
	}
	
	
	
}