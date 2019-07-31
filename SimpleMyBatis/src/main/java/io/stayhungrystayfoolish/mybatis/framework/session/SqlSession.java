package io.stayhungrystayfoolish.mybatis.framework.session;

import java.util.List;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 12:04 PM
 * @Description: SqlSession 方法
 * @Version: 1.0
 */
public interface SqlSession {

    /**
     * 创建一个实体
     * @param statementId xml 文件内对应的 statementId
     * @param param 创建实体
     * @param <T> 返回泛型
     * @return 实体主键
     */
    <T> T insert(String statementId, Object param);

    /**
     * 查询单个
     * @param statementId xml 文件内对应的 statementId
     * @param param 查询参数
     * @param <T> 返回泛型
     * @return 查询实体
     */
    <T> T selectOne(String statementId, Object param);

    /**
     * 查询集合
     * @param statementId xml 文件内对应的 statementId
     * @param param 查询参数
     * @param <T> 返回泛型
     * @return 查询实体集合
     */
    <T> List<T> selectList(String statementId, Object param);

}
