package io.stayhungrystayfoolish.test;

import org.junit.Test;

import java.sql.*;

public class TestJDBC {

    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_exercise?characterEncoding=utf-8", "root", "admin");
            String sql = "insert into user (username,birthday,sex,address) values (?,?,?,?)";
            // 设置 Insert 以后返回自动生成 id
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "lily");
            preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
            preparedStatement.setString(3, "female");
            preparedStatement.setString(4, "Beijing");
            resultSet = preparedStatement.getGeneratedKeys();
            if (null != resultSet && resultSet.next()) {
                System.out.println("Generated Result Id : " + resultSet.getInt(1));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_exercise?characterEncoding=utf-8", "root", "admin");
            String sql = "select * from user where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "lily");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " : " + resultSet.getString("username"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
