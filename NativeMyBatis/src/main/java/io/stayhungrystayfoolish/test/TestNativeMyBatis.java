package io.stayhungrystayfoolish.test;

import io.stayhungrystayfoolish.dao.PersonDao;
import io.stayhungrystayfoolish.dao.impl.PersonDaoImpl;
import io.stayhungrystayfoolish.domain.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestNativeMyBatis {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws Exception{
        // 加载全局配置文件（同时把映射文件也加载了）
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testCreatePerson() {
        PersonDao dao = new PersonDaoImpl(sqlSessionFactory);
        Person person = new Person();
        person.setName("bonismo");
        person.setSex("male");
        person.setAge(20);
        Long id = dao.createPerson(person);
        System.out.println(id);
        System.out.println(person);
    }

    @Test
    public void testFindPersonById() {
        PersonDao dao = new PersonDaoImpl(sqlSessionFactory);
        Person person = dao.findPersonById(1L);
        System.out.println(person);
    }

    @Test
    public void testFindPersonByName() {
        PersonDao dao = new PersonDaoImpl(sqlSessionFactory);
        Person person = dao.findPersonByName("bonismo");
        System.out.println(person);
    }

    @Test
    public void testFindAllPerson() {
        PersonDao dao = new PersonDaoImpl(sqlSessionFactory);
        List<Person> list = dao.findAllPerson();
        System.out.println(list);
    }

    @Test
    public void testDeletePersonById() {
        PersonDao dao = new PersonDaoImpl(sqlSessionFactory);
        dao.deleteById(1L);
    }
}
