/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ds.money.page;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

/**
 *
 * @author Gkh
 */
public abstract class Dialect {

    public static enum Type {

        ORACLE,
        MYSQL
    }

    public abstract String getLimitString(String query, int offset, int limit);

    public abstract void registerLimitParameters(Configuration configuration, List<ParameterMapping> parameterMappings, Map paginationMapperParamMap, RowBounds rowBounds);
}
