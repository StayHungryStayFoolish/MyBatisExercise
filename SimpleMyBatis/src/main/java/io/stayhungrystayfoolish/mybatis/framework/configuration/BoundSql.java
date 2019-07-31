package io.stayhungrystayfoolish.mybatis.framework.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 5:01 PM
 * @Description:
 * @Version: 1.0
 */
public class BoundSql {

    //解析之后的SQL语句
    private String sql;
    //解析出来的参数
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

}
