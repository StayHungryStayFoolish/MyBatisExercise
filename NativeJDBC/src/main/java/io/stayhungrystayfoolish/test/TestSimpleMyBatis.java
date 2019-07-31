package io.stayhungrystayfoolish.test;

import io.stayhungrystayfoolish.dao.UserDao;
import io.stayhungrystayfoolish.dao.impl.UserDaoImpl;
import io.stayhungrystayfoolish.domain.User;
import io.stayhungrystayfoolish.mybatis.framework.session.SqlSessionFactory;
import io.stayhungrystayfoolish.mybatis.framework.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/31 8:19 PM
 * @Description: 测试手写 MyBatis
 * @Version: 1.0
 */
public class TestSimpleMyBatis {

    private SqlSessionFactory sqlSessionFactory;


    @Before
    public void init() {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindUserById() {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = new User();
        user.setId(1L);
        User result = userDao.queryUserById(user);
        System.out.println(result);
    }
}
