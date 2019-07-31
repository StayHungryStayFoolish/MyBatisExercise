package io.stayhungrystayfoolish.mybatis.framework.configuration;

import io.stayhungrystayfoolish.mybatis.framework.session.DocumentReader;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 3:45 PM
 * @Description: XML 配置文件解析
 * @Version: 1.0
 */
public class XMLConfigParser {

    private Configuration configuration;

    public XMLConfigParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration parseConfiguration(Element element) {
        // SqlMapConfig.xml 的 <environments> 标签
        parseEnvironments(element.element("environments"));
        // SqlMapConfig.xml 的多个 <mappers> 标签
        parseMappers(element.element("mappers"));

        return configuration;
    }

    /**
     * 解析 <environments> 标签，判断 default 和 id 的值是否相同，并继续解析 <dataSource> 标签
     * @param environments <environments> 标签
     */
    private void parseEnvironments(Element environments) {
        String defaultId = environments.attributeValue("default");
        List<Element> elementList = environments.elements("environment");
        for (Element element : elementList) {
            String envId = element.attributeValue("id");
            // environments 的 default 标签的值必须和 environment 的 id 值相等才能解析数据库配置
            // XMLConfigBuilder 源码的 296，428 行
            if (null != defaultId && null != envId ) {
                if (defaultId.equals(envId)) {
                    parseDataSource(element.element("dataSource"));
                }
            }
        }
    }

    /**
     * 解析 <mappers> 标签集合
     * @param mappers <mappers> 集合
     */
    private void parseMappers(Element mappers) {
        List<Element> elements = mappers.elements("mapper");
        for (Element mapperEle : elements) {
            parseMapper(mapperEle);
        }
    }

    /**
     * 解析 <dataSource> 标签 ，使用 Apache 的 BasicDataSource 创建数据库
     * @param element <dataSource> 标签信息
     */
    private void parseDataSource(Element element) {
        String type = element.attributeValue("type");
        if (type == null || "".equals(type)) {
            type = "DBCP";
        }
        List<Element> elements = element.elements("property");

        // 使用 Properties 设置 dom4j 获取的值
        Properties properties = new Properties();
        for (Element propertyEle : elements) {
            String name = propertyEle.attributeValue("name");
            String value = propertyEle.attributeValue("value");
            properties.setProperty(name, value);
        }

        BasicDataSource dataSource = null;
        if ("DBCP".equals(type)) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
        }
        configuration.setDataSource(dataSource);
    }

    /**
     * 解析 <mapper> 单个标签
     * @param mapperEle <mapper> 标签
     */
    private void parseMapper(Element mapperEle) {
        // 获取 mapper 文件的路径
        String resource = mapperEle.attributeValue("resource");
        // 获取 mapper 文件的字节流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        Document document = DocumentReader.createDocument(inputStream);
        // 构建 mapper 文件解析
        XMLMapperParser xmlMapperParser = new XMLMapperParser(configuration);
        // 解析 mapper 文件
        xmlMapperParser.parse(document.getRootElement());
    }
}
