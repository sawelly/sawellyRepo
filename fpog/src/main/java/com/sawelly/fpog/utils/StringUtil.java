package com.sawelly.utils;

import java.util.Arrays;
import java.util.Random;

public class StringUtil {
	
	/**
	 * str不是null返回true
	 * @param str
	 * @return
	 */
	public static boolean isFine(String str) {
		return str != null && str.length() > 0;
	}	
	/**
	 * s中的s1替换成s2
	 * @param s
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static final String replace(String s, String s1, String s2)
	{
		if (s == null)
			return null;
		int i = 0;
		if ((i = s.indexOf(s1, i)) >= 0)
		{
			char ac[] = s.toCharArray();
			char ac1[] = s2.toCharArray();
			int j = s1.length();
			StringBuffer sb = new StringBuffer(ac.length);
			sb.append(ac, 0, i).append(ac1);
			i += j;
			int k;
			for (k = i; (i = s.indexOf(s1, i)) > 0; k = i)
			{
				sb.append(ac, k, i - k).append(ac1);
				i += j;
			}
			sb.append(ac, k, ac.length - k);
			return sb.toString();
		}
		else
		{
			return s;
		}
	}
	/**
	 * 获得一个随机的字符串
	 * @param len
	 * @return
	 */
	public static String getRandomString(int len) {
		StringBuilder buf = new StringBuilder(len + 1);
		String str = "0123456789QWERTYUIOPASDFGHJKLZXCVBNM" ;
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			buf.append( str.charAt( random.nextInt(str.length() )));
		}
		return buf.toString();
	}	
	/**
	 * 最大连续数
	 * @param x
	 * @return
	 */
    public static int maxContinueNum(int[] x){
		Arrays.sort(x);
		int maxc=0;
		int count=0;
		for(int i=0; i<x.length-1; i++){
			if(x[i+1]-1==x[i]){
				++count;
				if(count>maxc){
					maxc=count;
				}
			}
			else{
				count=0;
			}
		}
		return maxc+1;
    }
    
    /**
	 * 在数字前补零
	 * 
	 * @param num
	 *            数字
	 * @param length
	 *            输出位数
	 */
	public static String addzero(int num, int length)
	{
		String str = "";
		if (num < Math.pow(10, length - 1))
		{
			for (int i = 0; i < (length - (num + "").length()); i++)
			{
				str += "0";
			}
		}
		str = str + num;
		return str;
	}

}
