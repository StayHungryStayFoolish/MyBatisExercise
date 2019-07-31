package io.stayhungrystayfoolish.mybatis.framework.session;

import io.stayhungrystayfoolish.mybatis.framework.configuration.*;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 7:28 PM
 * @Description:
 * @Version: 1.0
 */
public class SimpleExecutor implements Executor {

    private Connection connection;

    @Override
    public <T> T save(Configuration configuration, MappedStatement mappedStatement, Object param) {
        return null;
    }

    /**
     * 查询
     * @param configuration 全局配置
     * @param mappedStatement mapper 配置
     *                         封装了 statementId、
     *                         parameterTypeClass、
     *                         resultTypeClass、
     *                         statementType、
     *                         sqlSource（封装了我解析 sql 语句、解析方法）
     * @param param 查询参数
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object param) {
        List<Object> results = new ArrayList<>();
        try {
            // 根据配置获取 DataSource
            DataSource dataSource = configuration.getDataSource();
            connection = dataSource.getConnection();

            // 获取未解析 sql 语句
            SqlSource sqlSource = mappedStatement.getSqlSource();
            // 解析 sql 语句
            BoundSql boundSql = sqlSource.getBoundSql();
            String sql = boundSql.getSql();

            // 获取 statementType 调用原生 JDBC
            String statementType = mappedStatement.getStatementType();

            if ("prepared".equals(statementType)) {
                // 将解析后的 sql 语句用预编译对象编译
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // 获取 sql 语句中的参数
                List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
                // 获取入参 parameterType 类型，基本类型、Map、List、POJO 类型
                Class<?> parameterTypeClass = mappedStatement.getParameterTypeClass();

                // 八种基本类型都可以如此处理
                if (parameterTypeClass == Integer.class) {
                    // 根本不关系#{}中的名称到底是什么
                    preparedStatement.setObject(1, param);
                } else {
                    // TODO: 2019/7/31  Map和List的我们暂不处理，此处主要解决POJO类型
                    // 遍历参数
                    for (int i = 0; i < parameterMappings.size(); i++) {
                        ParameterMapping parameterMapping = parameterMappings.get(i);
                        // 得到属性名称
                        String name = parameterMapping.getName();
                        // 通过反射获取入参对象中执行名称的属性值
                        Field field = parameterTypeClass.getDeclaredField(name);
                        // 设置暴力赋值，不管属性是不是私有
                        field.setAccessible(true);
                        Object value = field.get(param);
                        // 将值传入 preparedStatement
                        preparedStatement.setObject(i + 1, value);
                    }
                }

                ResultSet resultSet = preparedStatement.executeQuery();
                // 获取返回类型
                Class<?> resultTypeClass = mappedStatement.getResultTypeClass();

                while (resultSet.next()) {
                    // 创建返回类型 instance
                    Object returnObj = resultTypeClass.newInstance();
                    // 获取返回类型的元数据
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    // 获取元数据的列数
                    int count = metaData.getColumnCount();
                    for (int i = 1; i <= count; i++) {
                        // 根据列获取列名
                        String columnName = metaData.getColumnName(i);
                        // 反射获取类成员变量名字
                        Field field = resultTypeClass.getDeclaredField(columnName);
                        field.setAccessible(true);
                        // 获取查询结果 resultSet 值， getObject 可以使用索引  getObject(i+1)
                        Object value = resultSet.getObject(columnName);
                        field.set(returnObj, value);
                    }

                    results.add(returnObj);
                }
            } else {
                // ....
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return (List<T>) results;
    }
}
