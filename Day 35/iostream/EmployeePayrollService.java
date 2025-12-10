package com.javapractice.iostream;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EmployeePayrollService {
    public List<EmployeePayrollData> employeePayrollDataList;

    public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO;}

    private final EmployeePayrollDBService employeePayrollDBService;
    public EmployeePayrollService() {
        employeePayrollDBService = EmployeePayrollDBService.getInstance();
    }
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollDataList) {
        this();
        this.employeePayrollDataList = employeePayrollDataList;
    }
    public void readEmployeeData(Scanner consoleInputScanner) {
        System.out.print("Enter Id: ");
        int id = consoleInputScanner.nextInt();
        System.out.print("Enter name: ");
        String name = consoleInputScanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = consoleInputScanner.nextDouble();
        employeePayrollDataList.add(new EmployeePayrollData(id, name, salary));
    }

    public void addEmployeePayrollData(IOService ioService, String name, String gender, double salary, LocalDate startDate) {
        if (ioService.equals(IOService.DB_IO))
            employeePayrollDBService.addEmployeePayrollData(name, gender, salary, startDate);
    }

    public void updateEmployeeSalary(String name, double salary) {
        int result = employeePayrollDBService.updateEmployeeData(name, salary);
        if (result == 0) return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if (employeePayrollData != null) employeePayrollData.setSalary(salary);
    }

    private EmployeePayrollData getEmployeePayrollData(String name) {
        return employeePayrollDataList.stream()
                .filter(data -> data.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<EmployeePayrollData> getEmployeePayrollDataList() {
        return employeePayrollDataList;
    }

    public void setEmployeePayrollDataList(List<EmployeePayrollData> employeePayrollDataList) {
        this.employeePayrollDataList = employeePayrollDataList;
    }

    public void writeEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting employee payroll roaster to console\n" + employeePayrollDataList);
        else if (ioService.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().writeData(employeePayrollDataList);
        }
    }

    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            this.employeePayrollDataList = new EmployeePayrollFileIOService().readData();
        if (ioService.equals(IOService.DB_IO)) {
            this.employeePayrollDataList = employeePayrollDBService.readData();
        }
        return this.employeePayrollDataList;
    }

    public List<EmployeePayrollData> getEmployeeDataWithinDateRange(IOService ioService, LocalDate start, LocalDate end) {
        if (ioService.equals(IOService.DB_IO))
            return employeePayrollDBService.getEmployeePayrollDataBetweenDates(start, end);
        return null;
    }

    public Map<String, Double> readAverageSalaryByGender(IOService ioService) {
        if (ioService.equals(IOService.DB_IO))
            return employeePayrollDBService.getAverageSalaryByGender();
        return null;
    }

    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            new EmployeePayrollFileIOService().printData();
    }

    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            return new EmployeePayrollFileIOService().countEntries();
        return 0;
    }

    public boolean checkEmployeePayrollInSyncWithDB(String name) {
        List<EmployeePayrollData> employeePayrollListFromDB =
                employeePayrollDBService.getEmployeePayrollData(name);
        if (employeePayrollListFromDB.isEmpty()) {
            return false;
        }
        EmployeePayrollData dbEmp = employeePayrollListFromDB.get(0);

        EmployeePayrollData localEmp = this.employeePayrollDataList.stream()
                .filter(emp -> emp.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (localEmp == null) return false;

        return dbEmp.equals(localEmp);
    }
}
