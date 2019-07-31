package io.stayhungrystayfoolish.dao.impl;

import io.stayhungrystayfoolish.dao.UserDao;
import io.stayhungrystayfoolish.domain.User;
import io.stayhungrystayfoolish.util.Datasource;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    @Override
    public Long createUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "insert into user (username,birthday,sex,address) values (?,?,?,?)";
            connection = Datasource.getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
            preparedStatement.setString(3,user.getSex());
            preparedStatement.setString(4,user.getAddress());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (null != resultSet && resultSet.next()) {
                return Long.valueOf(resultSet.getInt(1));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Datasource.close(resultSet, preparedStatement, connection);
        }
        return null;
    }

    @Override
    public User queryUserByName(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from user where username = ?";
            connection = Datasource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (null != resultSet && resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setSex(resultSet.getString("sex"));
                user.setAddress(resultSet.getString("address"));
                return user;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Datasource.close(resultSet, preparedStatement, connection);
        }
        return null;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }
}
