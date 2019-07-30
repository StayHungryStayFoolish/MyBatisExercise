package io.stayhungrystayfoolish.dao;

import io.stayhungrystayfoolish.domain.User;

public interface UserDao {

    Long createUser(User user);

    User queryUserByName(String name);
}
