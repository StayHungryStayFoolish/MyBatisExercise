# MyBatis 源码主流程 

## 加载配置文件流程
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

#### 创建 SqlSource，解析并封装 SQL 及入参信息

```
    LanguageDirver 接口 :
        SqlSource createSqlSource(Configuration configuration, XNode script, Class<?> parameterType)
            XMLLanguageDriver : 该接口最终调用类
                createSqlSource() : 创建 SqlSource
                    初始化 SQL 标签处理器
                    XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
                        使用处理器解析
                        builder.parseScriptNode()
```

#### 解析 SQL 

```
    XMLScriptBuilder
        parseScriptNode() : 解析 SQL 语句
            解析select、insert、update、delete 标签中的 SQL 语句，最终将解析到的 SqlNode 封装到 MixedSqlNode 中的 List 集合中
		    将带有 ${} SQL信息封装到 TextSqlNode
		    将带有 #{} SQL信息封装到 StaticTextSqlNode
		    将动态 SQL 标签中的 SQL 信息分别封装到不同的 SqlNode 
            MixedSqlNode rootSqlNode = parseDynamicTags(context);
                SQL 文本内容封装，判断是否动态。
                TextSqlNode textSqlNode = new TextSqlNode(data);
```        

#### SQL 文本分词解析

```
    TextSqlNode
        isDynamic()
            解析 SQL 文本，具体对 #{} 和 ${} 的处理，交给 TokenHandler 子类处理
            GenericTokenParser parser = createParser(checker);
                parse(String text) : 解析 #{}、${} 
                    handler.handleToken(expression.toString()) 

```

## SqlSource 创建流程

#### XMLStatementBuilder 解析 parseStatementNode() 创建

```
    创建 SqlSource，解析 SQL，封装 SQL 语句（未参数绑定）和入参信息
    SqlSource sqlSource = langDriver.createSqlSource(configuration, context, parameterTypeClass);
        LanguageDriver 接口 createSqlSource() 方法
            XMLLanguageDriver 调用该实现类实现创建 SqlSource
                XMLScriptBuilder
                    parseScriptNode()
                        parseDynamicTags(context) : context 是解析到的 CRUD 标签，
                        最终解析到 SqlNode 封装到 MixedSqlNode 的 List集合。具体可以看 [解析 SQL]
                            textSqlNode.isDynamic() 
                                parse() : 调用 ParameterMappingTokenHandler 的 handleToken() 最终创建 ParameterMapping 对象，
                                使用构造方法封装 SqlSource
                                （最后的实例为 StaticSqlSource，BoundSql 存储在里边。BoundSql中要存储SQL语句的一个载体，
                                 SqlSource的SQL语句，最终都会存 储到该SqlSource实现类DynamicSqlSource、RawSqlSource 中）。
                                     
```
