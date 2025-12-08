package com.javapractice.iostream;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {

    private static EmployeePayrollDBService employeePayrollDBService;
    private EmployeePayrollDBService(){}

    public static EmployeePayrollDBService getInstance() {
        if (employeePayrollDBService == null)
            employeePayrollDBService = new EmployeePayrollDBService();
        return employeePayrollDBService;
    }

    private List<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet) {
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollDataList.add(new EmployeePayrollData(id, name, salary, startDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeePayrollDataList;
    }

    private Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        Connection connection;
        System.out.println("Connecting to database." + jdbcUrl);
        connection = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Connection successful!");
        return connection;
    }

    public List<EmployeePayrollData> readData() {
        String sql = "SELECT * FROM employee_payroll";
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            employeePayrollDataList = this.getEmployeePayrollData(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeePayrollDataList;
    }

    public int updateEmployeeData(String name, double salary) {
        return this.updateEmployeeDataUsingPreparedStatement(name, salary);
    }

    private int updateEmployeeDataUsingPreparedStatement(String name, double salary) {
        String sql = String.format("update employee_payroll set salary = %.2f where name = '%s';", salary, name);
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<EmployeePayrollData> getEmployeePayrollData(String name) {
        String sql = "SELECT * FROM employee_payroll WHERE name = ?";
        try (Connection connection = this.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return getEmployeePayrollData(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<EmployeePayrollData> getEmployeePayrollDataBetweenDates(LocalDate start, LocalDate end) {
        String sql = "SELECT * FROM employee_payroll WHERE start BETWEEN ? AND ?";
        try (Connection connection = this.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(start));
            ps.setDate(2, java.sql.Date.valueOf(end));
            ResultSet rs = ps.executeQuery();
            return getEmployeePayrollData(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
