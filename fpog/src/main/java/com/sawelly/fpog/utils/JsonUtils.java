/**
 *  @Project       : oel_DemonstrationTraining

 *  @Program Name  : cn.com.teacher.cistus.dt.util.JsonUtils.java
 *  @Class Name    : JsonUtils
 *  @Description   : 类描述
 *  @Author        : leonlau
 *  @Creation Date : 2013-6-24 下午1:51:06 
 *  @ModificationHistory  
 */

package com.sawelly.fpog.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * 基于fastjson
 * 
 * @Project : oel_DemonstrationTraining
 * @Program Name : cn.com.teacher.cistus.dt.util.JsonUtils.java
 * @Class Name : JsonUtils
 * @Description : 类描述
 * @Author : leonlau
 * @Creation Date : 2013-6-24 下午2:01:02
 * @ModificationHistory
 */
public class JsonUtils {
	private static final SerializeConfig config;
	static {
		config = new SerializeConfig();
//		config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
//		config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
	}
	private static final SerializerFeature[] features = {
			SerializerFeature.WriteMapNullValue, // 输出空置字段
			SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
			SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
			SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
	};

	
	
	/** 
	 * 	json转为object
	 *  @Description    : 方法描述
	 *  @Method_Name    : toBean
	 *  @param json
	 *  @param clazz
	 *  @return 
	 *  @Update Date    : 
	 *  @Update Author  : leonlau
	 */
	public static <T> T toBean(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}
	/** 
	 * 	json转对象list
	 *  @Description    : 方法描述
	 *  @Method_Name    : toBean
	 *  @param json
	 *  @param clazz
	 *  @return 
	 *  @Update Date    : 
	 *  @Update Author  : leonlau
	 */
	public static <T> List<T> toList(String json, Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}
	/** 
	 * 	json转对象map
	 *  @Description    : 方法描述
	 *  @Method_Name    : toBean
	 *  @param json
	 *  @param clazz
	 *  @return 
	 *  @Update Date    : 
	 *  @Update Author  : leonlau
	 */
	public static <K, V> Map<K, V> toMap(String json) {
		return JSON.parseObject(json, new TypeReference<Map<K, V>>() {
		});
	}
	
	/** 
	 * json转array
	 * @param json
	 * @return JSONArray
	 */
	public static JSONArray toArray(String json) {
		return JSON.parseArray(json);
	}
	
	/** 
	 *  object 转为json
	 *  @Description    : 方法描述
	 *  @Method_Name    : toJson
	 *  @param obj
	 *  @return 
	 *  @Update Author  : leonlau
	 */
	public static String objToJson(Object obj) {
		return JSON.toJSONString(obj, config, features);
	}
	
	/** 
	 * List转json
	 * @param list
	 * @return json
	 */
	public static String listToJson(List<?> list){
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int entryIndex = 0;
		for(Object obj:list){
			if(entryIndex!=0){
				builder.append(",");
			}
			builder.append(objToJson(obj));
			entryIndex++;
		}
		builder.append("]");
		return builder.toString();
	}

	/** 
	 * array转json
	 * @param arr
	 * @return json
	 */
	public static String arrayToJson(String[] arr) {
		return JSON.toJSONString(arr);
	}
	
	/** 
	 * map转json
	 * @param info
	 * @return json
	 */
	public static String mapToJson(Map<Object, Object> info) {
		return JSON.toJSONString(info);
	}
}
