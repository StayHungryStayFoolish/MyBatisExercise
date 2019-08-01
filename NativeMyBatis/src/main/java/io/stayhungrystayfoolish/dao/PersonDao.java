package io.stayhungrystayfoolish.dao;


import io.stayhungrystayfoolish.domain.Person;

import java.util.List;

public interface PersonDao {

    Long createPerson(Person person);

    Person findPersonById(Long id);

    Person findPersonByName(String name);

    List<Person> findAllPerson();

    void deleteById(Long id);
}
