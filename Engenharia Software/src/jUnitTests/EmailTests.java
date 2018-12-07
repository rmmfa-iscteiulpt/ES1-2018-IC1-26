package jUnitTests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import engenhariaSoftware.common.Email;

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
	
	@Test
	public void testDate() throws ParseException {
		String s = "07-12-2018";
		Date d = new SimpleDateFormat("dd-MM-YYYY").parse(s);
		Email testdate = new Email ("Sara","ISCTE",73501,d);
		Date output = testdate.getDate();
		assertEquals(d,output);
		
	}
	
	@Test
	public void testMailHeader() throws ParseException {
		String s = "07-12-2018";
		Date d = new SimpleDateFormat("dd-MM-YYYY").parse(s);
		Email testheader = new Email ("Sara","ISCTE",73501,d);
		String output = "Outlook: " + testheader.getUserName() + ": " + testheader.getDate();
		assertEquals("Outlook: Sara: " + d, output);		
	}
	
	@Test
	public void testString() {
		Email teststring = new Email ("Sara","ISCTE",73501,new Date(0));;
		String output = teststring.getUserName() + "-" + teststring.getContent() ;
		assertEquals("Sara-ISCTE", output);		

	}
	
		
	}

