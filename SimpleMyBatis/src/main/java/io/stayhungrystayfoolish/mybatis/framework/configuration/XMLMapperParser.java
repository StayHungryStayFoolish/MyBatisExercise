package io.stayhungrystayfoolish.mybatis.framework.configuration;

import org.dom4j.Element;

import java.util.List;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 4:34 PM
 * @Description:
 * @Version: 1.0
 */
public class XMLMapperParser {

    private String namespace;
            
    private Configuration configuration;

    public XMLMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(Element rootElement) {
        // 将 select 标签解析为 MapperStatement 对象
        // 解析 <mapper namespace="">
        namespace = rootElement.attributeValue("namespace");
        // 将解析 MappedStatement 对象放入 DatasourceConfiguration
        // 解析 <select>
        parseStatements(rootElement.elements("select"));
    }

    private void parseStatements(List<Element> elements) {
        /*
         *  解析 <select> 标签内容
         *     <select id="findUserById" parameterType="com.kkb.mybatis.po.User" resultType="com.kkb.mybatis.po.User" statementType="prepared">
         *         SELECT * FROM user WHERE id = #{id}
         *     </select>
         */
        for (Element selectEle : elements) {
            parseStatement(selectEle);
        }
    }

    private void parseStatement(Element selectEle) {
        // statementId
        String id = selectEle.attributeValue("id");
        id = namespace + "." + id;

        String parameterType = selectEle.attributeValue("parameterType");
        String resultType = selectEle.attributeValue("resultType");
        String statementType = selectEle.attributeValue("statementType");

        // 通过反射获取类对象
        Class<?> parameterTypeClass = getClassType(parameterType);
        Class<?> resultTypeClass = getClassType(resultType);


        // getTextTrim() 获取标签内的文本内容
        // 还包含#{id}占位符的SQL语句
        // 此时拿到未解析的SQL语句，还需要进行特殊解析
        // 使用面向对象思想，创建 SqlSource 对象（提供获取SQL语句和SQL语句中的参数这个功能）

        // 获取的SQL语句：select * from user where id = #{id}
        String sqlText = selectEle.getTextTrim();
        // 需要的SQL语句：select * from user where id = ?
        SqlSource sqlSource = new SqlSource(sqlText);

        // 封装MappedStatement对象
        // 可以使用构建者模式去创建MappedStatement对象
        MappedStatement mappedStatement =
                new MappedStatement(id, parameterTypeClass, resultTypeClass, statementType, sqlSource);

        // 将解析的 statementId 和封装好的 MappedStatement 对象添加到 datasourceConfiguration
        configuration.addMappedStatement(id, mappedStatement);
    }

    private Class<?> getClassType(String parameterType) {
        if (parameterType == null || "".equals(parameterType)) {
            return null;
        }
        try {
            return Class.forName(parameterType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
