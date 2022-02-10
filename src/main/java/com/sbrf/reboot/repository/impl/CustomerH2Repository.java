package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerH2Repository implements CustomerRepository {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/my_db";

    private final String USER = "sa";
    private final String PASS = "";

    private DataSource dataSource;

    public CustomerH2Repository() {
        initDatasource();
        createTableIfNotExists();
    }

    private void initDatasource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(JDBC_DRIVER);
        config.setJdbcUrl(DB_URL);
        config.setUsername(USER);
        config.setPassword(PASS);
        this.dataSource = new HikariDataSource(config);
    }

    @SneakyThrows
    void createTableIfNotExists() {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS `CUSTOMER` (" +
                    "`ID` INT PRIMARY KEY AUTO_INCREMENT, " +
                    "`NAME` VARCHAR(255) NOT NULL, " +
                    "`EMAIL` VARCHAR(255))");
            statement.executeBatch();
            connection.commit();
        } catch (BatchUpdateException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM CUSTOMER")) {
            while (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getLong("id"));
                customer.setName(result.getString("name"));
                customer.setEMail(result.getString("eMail"));
                customers.add(customer);
            }
        }

        return customers;
    }

    @SneakyThrows
    @Override
    public boolean createCustomer(String name, String eMail) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO CUSTOMER(NAME, EMAIL) VALUES(?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, eMail);
            int affected = stmt.executeUpdate();
            return affected > 0;
        }
    }
}


