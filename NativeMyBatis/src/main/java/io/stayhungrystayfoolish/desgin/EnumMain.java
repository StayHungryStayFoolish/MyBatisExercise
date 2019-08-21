package io.stayhungrystayfoolish.desgin;

/**
 * @Author: bonismo@hotmail.com
 * @Description: 枚举单例测试
 * @Date: 2019-08-21 23:02
 * @Version: V1.0
 */
public class EnumMain {

    public static void main(String[] args) {
        DataConnection dataConnection1 = DataSourceSingleton.DATA_SOURCE_SINGLETON.getDataConnection();
        DataConnection dataConnection2 = DataSourceSingleton.DATA_SOURCE_SINGLETON.getDataConnection();
        System.out.println(dataConnection1.hashCode());
        System.out.println(dataConnection2.hashCode());
        System.out.println(dataConnection1 == dataConnection2);
    }
}
