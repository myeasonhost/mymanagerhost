package com.ds.money.page;
/*
 *    Copyright 2009-2012 The MyBatis Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlEnumValue;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class EnumIntegerTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private Class<E> type;
    private final E[] enums;
    private final int[] values;

    public EnumIntegerTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
        this.values = new int[this.enums.length];
        for (int i = 0; i < enums.length; i++) {
            E e = enums[i];
            try {
                Field field = type.getField(e.name());
                if (field.isAnnotationPresent(XmlEnumValue.class)) {
                    XmlEnumValue v = field.getAnnotation(XmlEnumValue.class);
                    if (v != null) {
                        values[i] = Integer.parseInt(v.value());
                    }
                }
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, values[parameter.ordinal()]);
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int columnValue = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            for (int i = 0; i < values.length; i++) {
                if (columnValue == values[i]) {
                    return enums[i];
                }
            }
            throw new IllegalArgumentException("Cannot convert " + columnValue + " to " + type.getSimpleName() + " by value.");
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int columnValue = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            for (int i = 0; i < values.length; i++) {
                if (columnValue == values[i]) {
                    return enums[i];
                }
            }
            throw new IllegalArgumentException("Cannot convert " + columnValue + " to " + type.getSimpleName() + " by value.");
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int columnValue = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            for (int i = 0; i < values.length; i++) {
                if (columnValue == values[i]) {
                    return enums[i];
                }
            }
            throw new IllegalArgumentException("Cannot convert " + columnValue + " to " + type.getSimpleName() + " by value.");
        }
    }
}
