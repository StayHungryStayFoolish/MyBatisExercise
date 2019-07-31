package io.stayhungrystayfoolish.mybatis.framework.util;

import io.stayhungrystayfoolish.mybatis.framework.configuration.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 5:41 PM
 * @Description:
 * @Version: 1.0
 */
public class ParameterMappingTokenHandler implements TokenHandler {

    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    @Override
    public String handleToken(String content) {
        parameterMappings.add(buildParameterMapping(content));
        return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
        return new ParameterMapping(content);
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
