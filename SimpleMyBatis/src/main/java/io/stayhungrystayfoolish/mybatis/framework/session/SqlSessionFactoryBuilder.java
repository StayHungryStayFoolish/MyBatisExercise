package io.stayhungrystayfoolish.mybatis.framework.session;

import io.stayhungrystayfoolish.mybatis.framework.configuration.DatasourceConfiguration;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 2:58 PM
 * @Description:
 * @Version: 1.0
 */
public class SqlSessionFactoryBuilder {

    private DatasourceConfiguration datasourceConfiguration;

    public SqlSessionFactoryBuilder() {
        this.datasourceConfiguration = new DatasourceConfiguration();
    }
}
