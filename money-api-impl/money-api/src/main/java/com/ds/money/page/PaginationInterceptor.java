/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ds.money.page;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;

/**
 *
 * @author Gkh
 */
@Intercepts({
    @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PaginationInterceptor implements Interceptor {

    //protected static final Logger logger = Logger.getLogger(PaginationInterceptor.class.getName());
    protected static final Logger logger =null;

    @SuppressWarnings("unchecked")
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }

        Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
        Dialect dialect = null;
        Dialect.Type databaseType = null;
        try {
            databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
        } catch (Exception e) {
        }
        if (databaseType != null) {
            switch (databaseType) {
                case ORACLE:
                    dialect = new OracleDialect();
                    break;
                case MYSQL:
                    dialect = new MySQLDialect();
                    break;
            }
        }
        if (dialect == null) {
            return invocation.proceed();
        }


        BoundSql originalBoundSql = statementHandler.getBoundSql();
        String originalSql = originalBoundSql.getSql();
        String paginationSql = dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit());
        if (logger.isDebugEnabled()) {
            logger.debug("pagination: " + paginationSql);
        }

        List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>(originalBoundSql.getParameterMappings());
        Object parameterObject = originalBoundSql.getParameterObject();
        BoundSql boundSql = new BoundSql(configuration, paginationSql, parameterMappings, parameterObject);
        Map<String, Object> additionalParameters = (Map<String, Object>) metaStatementHandler.getValue("delegate.boundSql.additionalParameters");
        for (Map.Entry<String, Object> entry : additionalParameters.entrySet()) {
            boundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
        }

        HashMap paginationMapperParamMap = new HashMap();
        boundSql.setAdditionalParameter("_pagination", paginationMapperParamMap);
        if (logger.isTraceEnabled()) {
            logger.trace("pagination call dialect.registerLimitParameters");
        }
        dialect.registerLimitParameters(configuration, parameterMappings, paginationMapperParamMap, rowBounds);

        metaStatementHandler.setValue("delegate.boundSql", boundSql);

        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        ParameterHandler parameterHandler = configuration.newParameterHandler(mappedStatement, parameterObject, boundSql);
        metaStatementHandler.setValue("delegate.parameterHandler", parameterHandler);

        metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
        metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);

        if (logger.isTraceEnabled()) {
            logger.trace("pagination interceptor done");
        }

        return invocation.proceed();
    }


    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }


    public void setProperties(Properties arg0) {
    }
}
