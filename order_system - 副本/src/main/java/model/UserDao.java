package model;

import util.OrderSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public void add(User user) throws OrderSystemException {
//        JDBC编程基本流程:
//        1.先和数据库建立连接;DataSource
        Connection connection = DBUtil.getConnection();
//        2.拼装sql语句;PrepareStatement;
        String sql = "insert into user values(null, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,user.getIsAdmin());
            statement.setString(2,user.getName());
            statement.setString(3,user.getPassword());

//        3.执行sql语句;executeQuery,executeUpdate
            int ret = statement.executeUpdate();
            if (ret != 1) {
                throw new OrderSystemException("插入用户失败");
            }
            System.out.println("插入用户成功!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("插入用户失败");
        } finally {
//        4.关闭连接(如果是查询语句,还需要遍历结果集);close
            DBUtil.close(connection, statement, null);
        }
    }

    public User selectByName(String name) throws OrderSystemException {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from user where name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setIsAdmin(resultSet.getInt("isAdmin"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("按姓名查找失败!");
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    public User selectByUserId(int userId) throws OrderSystemException {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from user where userId = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setIsAdmin(resultSet.getInt("isAdmin"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("按用户id查找失败!");
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    //单元测试
//    public static void main(String[] args) throws OrderSystemException {
//        UserDao userDao = new UserDao();
////        User user = new User();
////        user.setName("ys");
////        user.setPassword("123");
////        user.setIsAdmin(0);
////        userDao.add(user);
//        User user = userDao.selectByName("ys");
//        System.out.println("按照姓名查找");
//        System.out.println(user);
//
//        user = userDao.selectByUserId(2);
//        System.out.println("按照id查找");
//        System.out.println(user);
//    }
}
