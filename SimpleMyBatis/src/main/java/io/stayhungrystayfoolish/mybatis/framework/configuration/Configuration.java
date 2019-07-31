package io.stayhungrystayfoolish.mybatis.framework.configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 2:30 PM
 * @Description: 全局配置及数据库信息
 * @Version: 1.0
 */
public class Configuration {

    private DataSource dataSource;

    /**
     * 存储 statementId 和 解析的 MappedStatement 集合
     */
    private Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
        this.mappedStatements.put(statementId, mappedStatement);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }
}
