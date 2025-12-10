package com.javapractice.iostream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollFileIOService {
    public static String PAYROLL_FILE_NAME = "payroll-file.txt";
    public void writeData(List<EmployeePayrollData> employeePayrollDataList) {
        StringBuffer empBuffer = new StringBuffer();
        employeePayrollDataList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });

        try {
            Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EmployeePayrollData parseEmployee(String line) {
        line = line.replace("EmployeePayrollData{", "")
                .replace("}", "");
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0].split("=")[1].trim());
        String name = parts[1].split("=")[1].trim().replace("'", "");
        double salary = Double.parseDouble(parts[2].split("=")[1].trim());

        return new EmployeePayrollData(id, name, salary);
    }

    public List<EmployeePayrollData> readData() {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {
            Files.lines(new File(PAYROLL_FILE_NAME).toPath())
                    .forEach(line -> {
                        EmployeePayrollData emp = parseEmployee(line);
                        employeePayrollList.add(emp);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    public void printData() {
        try {
            Files.lines(new File(PAYROLL_FILE_NAME).toPath())
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File(PAYROLL_FILE_NAME).toPath())
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }
}
