package com.techprime;

public class Utils {

	public static boolean isNullOrEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}

	public static String capitalizeFirstLetter(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
	}

}
