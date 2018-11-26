package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import engenhariaSoftware.common.Tweet;

class TweetJUnit {

	@Test
	void test() {
		Tweet test=new Tweet("Filhipe","ola", 78011,new Date());
		String output=test.getUserName();
		assertEquals("Filhipe", output);
	}
	
	@Test
	void test1() {
		Tweet test1=new Tweet("Filhipe","ola", 78011,new Date());
		String output=test1.getText();
		assertEquals("ola", output);
	}
	
	@Test
	void test2() {
		Tweet test1=new Tweet("Filhipe","ola", 78011,new Date());
		Long output=test1.getId();
		assertEquals("78011", output);
	}

}
