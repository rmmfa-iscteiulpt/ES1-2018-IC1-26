package JUnit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import engenhariaSoftware.common.Email;
import junit.framework.Assert;

public class EmailTests {

	@Test
	void testContent() {
		Email test = new Email ("Sara","ISCTE",73501, new Date(0));
		String output = test.getContent();
		assertEquals("ISCTE", output);
		
	}

	@Test
	void testUserName() {
		Email test1 = new Email ("Sara","ISCTE",73501, new Date(0));
		String output = test1.getUserName();
		assertEquals("Sara", output);
		
	}

	@Test
	public void testId() {
		Email test2 = new Email ("Sara","ISCTE",73501, new Date(0));
		long output = test2.getId();
		assertEquals(73501, output);
	}

}