package com.qf.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author zyh
 * @date 2019/9/10 0010 14:18
 * 佛祖保佑
 */
public class DataSourceUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal;
    static{
        threadLocal = new ThreadLocal<>();
        Properties properties = new Properties();
        InputStream stream = DataSourceUtils.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(stream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("初始化连接池失败");
        }
    }
    public static DataSource getDataSource(){

      return  dataSource;
    }
    public static Connection getConnection(){
        Connection connection = threadLocal.get();
        if (connection==null){
            try {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void startTran() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }
    public static void commitTran() throws SQLException {
        Connection connection = getConnection();
        connection.commit();
    }
    public static void collBack() throws SQLException {
        Connection connection = getConnection();
        connection.rollback();
    }
    public static void close() throws SQLException {
        Connection connection = getConnection();
        connection.close();
        threadLocal.remove();
    }
}
