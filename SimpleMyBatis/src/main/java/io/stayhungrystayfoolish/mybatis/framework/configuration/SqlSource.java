package io.stayhungrystayfoolish.mybatis.framework.configuration;

import io.stayhungrystayfoolish.mybatis.framework.util.GenericTokenParser;
import io.stayhungrystayfoolish.mybatis.framework.util.ParameterMappingTokenHandler;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 5:01 PM
 * @Description: Mapper 文件内的 Sql 语句及参数
 * @Version: 1.0
 */
public class SqlSource {

    // 未解析 sql 语句
    private String sqlText;

    public SqlSource(String sqlText) {
        this.sqlText = sqlText;
    }

    public BoundSql getBoundSql() {
        // 解析sql文本
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        // parse 方法内使用 ParameterMappingTokenHandler 处理了 sql 语句
        String sql = genericTokenParser.parse(sqlText);
        // 就是将解析之后的SQL语句，和解析出来的SQL参数使用组合模式绑定到一个类中
        return new BoundSql(sql, tokenHandler.getParameterMappings());
    }
}
