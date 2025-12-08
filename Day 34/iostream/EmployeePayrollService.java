package com.javapractice.iostream;

import java.time.LocalDate;
import java.util.List;
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

    public List<EmployeePayrollData> getEmployeeDataWithinDateRange(LocalDate start, LocalDate end) {
        return employeePayrollDBService.getEmployeePayrollDataBetweenDates(start, end);
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
        List<EmployeePayrollData> employeePayrollList = employeePayrollDBService.getEmployeePayrollData(name);
        return employeePayrollList.get(0).equals(getEmployeePayrollData(name));
    }
}
