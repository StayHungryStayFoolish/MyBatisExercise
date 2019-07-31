package io.stayhungrystayfoolish.mybatis.framework.configuration;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 5:02 PM
 * @Description:
 * @Version: 1.0
 */
public class ParameterMapping {

    private String name;

    public ParameterMapping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
