package engenhariaSoftware.common;

public class Tweet {

	private String user;
	private String text;
	private long id;
	
	public Tweet(String user, String text, long id) {
		this.user = user;
		this.text = text;
		this.id = id;
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
		return this.user + this.id;
	}
	
	@Override
	public String toString() {
		return user + " -- " + text;
	}
	
	
	
}