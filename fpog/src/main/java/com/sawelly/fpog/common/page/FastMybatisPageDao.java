package com.sawelly.fpog.common.page;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;
import org.apache.ibatis.transaction.managed.ManagedTransaction;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FastMybatisPageDao extends MybatisPageDao {

	private Logger log = LoggerFactory.getLogger(FastMybatisPageDao.class);

	public static final String COUNT_ID = "_count";

	private HashMap<String, String> map_statement = new HashMap<String, String>();

	protected void initDao() throws Exception {
		super.initDao();
		SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate) getSqlSession();
		Configuration configuration = sqlSessionTemplate.getConfiguration();
		Collection<String> statements = configuration.getMappedStatementNames();
		for (Iterator<String> iter = statements.iterator(); iter.hasNext();) {
			String element = iter.next();
			map_statement.put(element, element);
		}
	}

	public Page query(Object parameterObj, int start, int limit, String statementName) {
		// 不支持其他数据库,只支持mysql
		int pageSize = limit;
		if (pageSize < 1) {
			pageSize = getInitPageSize();
		}
		SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate) getSqlSession();
		Configuration configuration = sqlSessionTemplate.getConfiguration();
		MappedStatement mappedStatement = configuration.getMappedStatement(statementName);

		BoundSql boundSql = mappedStatement.getBoundSql(parameterObj);
		String sql = boundSql.getSql();
		String countSql = null;
		if (sql != null) {
			countSql = getsql(sql);
		}
		Connection connection = null;
		List data = null;
		int current = 1;
		int pages = 0;
		int totalSize = 0;
		try {
			// 获取连接
			connection = sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
			if (map_statement.containsKey(statementName + COUNT_ID)) {
				// 优先查找能统计条数的sql
				Object count_obj = sqlSessionTemplate.selectOne(statementName + COUNT_ID, parameterObj);
				if (count_obj != null) {
					totalSize = Integer.parseInt(count_obj.toString());
				}
			} else {
				totalSize = getTotalSize(parameterObj, configuration, mappedStatement, boundSql, countSql, connection,
						totalSize);
				/*
				 * ManagedTransaction transaction = new
				 * ManagedTransaction(connection, false); SimpleExecutor exe =
				 * new SimpleExecutor(configuration, transaction); RowBounds
				 * no_rowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET,
				 * RowBounds.NO_ROW_LIMIT); StaticSqlSource sqlsource = new
				 * StaticSqlSource(configuration, countSql, boundSql
				 * .getParameterMappings()); MappedStatement.Builder builder =
				 * new MappedStatement.Builder(configuration, "id_temp_count",
				 * sqlsource, SqlCommandType.SELECT); List<ResultMapping>
				 * resultMappings=new ArrayList<ResultMapping>(1);
				 * ResultMapping.Builder mmmm=new
				 * ResultMapping.Builder(configuration, "ITEMS_COUNT",
				 * "ITEMS_COUNT", new IntegerTypeHandler());
				 * resultMappings.add(mmmm.build()); List<ResultMap>
				 * resultMaps=new ArrayList<ResultMap>(1); ResultMap.Builder
				 * fff=new ResultMap.Builder(configuration, "_temp_res_map",
				 * int.class, resultMappings, true);
				 * resultMaps.add(fff.build()); builder.resultMaps(resultMaps);
				 * MappedStatement query_statement = builder.build(); // 查询总行数
				 * data = exe.query(query_statement,
				 * wrapCollection(parameterObj), no_rowBounds,
				 * Executor.NO_RESULT_HANDLER); if (data.size() > 0) { if
				 * (data.get(0) != null) { totalSize =
				 * Integer.parseInt(data.get(0).toString()); } }
				 */
			}
			log.debug("符合查询条件的结果集(totalSize)有" + totalSize + "行.");
			pages = totalSize / pageSize + (totalSize % pageSize > 0 ? 1 : 0);
			current = start;
			if (current < 1) {
				current = 1;
			}
			if ((current > pages) && (pages > 0)) {
				current = pages;
			}
			
			//for mysql
			StringBuilder str_sql = new StringBuilder(sql);
			str_sql.append(" limit ");
			str_sql.append(pageSize * (current - 1));
			str_sql.append(" , ");
			str_sql.append(pageSize);
			
			//for oracle
			//String str_sql =getLimitString(sql, current, pageSize);
			
			ManagedTransaction transaction = new ManagedTransaction(connection, false);
			
			configuration.newExecutor(transaction, ExecutorType.SIMPLE);
			SimpleExecutor exe = new SimpleExecutor(configuration, transaction);
			RowBounds no_rowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
			// 查询结果集
			StaticSqlSource sqlsource = new StaticSqlSource(configuration, str_sql.toString(), boundSql
					.getParameterMappings());
			MappedStatement.Builder builder = new MappedStatement.Builder(configuration, "id_temp_result", sqlsource,
					SqlCommandType.SELECT);
			builder.resultMaps(mappedStatement.getResultMaps()).resultSetType(mappedStatement.getResultSetType())
					.statementType(mappedStatement.getStatementType());
			MappedStatement query_statement = builder.build();

			List<ResultMap> resultMap = mappedStatement.getResultMaps();
			if (resultMap.size() > 0) {
				BeanResultHandler result_handler = new BeanResultHandler(resultMap.get(0).getType());
				exe.query(query_statement, wrapCollection(parameterObj), no_rowBounds, result_handler);
				data = result_handler.getResultList();
			} else {
				DefaultResultHandler default_handler = new DefaultResultHandler();
				exe.query(query_statement, wrapCollection(parameterObj), no_rowBounds, default_handler);
				data = default_handler.getResultList();
			}

		} catch (Exception e) {
			log.error("分页查询失败!" + countSql, e);
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error("关闭连接失败!", e);
				}
			}
		}
		if ((data == null) || (data.size() == 0)) {
			return new Page(current, pageSize, pages, data, totalSize);
		}
		return new Page(current, pageSize, pages, data, totalSize);
	}

	private class BeanResultHandler implements ResultHandler {

		private final List<Object> list;

		private Class<?> type = null;

		public BeanResultHandler(Class<?> type) {
			list = new ArrayList<Object>();
			this.type = type;
		}

		public void handleResult(ResultContext context) {
			Object obj = context.getResultObject();
			if (obj.getClass().getName().contains("$")) {
				String json_str = JSON.toJSONString(obj);
				list.add(JSON.parseObject(json_str, type));
			} else {
				list.add(obj);
			}

		}

		public List<Object> getResultList() {
			return list;
		}

	}

	private int getTotalSize(Object parameterObj, Configuration configuration,
			MappedStatement mappedStatement, BoundSql boundSql,
			String countSql, Connection connection, int totalSize)
			throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Exception ex = null;
		try {
			ParameterHandler handler = configuration.newParameterHandler(
					mappedStatement, parameterObj, boundSql);
			stmt = connection.prepareStatement(countSql);
			handler.setParameters(stmt);
			rs = stmt.executeQuery();
			if (rs.next()) {
				totalSize = rs.getInt(1);
			}
		} catch (Exception e) {
			ex = e;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		}
		if (ex != null) {
			throw ex;
		}
		return totalSize;
	}

	private Object wrapCollection(final Object object) {
		if (object instanceof List) {
			StrictMap<Object> map = new StrictMap<Object>();
			map.put("list", object);
			return map;
		} else if (object != null && object.getClass().isArray()) {
			StrictMap<Object> map = new StrictMap<Object>();
			map.put("array", object);
			return map;
		}
		return object;
	}

	protected String getsql(String sql) {
		sql = sql.replaceAll("\\s", " ").toLowerCase();
		Pattern p = Pattern.compile(" {2,}");
		Matcher m = p.matcher(sql.trim());
		sql = m.replaceAll(" ");

		int order_by = sql.lastIndexOf("order by");
		if (order_by > -1) {
			sql = sql.substring(0, order_by);
		}

		String[] paramsAndMethod = sql.split(" ");
		int count = 0;
		int index = 0;
		for (int i = 0; i < paramsAndMethod.length; i++) {
			if (paramsAndMethod[i].contains("select")) {
				count++;
			} else if (paramsAndMethod[i].contains("from")) {
				count--;
			}
			if (count == 0) {
				index = i;
				break;
			}
		}
		StringBuilder return_sql = new StringBuilder(
				"select count(1) as ITEMS_COUNT ");
		StringBuilder common_count = new StringBuilder();
		for (int j = index; j < paramsAndMethod.length; j++) {
			common_count.append(" ");
			common_count.append(paramsAndMethod[j]);
		}
		if (sql.contains("group by ")) {
			return_sql.append(" from ( ");
			return_sql.append(" select 1 ");
			return_sql.append(common_count);
			return_sql.append(" ) cistus_alias_count ");
			return return_sql.toString();
		}
		return return_sql.append(common_count).toString();
	}

	public String getLimitString(String sql, int offset, int limit) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);

		pagingSelect
				.append("select * from ( select row_.*, rownum rownum_ from ( ");

		pagingSelect.append(sql);

		pagingSelect.append(" ) row_ ) where rownum_ > " + ((offset-1)*limit)
				+ " and rownum_ <= " + (offset * limit));

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}

		return pagingSelect.toString();
	}

}
