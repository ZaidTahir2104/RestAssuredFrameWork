package com.personapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	public static String personFName() {
		String generateString = RandomStringUtils.randomAlphabetic(1);
		return ("Zaid"+generateString);
	}
	public static String personLName() {
		String generateString = RandomStringUtils.randomAlphabetic(1);
		return ("Tahir"+generateString);
	}
	public static String personAge() {
		String generateAge = RandomStringUtils.randomNumeric(2);
		return (generateAge);
	}
	public static String personAddress() {
		String generateAddress = RandomStringUtils.randomAlphabetic(1);
		return ("58-"+generateAddress+"Gulshan e Lahore");
	}
	public static String personPhoneNumber() {
		String generatePhoneNumber = RandomStringUtils.randomNumeric(3);
		return ("123-456-"+generatePhoneNumber);
	}
//	public static String personID() {
//		String generateID = RandomStringUtils.randomNumeric(3);
//		return (generateID);
//	}

}
