package com.sawelly.fpog.interceptor;

import com.sawelly.fpog.common.context.ProjectContext;
import com.sawelly.fpog.common.context.ProjectContextUtils;
import com.sawelly.fpog.entity.BaseCommonEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {
        MappedStatement.class, Object.class})})
public class MybatisAppInterceptor implements Interceptor {
    private Logger log = LoggerFactory.getLogger(MybatisAppInterceptor.class);

    /**
     * @Description : 方法描述
     * @Method_Name : intercept
     * @Update Date :
     * @Update Author : leonlau
     */

    public Object intercept(Invocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        Object parameter = invocation.getArgs()[1];

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        if ("update".equals(methodName) && !SqlCommandType.DELETE.equals(mappedStatement.getSqlCommandType())) { // mybatis 操作，无论是insert update
            // 都对应Executor update方法

            // 解决批量插入或更新
            if (parameter instanceof Map) {
                Map<String, Object> parameterList = (Map) parameter;
                Iterator it = parameterList.keySet().iterator();
                while (it.hasNext()) {
                    String key = (String) it.next();

                    if (parameterList.get(key) instanceof List) {
                        List<Object> entitys = (List) parameterList.get(key);
                        for (Object obj : entitys) {
                            updateFields(obj, mappedStatement);
                        }
                    } else {
                        updateFields(parameterList.get(key), mappedStatement);
                    }

                }
            } else {
                updateFields(parameter, mappedStatement);
            }
        }

        return invocation.proceed();
    }

    private void updateFields(Object parameter, MappedStatement mappedStatement) {
        if (parameter instanceof BaseCommonEntity) {
            ProjectContext sc = ProjectContextUtils.getProjectContext();
            BaseCommonEntity baseCommonEntity = (BaseCommonEntity) parameter;
            if (SqlCommandType.INSERT.equals(mappedStatement
                    .getSqlCommandType())) {
                // 设置插入时间
                baseCommonEntity.setCreateDate(new Date());

                if (null != sc) {
                    if (null != sc.getUserId()) {
                        baseCommonEntity.setCreatorId(sc.getUserId());
                    }
                }

            } else if (SqlCommandType.UPDATE.equals(mappedStatement
                    .getSqlCommandType())) {
                // 更新更新时间
                baseCommonEntity.setModifyDate(new Date());
                if (null != sc && null != sc.getUserId()) {
                    baseCommonEntity.setModifierId(sc.getUserId());
                }
            }

        }
    }

    private Properties properties;


    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
