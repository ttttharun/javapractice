package com.javapractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    public List<EmployeePayrollData> employeePayrollDataList;
    public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO}

    public EmployeePayrollService() {
        employeePayrollDataList = new ArrayList<>();
    }

    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollDataList) {
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

    public long readEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            this.employeePayrollDataList = new EmployeePayrollFileIOService().readData();
        return employeePayrollDataList.size();
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
}
