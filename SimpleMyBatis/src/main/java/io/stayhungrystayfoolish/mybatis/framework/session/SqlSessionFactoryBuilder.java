package io.stayhungrystayfoolish.mybatis.framework.session;

import io.stayhungrystayfoolish.mybatis.framework.configuration.Configuration;
import io.stayhungrystayfoolish.mybatis.framework.configuration.XMLConfigParser;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 2:58 PM
 * @Description:
 * @Version: 1.0
 */
public class SqlSessionFactoryBuilder {

    // 全局配置文件、映射文件配置信息
    private Configuration configuration;

    public SqlSessionFactoryBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 使用流方式读取配置文件
     * @param inputStream 字节流
     * @return SqlSessionFactory
     */
    public SqlSessionFactory build(InputStream inputStream) {
        // 解析全局配置文件，封装为 Configuration 对象
        // 通过 InputStream 流对象，去创建 Document对象（dom4j）---此时没有针对xml文件中的语义进行解析
        // DocumentReader---去加载InputStream流，创建Document对象的
        Document document = DocumentReader.createDocument(inputStream);
        // 进行 mybatis 语义解析（全局配置文件语义解析、映射文件语义解析）
        // XMLConfigParser---解析全局配置文件
        // XMLMapperParser---解析全局配置文件
        XMLConfigParser xmlConfigParser = new XMLConfigParser(configuration);
        configuration = xmlConfigParser.parseConfiguration(document.getRootElement());
        return build();
    }

    /**
     * 使用流方式读取配置文件
     * @param reader 字符流
     * @return SqlSessionFactory
     */
    public SqlSessionFactory build(Reader reader) {

        return build();
    }

    /**
     * 使用 DatasourceConfiguration ( 数据库配置信息 ) 构建 SqlSessionFactory
     * @return SqlSessionFactory
     */
    private SqlSessionFactory build() {
        return new DefaultSqlSessionFactory(configuration);
    }
}
