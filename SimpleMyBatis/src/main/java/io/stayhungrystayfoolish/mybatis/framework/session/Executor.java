package io.stayhungrystayfoolish.mybatis.framework.session;

import io.stayhungrystayfoolish.mybatis.framework.configuration.Configuration;
import io.stayhungrystayfoolish.mybatis.framework.configuration.MappedStatement;

import java.util.List;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 7:21 PM
 * @Description: Sql 执行器
 * @Version: 1.0
 */
public interface Executor {

    /**
     * 创建一个实体
     *
     * @param configuration 全局配置
     * @param mappedStatement mapper 配置
     * @param param       创建实体
     * @param <T>         返回泛型
     * @return 实体主键
     */
    <T> T save(Configuration configuration, MappedStatement mappedStatement,Object param);

    /**
     * 查询集合
     *
     * @param configuration 全局配置
     * @param mappedStatement mapper 配置
     * @param param 查询参数
     * @param <T> 返回泛型
     * @return 查询实体集合
     */
    <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object param);

}
