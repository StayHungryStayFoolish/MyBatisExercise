# 基于 MyBatis 原理实现简单 ORM 框架

#### 配置类解析

1. 解析配置

```text   
    1. XMLConfigParser 解析 XML 全局及数据库配置并封装对象
    
        2. XMLMapperParser 解析 mappers sql 语句文件
        
            3. SqlSource 封装从 mapper 文件读取的未解析 sql 语句，
                    getBoundSql() 解析 sql 语句为 MySQL 可读语句，
                    并将 sql 语句内的参数封装到 BondSql 对象内。
                    
                4. MappedStatement 封装 mapper 文件 <insert></><select><update><delete> 标签内的内容
                    
                    5. 将 mapper 文件的 statementId 和对应的 mappedStatement 对象封装到全局配置对象 Configuration
```
    
   
注:  XMLMapperParser 解析 mapper 文件，封装 SqlSource、BoundSql、MappedStatement、ParameterMapping 对象


#### 执行 mapper 文件

1. 
