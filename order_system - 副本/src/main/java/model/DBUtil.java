package model;

import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//管理员:1 普通用户:0
//订单完成:1 未完成:0

public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/order_system?characterEncoding=utf-8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1996";

    private static volatile DataSource dataSource = null;

    private static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setURL(URL);
                    ((MysqlDataSource)dataSource).setUser(USERNAME);
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("数据库连接失败");
        return null;
    }

    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) { //连接,语句,结果集
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
