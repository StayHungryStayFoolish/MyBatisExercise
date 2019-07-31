package io.stayhungrystayfoolish.mybatis.framework.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 5:01 PM
 * @Description: mapper 文件 Sql 语句解析出来的 sql 语句及参数集合
 * @Version: 1.0
 */
public class BoundSql {

    /**
     * 解析之后的SQL语句
     */
    private String sql;

    /**
     * 解析出来的参数集合
     */
    private List<ParameterMapping> parameterMappings = new ArrayList<>();


    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public void addParameterMapping(ParameterMapping parameterMapping) {
        this.parameterMappings.add(parameterMapping);
    }


    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
