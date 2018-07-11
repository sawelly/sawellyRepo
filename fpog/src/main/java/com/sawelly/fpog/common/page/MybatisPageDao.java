package com.sawelly.fpog.common.page;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * 通过获取定义的sql，额外添加分页sql并执行.
 * @author hedy
 *
 */
public class MybatisPageDao extends SqlSessionDaoSupport{
    private Logger log = LoggerFactory.getLogger(MybatisPageDao.class);


    private static final String DEFAULT_COUNT_SQL = "select 1 from dual";
	/**默认分页大小,仅当pageSize未设置时使用**/
	private int initPageSize = 15;
	public int getInitPageSize() {
		return initPageSize;
	}

	public void setInitPageSize(int initPageSize) {
		this.initPageSize = initPageSize;
	}
	
	/**
	 * Mybatis专用分页组件，调用query方法将自动在查询的sql前后添加分页sql，并返回封装好的Page对象。<br>
	 * 如果不想使用该组件，可以不调用该方法即可。
	 * @param map 查询体所需的参数
	 * @param start 
	 * @param limit
	 * @param statementName 配置文件中指定的查询体id.无需写分页语句，程序将自动添加。
	 * @return
	 * @throws Throwable
	 */
	public Page query(Object parameterObj, int start, int limit,String statementName) {
			int pageSize = limit;
			if(pageSize<1)
				pageSize = initPageSize;
			SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate) getSqlSession();
			Configuration configuration = sqlSessionTemplate.getConfiguration();
			MappedStatement mappedStatement = configuration.getMappedStatement(statementName);
			/**
			 * 获取绑定的sql
			 */
			BoundSql boundSql = mappedStatement.getBoundSql(parameterObj);
			String sql = boundSql.getSql();
			
			if(log.isDebugEnabled()){
				log.debug("获取需要执行的原始sql:\n"+sql);
			}
			
			//统计总数,需要设置别名，否则报错:
			//com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Every derived table must have its own alias
			String countSql = DEFAULT_COUNT_SQL;
			if(sql!=null){
				String upperSql = sql.replaceAll("\\s", " ").trim().toUpperCase();
				int groupByIndex = upperSql.indexOf(" GROUP ");
				int fromIndex = upperSql.indexOf("FROM");
				int bracketIndex = upperSql.indexOf("(");
				
				//from之前的参数可能存在子查询
				if(bracketIndex<fromIndex||groupByIndex!=-1){
					countSql = "select count(1) from("+sql+") cistus_alias_count";
				}else if(fromIndex!=-1){
					countSql = "select count(1) "+sql.substring(fromIndex);
				}
			}
			Connection connection = null;
			List<?> data = null;
			int current = 1;
			int pages = 0;
			int totalSize = 0;
			try{
				connection = sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
				PreparedStatement countStmt = connection.prepareStatement(countSql);
				BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),countSql,boundSql.getParameterMappings(),parameterObj);
				
				log.debug("\n统计总数sql--->\n"+countBS.getSql());
				
				setParameters(countStmt,mappedStatement,countBS,parameterObj);
				
				ResultSet rs = countStmt.executeQuery();
				
				if (rs.next()) {
					totalSize = rs.getInt(1);
				}
				rs.close();
				countStmt.close();
				
				log.debug("符合查询条件的结果集(totalSize)有"+totalSize+"行.");
				
				pages = totalSize / pageSize + ((totalSize % pageSize) > 0 ? 1 : 0);
				current = start;
				if (current < 1)
					current = 1;
				if (current > pages&&pages>0)
					current = pages;
				data =  sqlSessionTemplate.selectList(statementName, parameterObj, new RowBounds(pageSize * (current -1),pageSize));
			} catch (SQLException e) {
				log.error("分页查询失败!");
				e.printStackTrace();
			}finally{
				if(connection!=null)
					try {
						connection.close();
					} catch (SQLException e) {
						log.error("关闭连接失败!",e);
					}
			}
			
			if (data==null||data.size() == 0) {
				return new Page(current, pageSize, pages, data, totalSize);
			}
			return new Page(current, pageSize, pages, data, totalSize);
	}
	
	/**
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject)  {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());
					}
					try {
						typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
					} catch (SQLException e) {
						log.error("MybatisPageDao.setParameters()方法执行失败.\n"+e.getMessage());
					}
				}
			}
		}
	}
}

