package engenhariaSoftware.common;

import java.util.Date;
	/**
	 * @author Frederico
	 * @author Sara
	 * @author Rita
	 * @author Filipe
	 * @since November,2018
	 * 
	 *  The Facebook class access to academic information made available in Facebook.
	 *      
	 *
	 */
public class PostFacebook {
	
	private String user;
	private String text;
	private String id;
	private Date createdAt;
	
	/**
	 * Facebook constructor ,
	 * initialization user, text, id, createAt.
	 * @param user this is the username 
	 * @param text this is what the username wrote
	 * @param id this is the username id
	 * @param createdAt this is the date of the text
	 */
	
	public PostFacebook(String user, String text, String id, Date createdAt) {
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
		return " Facebook: " + this.user + ": " + this.createdAt;
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
