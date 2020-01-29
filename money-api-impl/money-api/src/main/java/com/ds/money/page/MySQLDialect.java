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
public class MySQLDialect extends Dialect {

    @Override
    public String getLimitString(String query, int offset, int limit) {
        query = query.trim();
        StringBuilder pagingSelect = new StringBuilder(query.length() + 128);
        pagingSelect.append(query);
        pagingSelect.append(" limit ?, ?");
        return pagingSelect.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void registerLimitParameters(Configuration configuration, List<ParameterMapping> parameterMappings, Map paginationMapperParamMap, RowBounds rowBounds) {
        parameterMappings.add(new ParameterMapping.Builder(configuration, "_pagination._offset", Integer.class).build());
        parameterMappings.add(new ParameterMapping.Builder(configuration, "_pagination._limit", Integer.class).build());
        paginationMapperParamMap.put("_limit", rowBounds.getLimit());
        paginationMapperParamMap.put("_offset", rowBounds.getOffset());
    }
}
