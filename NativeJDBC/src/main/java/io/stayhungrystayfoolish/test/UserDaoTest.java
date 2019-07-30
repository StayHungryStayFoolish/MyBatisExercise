package io.stayhungrystayfoolish.test;

import io.stayhungrystayfoolish.dao.UserDao;
import io.stayhungrystayfoolish.dao.impl.UserDaoImpl;
import io.stayhungrystayfoolish.domain.User;
import org.junit.Test;

import java.util.Date;

public class UserDaoTest {

    @Test
    public void testInsert() {
        UserDao userDao = new UserDaoImpl();
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
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserByName(username);
        System.out.println(user.toString());
    }
}
