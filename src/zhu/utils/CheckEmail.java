package zhu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckEmail {
	public static boolean isEmail(String email) {
	Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	Matcher matcher = emailPattern.matcher(email);
	if(matcher.find()){
	return true;
	}
	return false;
	}
}

