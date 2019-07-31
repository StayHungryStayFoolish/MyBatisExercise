package io.stayhungrystayfoolish.mybatis.framework.session;

import io.stayhungrystayfoolish.mybatis.framework.configuration.DatasourceConfiguration;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 3:24 PM
 * @Description: 具体构建 SqlSessionFactory
 * @Version: 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private DatasourceConfiguration datasourceConfiguration;

    public DefaultSqlSessionFactory(DatasourceConfiguration datasourceConfiguration) {
        this.datasourceConfiguration = datasourceConfiguration;
    }

    @Override
    public SqlSession openSession() {
        return null;
    }
}
