package io.stayhungrystayfoolish.desgin;

import com.sun.scenario.effect.impl.prism.PrImage;

import java.sql.Connection;

/**
 * @Author: bonismo@hotmail.com
 * @Description:
 * @Date: 2019-08-21 22:59
 * @Version: V1.0
 */
public enum DataSourceSingleton {

    DATA_SOURCE_SINGLETON;

    private DataConnection dataConnection = null;

    private DataSourceSingleton() {
        this.dataConnection = new DataConnection();
    }

    public DataConnection getDataConnection() {
        return dataConnection;
    }
}
