package com.sawelly.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProjectIdSqlParser {
	private static Log log = LogFactory.getLog(ProjectIdSqlParser.class);

	public static String appendProjectInfo(String sql, Integer projectId, String id) {
		if (projectId == null) {
			return sql;
		}
		sql = sql.replaceAll("\\s{1,}", " ").trim().toUpperCase();
		// sql = sql.replaceAll("\\n", "").trim().toUpperCase();
		Integer whereIndex = sql.indexOf("WHERE");
		String[] sqlArray = sql.split(" ");
		StringBuffer result = new StringBuffer();

		if (!isSubSelect(sqlArray)) {
			if (whereIndex != -1) {
				conditionSqlParse(sqlArray, result, projectId);
			} else {
				sqlParse(sqlArray, result, projectId);
			}
		} else {
			Integer count = whereCount(sqlArray);
			if (count == 2) {
				conditionSqlParse(sqlArray, result, projectId);
			} else {
				log.error(id + " : #500: 暂不支持此SQL PROJECTID 的添加,请自行添加");
				return sql;
			}

		}

		return result.toString();
	}

	private static void conditionSqlParse(String[] sqlArray, StringBuffer result, Integer projectId) {
		boolean afterFrom = false;
		Integer index = 0;
		List<String> tableName = new ArrayList<String>();
		Integer tableNameIndex = 0;
		for (String s : sqlArray) {
			if (s.indexOf("FROM") != -1) {
				afterFrom = true;
			}
			if ("AS".equals(s) && afterFrom) {
				tableName.add(sqlArray[index + 1].trim());
				tableNameIndex++;
			}
			if (s.indexOf("WHERE") != -1) {
				if (tableNameIndex > 0 && !"".equals(tableName.get(tableNameIndex - 1))) {
					result.append(" WHERE ");
					result.append(tableName.get(tableNameIndex - 1));
					result.append(".");
					result.append("PROJECT_ID=");
					result.append(projectId);
					result.append(" AND ");
					tableNameIndex--;
				} else {
					result.append(" WHERE PROJECT_ID=");
					result.append(projectId);
					result.append(" AND ");
				}
			} else {
				result.append(" ");
				result.append(s);
				result.append(" ");
			}
			index++;
		}
	}

	private static void sqlParse(String[] sqlArray, StringBuffer result, Integer projectId) {
		boolean afterFrom = false;
		boolean canDo = false;
		Integer whereIndex = null;
		Integer index = 0;
		List<String> tableName = new ArrayList<String>();
		Integer tableNameIndex = 0;
		for (String s : sqlArray) {
			if (s.indexOf("FROM") != -1) {
				afterFrom = true;
				afterFrom = true;
			}
			if ("AS".equals(s) && afterFrom) {
				tableName.add(sqlArray[index + 1].trim());
				tableNameIndex++;
			}
			result.append(" ");
			result.append(s);
			result.append(" ");
			if (canDo || (whereIndex != null && whereIndex == index)) {
				if (tableNameIndex > 0 && !"".equals(tableName.get(tableNameIndex - 1))) {
					result.append(" WHERE ");
					result.append(tableName.get(tableNameIndex - 1));
					result.append(".");
					result.append("PROJECT_ID=");
					result.append(projectId);
					result.append(" ");
					tableNameIndex--;
				} else {
					result.append(" WHERE PROJECT_ID=");
					result.append(projectId);
					result.append(" ");
				}
			}
			if (s.equals("FROM") && (index + 2 >= sqlArray.length || sqlArray[index + 2].indexOf("AS") == -1)) {
				canDo = true;
			} else if (s.equals("FROM") && (index + 2 < sqlArray.length && sqlArray[index + 2].indexOf("AS") != -1)) {
				whereIndex = index + 3;
			} else {
				canDo = false;
			}
			index++;
		}
	}

	private static boolean isSubSelect(String[] sqlArray) {
		Integer index = 0;
		for (String s : sqlArray) {
			if (s.indexOf("SELECT") != -1) {
				index++;
			}
		}
		return index == 2 ? true : false;
	}

	private static Integer whereCount(String[] sqlArray) {
		Integer index = 0;
		for (String s : sqlArray) {
			if (s.indexOf("WHERE") != -1) {
				index++;
			}
		}
		return index;
	}
}
