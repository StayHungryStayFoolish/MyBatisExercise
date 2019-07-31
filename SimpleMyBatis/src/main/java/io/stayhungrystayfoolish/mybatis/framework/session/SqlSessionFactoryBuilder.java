package io.stayhungrystayfoolish.mybatis.framework.session;

import io.stayhungrystayfoolish.mybatis.framework.configuration.DatasourceConfiguration;
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
    private DatasourceConfiguration datasourceConfiguration;

    public SqlSessionFactoryBuilder() {
        this.datasourceConfiguration = new DatasourceConfiguration();
    }

    /**
     * 使用流方式读取配置文件
     * @param inputStream 字节流
     * @return SqlSessionFactory
     */
    public SqlSessionFactory build(InputStream inputStream) {
        // 使用自定义 xml 路径的字节流，读取配置文件
        Document document = DocumentReader.createDocument(inputStream);
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
        return new DefaultSqlSessionFactory(datasourceConfiguration);
    }
}
