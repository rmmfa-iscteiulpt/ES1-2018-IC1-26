package engenhariaSoftware.common;

import java.util.Date;

public class Email {

	private String user;
	private String content;
	private long id;
	private Date date;
	
	public Email(String user, String content, long id, Date date) {
		super();
		this.user = user;
		this.content = content;
		this.id = id;
		this.date = date;
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
	
	public String getContent() {
		return this.content;
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
	
	public String mailHeader () {
		return "Outlook: " + this.user + ": " + this.date;
	}
	
	/**
	 * getCreatedAt,
	 * Getter for the date of creation.
	 * @return date of creation
	 */
	
	public Date getDate() {
		return this.date;
	}
	
	@Override
	public String toString() {
		return this.user + " - " + this.content;
	}
	
	
	
}
