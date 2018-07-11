package com.sawelly.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 数据合法性验证类 .<p>
 * 
 */

public class ValidUtil {

	/**
	 * 判断是否为合法的ip.<p>
	 * 
	 * @param ip
	 * @return NONE
	 */
	public static boolean isLicitIp(String ip) {
		if (!StringUtil.isFine(ip)) {
			return false;
		}
		String regex = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(ip);
		if (!m.find()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否为email.<p>
	 * 
	 * @param email
	 * @return NONE
	 */
	public static boolean isEmail(String email) {
		if (StringUtil.isFine(email) && email.indexOf("@") > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
