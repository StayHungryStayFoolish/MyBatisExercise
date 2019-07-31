package io.stayhungrystayfoolish.mybatis.framework.configuration;

import javax.sql.DataSource;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 2:30 PM
 * @Description:
 * @Version: 1.0
 */
public class DatasourceConfiguration {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
