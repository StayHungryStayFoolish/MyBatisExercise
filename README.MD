# MyBatis 源码主流程 

#### 创建 SqlSessionFactory

```
    SqlSessionFactoryBuilder  
        build(parse.parse()) : 解析全局配置文件，构建 SqlSesionFactory
        XMLConfigBuilder 
            parse(parseConfiguration()): 解析全局配置文件，获取 Configuration 对象
                parseConfiguration(XNode root) : 从 <configuration> 根标签依次解析
                    mapperElement(XNode parent) : 解析 <mappers> 标签，解析 mapper 映射文件根路径
``` 

#### 解析 Mapper 映射文件

```
    XMLMapperBuilder
        mapperParser.parse() : 解析 mapper 映射文件
            configurationElement(XNode context) : 解析映射文件
                二级缓存
                解析 <cache-ref> 子标签
                cacheRefElement(context.evalNode("cache-ref"))
                解析 <cache> 子标签
                cacheElement(context.evalNode("cache"))
                
                解析 <parameterMap> 子标签
                parameterMapElement(context.evalNodes("/mapper/parameterMap"))
                解析 <resultMap> 子标签
                resultMapElements(context.evalNodes("/mapper/resultMap"))
                解析 <sql> 子标签，也就是 SQL 片段
                sqlElement(context.evalNodes("/mapper/sql"))
                解析 <select>、<insert>、<update>、<delete> 子标签
                buildStatementFromContext(context.evalNodes("select|insert|update|delete"))
                    
                    构建 MapperStatement 对象
                    buildStatementFromContext(List<XNode> list, String requiredDatabaseId)
                        
                        具体解析 <select>、<insert>、<update>、<delete> 子标签    
                        parseStatementNode() : 
```

#### 具体解析 Mapper 映射文件的 CRUD 标签，构建 MappedStatement

```
    XMLStatementBuilder
        parseStatementNode() :
         
            设置默认 StatementType 为 Prepared，参数指定了后面的 JDBC 处理时，采用哪种 Statement
            StatementType statementType = StatementType.valueOf(context.getStringAttribute("statementType", StatementType.PREPARED.toString()));
            
            创建 SqlSource，解析 SQL ，封装 SQL 语句（未参数绑定）和入参信息
            SqlSource sqlSource = langDriver.createSqlSource(configuration, context, parameterTypeClass);
            
```
