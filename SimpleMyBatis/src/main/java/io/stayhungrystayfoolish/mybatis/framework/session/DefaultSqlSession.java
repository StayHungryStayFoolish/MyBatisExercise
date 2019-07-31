package io.stayhungrystayfoolish.mybatis.framework.session;

import io.stayhungrystayfoolish.mybatis.framework.configuration.Configuration;
import io.stayhungrystayfoolish.mybatis.framework.configuration.MappedStatement;

import java.util.List;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 7:20 PM
 * @Description:
 * @Version: 1.0
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T insert(String statementId, Object param) {
        return null;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> list = selectList(statementId, param);
        if (list != null && list.size() == 1) {
            return (T) list.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        // 真正和数据库进行 CRUD 操作的类
        // 使用执行器去执行 statement
        Executor executor = new SimpleExecutor();
        // 根据 statementId 获取 MappedStatement
        MappedStatement statement = configuration.getMappedStatements().get(statementId);
        return executor.query(configuration, statement, param);
    }
}
