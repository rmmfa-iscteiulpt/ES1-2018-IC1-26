package jUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import engenhariaSoftware.common.TweetPostEmail;

class TweetPostEmailTests {


		@Test
		void testType() {
			TweetPostEmail testtype = new TweetPostEmail ("Twitter","Sara","ISCTTE", "0" ,new Date(0));
			String result = testtype.getType();
			assertEquals("Twitter", result);
			
		}

		@Test
		void testUser() {
			TweetPostEmail testuser = new TweetPostEmail ("Twitter","Sara","ISCTTE", "0" ,new Date(0));
			String result = testuser.getUserName();
			assertEquals("Sara", result);
			
		}

		@Test
		public void testText() {
			TweetPostEmail testtext = new TweetPostEmail ("Twitter","Sara","ISCTE", "0" ,new Date(0));
			String result = testtext.getText();
			assertEquals("ISCTE", result);
			
		}
		
		@Test
		public void testId() {
			TweetPostEmail testid = new TweetPostEmail ("Twitter","Sara","ISCTTE", "0" ,new Date(0));
			String result = testid.getId();
			assertEquals("0", result);
		}
		
		@Test
		public void testDate() throws ParseException {
			String s = "07-12-2018";
			Date d = new SimpleDateFormat("dd-MM-YYYY").parse(s);
			TweetPostEmail testdata = new TweetPostEmail ("Twitter","Sara","ISCTTE", "0" , d);
			Date output = testdata.getCreatedAt();
			assertEquals(d,output);
			
		
		
		}
		
		@Test
		public void testPostHeader() throws ParseException {
			String s = "07-12-2018";
			Date d = new SimpleDateFormat("dd-MM-YYYY").parse(s);
			TweetPostEmail testpost = new TweetPostEmail ("Twitter","Sara","ISCTTE", "0" , d);
			String result = testpost.getType() + ": " + testpost.getUserName() + ": " + testpost.getCreatedAt();
			assertEquals("Twitter: Sara: " + d, result);
		}
		
		@Test
		public void testString() {
		TweetPostEmail teststring = new TweetPostEmail ("Twitter","Sara","ISCTE", "0" , new Date(0));
		String result = teststring.getUserName() + " - " + teststring.getText();
		assertEquals("Sara - ISCTE" , result);
		
		}
		
		
		
	}

		
		
