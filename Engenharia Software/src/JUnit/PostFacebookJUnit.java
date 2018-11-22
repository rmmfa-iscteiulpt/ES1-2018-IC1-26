package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import engenhariaSoftware.common.PostFacebook;

class PostFacebookJUnit {

	@Test
	void test() {
		PostFacebook test=new PostFacebook("Filhipe","ola","78011",new Date());
		String output=test.getUserName();
		assertEquals("Filhipe",output);
	}
	
	@Test
	void test1() {
		PostFacebook test1=new PostFacebook("Filhipe","ola","78011",new Date());
		String output=test1.getText();
		assertEquals("ola",output);
	}
	
	@Test
	void test2() {
		PostFacebook test2=new PostFacebook("Filhipe","ola","78011",new Date());
		String output=test2.getId();
		assertEquals("78011",output);
	}

}
