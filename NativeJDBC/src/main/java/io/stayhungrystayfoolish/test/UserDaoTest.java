package io.stayhungrystayfoolish.test;

import io.stayhungrystayfoolish.dao.UserDao;
import io.stayhungrystayfoolish.dao.impl.UserDaoImpl;
import io.stayhungrystayfoolish.domain.User;
import io.stayhungrystayfoolish.mybatis.framework.session.SqlSessionFactory;
import org.junit.Test;

import java.util.Date;

public class UserDaoTest {

    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testInsert() {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = new User();
        user.setUsername("bonismo");
        user.setBirthday(new Date());
        user.setSex("male");
        user.setAddress("Beijing");
        Long id = userDao.createUser(user);
        System.out.println("Generate User Id : " + id);
    }

    @Test
    public void testQuery() {
        String username = "bonismo";
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.queryUserByName(username);
        System.out.println(user.toString());
    }
}
