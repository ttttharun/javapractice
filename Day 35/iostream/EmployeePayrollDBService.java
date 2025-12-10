package com.javapractice.iostream;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return this.getEmployeePayrollDataUsingDB(sql);
    }

    private List<EmployeePayrollData> getEmployeePayrollDataUsingDB(String sql) {
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

    public void addEmployeePayrollDataUC7(String name, String gender, double salary, LocalDate startDate) {
        String sql = "INSERT INTO employee_payroll (name, gender, salary, start) VALUES (?, ?, ?, ?)";

        try (Connection connection = this.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setDouble(3, salary);
            ps.setDate(4, java.sql.Date.valueOf(startDate));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public Map<String, Double> getAverageSalaryByGender() {
        String sql = "SELECT gender, AVG(salary) as avg_salary FROM employee_payroll GROUP BY gender;";
        Map<String, Double> genderToAvgSalaryMap = new HashMap<>();
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                Double salary = resultSet.getDouble("avg_salary");
                genderToAvgSalaryMap.put(gender, salary);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genderToAvgSalaryMap;
    }

    public EmployeePayrollData addEmployeePayrollData(String name, String gender,
                                       double salary, LocalDate startDate) {
        int employeeId = -1;
        Connection connection = null;
        EmployeePayrollData employeePayrollData = null;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Statement statement = connection.createStatement()){
            String sql = String.format("INSERT INTO employee_payroll " +
                    "(name, gender, salary, start) VALUES ('%s', '%s', %s, '%s')",
                    name, gender, salary, Date.valueOf(startDate));
            int rowAffected = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (rowAffected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) employeeId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                return employeePayrollData;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        try (Statement statement = connection.createStatement()) {
            double deductions = salary * 0.2;
            double taxable_pay = salary - deductions;
            double tax = taxable_pay * 0.1;
            double net_pay = salary - tax;
            String sql = String.format("INSERT INTO payroll_details " +
                    "(employee_id, basic_pay, deductions, taxable_pay, tax, net_pay) VALUES " +
                    "(%s, %s, %s, %s, %s, %s)", employeeId, salary, deductions, taxable_pay, tax, net_pay);
            int rowAffected = statement.executeUpdate(sql);
            if (rowAffected == 1) {
                employeePayrollData = new EmployeePayrollData(employeeId, name, salary, startDate);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                return  employeePayrollData;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } try {
            connection.commit();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return employeePayrollData;
    }
}
