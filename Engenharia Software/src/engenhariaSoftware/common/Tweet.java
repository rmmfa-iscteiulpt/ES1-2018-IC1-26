package engenhariaSoftware.common;

import java.util.Date;

/**
 * @author Frederico
 * @since November,2018
 * 
 *  The Tweet class access to academic information made available in Twitter.
 *      
 *
 */

public class Tweet {

	private String user;
	private String text;
	private long id;
	private Date createdAt;
	
	/**
	 * Tweet constructor ,
	 * initialization user, text, id, createAt.
	 * @param user this is the username 
	 * @param text this is what the username wrote
	 * @param id this is the username id
	 * @param createdAt this is the date of the text
	 */
	
	public Tweet(String user, String text, long id, Date createdAt) {
		this.user = user;
		this.text = text;
		this.id = id;
		this.createdAt = createdAt;
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
	 * Getter for the tweet text.
	 * @return the text
	 */
	
	public String getText() {
		return this.text;
	}
	
	/**
	 * getId,
	 * Getter for the tweet id.
	 * @return id
	 */
	
	public long getId() {
		return this.id;
	}
	
	/**
	 * tweetHeader,
	 * Getter for the tweet header.
	 * @return user and date of creation
	 */
	
	public String tweetHeader () {
		return "Twitter: " + this.user + ": " + this.createdAt;
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