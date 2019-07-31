package io.stayhungrystayfoolish.mybatis.framework.session;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 2:54 PM
 * @Description:
 * @Version: 1.0
 */
public interface SqlSessionFactory {

    /**
     * 打开 SqlSession 连接数据库
     * @return SqlSession
     */
    SqlSession openSession();
}
