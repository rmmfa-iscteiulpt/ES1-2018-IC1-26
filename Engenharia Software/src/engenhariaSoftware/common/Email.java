package engenhariaSoftware.common;

import java.util.Date;

	/**
	 *@author Frederico
	 * @author Sara
	 * @author Rita
	 * @author Filipe
	 * @since December,2018
	 * 
	 *  The Email class is the class access to email information.   
	 *
	 */

public class Email {

	private String user;
	private String content;
	private long id;
	private Date date;
	
	/**
	 * Email constructor,
	 * initialization user, content, id, date.
	 * 
	 * @param user this is the email of the user
	 * @param content is the content of the email
	 * @param id is the id of the email
	 * @param date this is the date of the email
	 */
	
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
	 * getContent,
	 * Getter for the content of the email.
	 * @return the content
	 */
	
	public String getContent() {
		return this.content;
	}
	
	/**
	 * getId,
	 * Getter for the email id.
	 * @return id
	 */
	
	public long getId() {
		return this.id;
	}
	
	/**
	 * mailHeader,
	 * Getter for the email header.
	 * @return user and date of creation
	 */
	
	public String mailHeader () {
		return "Outlook: " + this.user + ": " + this.date;
	}
	
	/**
	 * getDate,
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
