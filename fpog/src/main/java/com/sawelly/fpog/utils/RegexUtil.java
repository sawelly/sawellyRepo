package com.sawelly.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 正则表达式
 * @author Administrator
 *
 */
public class RegexUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String value = "s234awelly@12SFAFDA6.com safa@12.com.cn";
		System.out.println(checkEmail(value));
	}
	/**
	* 检查 email输入是否正确
	* 正确的书写格 式为sawelly@126.com/safa-fas@fsa.fea.com/fsafd@fsa.com.cn……
	* @param value要检验的字符串
	* @return num检索到的个数
	*/

	public static int checkEmail(String value) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p=Pattern.compile(regex);
    	Matcher m=p.matcher(value);
    	int num=0;
    	while(m.find()){
    		num++;
    	}
		return num;
	}

}
