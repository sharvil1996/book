package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
	static Pattern pattern;
	static Matcher matcher;
	public static boolean isEmpty(String param) {
		boolean isEmpty = false;
		if (param == "" || param.trim().length() <= 0) {
			isEmpty = true;
		}
		return isEmpty;
	}

	public static String getRandomString(int len) {
		Random myrandom = new Random();
		String temp = "";
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		List<Character> characters = new ArrayList<Character>();
		for (char c : alpha.toCharArray()) {
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(alpha.length());
		while (characters.size() != 0) {
			int randPicker = (int) (Math.random() * characters.size());
			output.append(characters.remove(randPicker));
		}
		alpha = output.toString();
		for (int i = 0; i < len; i++)
			temp += alpha.charAt(myrandom.nextInt(len)) + "";
		return temp;
	}
	
	public static boolean isValidEmailAddress(String email) {
		pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
		matcher = pattern.matcher(email);
		if (matcher.matches() && (email.endsWith(".com") || email.endsWith(".co.in") || email.endsWith(".in")))
			return true;
		return false;
	}
	
	public static boolean validateText(String param) {
		if (param.matches("[a-zA-Z]*")  )
			return false;
		return true;
	}
	
	public static boolean validateNumber(String param){
		boolean flag=false;
		if(!ValidationUtils.isEmpty(param)){
			int i;
			for(i=0;i<param.length();i++){
				 if (!Character.isDigit(param.charAt(i))){
					 break;
				 }
			}
			if(i==param.length()){
				flag=true;
			}
		}
		return flag;
	}
	

}
