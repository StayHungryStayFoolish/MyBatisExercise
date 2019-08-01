# MyBatis 

###
```java
    SqlSessionFactoryBuilder  
        build(parse.parse()) : 解析全局配置文件，构建 SqlSesionFactory
        XMLConfigBuilder 
            parse(parseConfiguration()): 解析全局配置文件，获取 Configuration 对象
                parseConfiguration(XNode root) :
``` 
