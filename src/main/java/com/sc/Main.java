package com.sc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sc.dao.UserMapper;
import com.sc.entity.User;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/for_mybatis");
//        dataSource.setDatabaseName("for_mybatis");
        dataSource.setPassword("123456");
        dataSource.setUser("root");

        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            User user = (User) session.selectOne("com.sc.dao.UserMapper.selectOne", 1);
            System.out.println(user);
        }

    }
}
