package io.stayhungrystayfoolish.dao.impl;

import io.stayhungrystayfoolish.dao.PersonDao;
import io.stayhungrystayfoolish.domain.Person;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PersonDaoImpl implements PersonDao {

    private final static String NAMESPACE = "native.";
    private final static String CREATE_PERSON = "createPerson";
    private final static String FIND_PERSON_BY_ID = "findPersonById";
    private final static String FIND_PERSON_BY_NAME = "findPersonByName";
    private final static String FIND_ALL_PERSON = "findAllPerson";
    private final static String DELETE_BY_ID = "deleteById";

    private final SqlSessionFactory sqlSessionFactory;

    public PersonDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Long createPerson(Person person) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // sqlSession 接口，开发人员使用它对数据库进行增删改查操作
        // sqlSession 调用 Executor 执行，在 Configuration 中默认的 ExecutorType 是 SIMPLE
        int id = sqlSession.insert(NAMESPACE.concat(CREATE_PERSON), person);
        sqlSession.commit();
        return person.getId();
    }

    @Override
    public Person findPersonById(Long id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne(NAMESPACE.concat(FIND_PERSON_BY_ID), id);
    }

    @Override
    public Person findPersonByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne(NAMESPACE.concat(FIND_PERSON_BY_NAME), name);
    }

    @Override
    public List<Person> findAllPerson() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectList(NAMESPACE.concat(FIND_ALL_PERSON));
    }

    @Override
    public void deleteById(Long id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete(NAMESPACE.concat(DELETE_BY_ID), id);
        sqlSession.commit();
    }
}
