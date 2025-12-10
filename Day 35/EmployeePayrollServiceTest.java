package com;

import com.javapractice.iostream.EmployeePayrollData;
import com.javapractice.iostream.EmployeePayrollService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.javapractice.iostream.EmployeePayrollService.IOService.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeePayrollServiceTest {
    @Test
    public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        EmployeePayrollData[] arrayOfEmployees = {
                new EmployeePayrollData(1, "Abhisheak", 400000),
                new EmployeePayrollData(2, "Chandana", 450000),
                new EmployeePayrollData(3, "Lynda", 500000)
        };
        EmployeePayrollService employeePayrollService;
        employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmployees));
        employeePayrollService.writeEmployeePayrollData(FILE_IO);
        employeePayrollService.printData(FILE_IO);
        long entries = employeePayrollService.countEntries(FILE_IO);
        assertEquals(3, entries);
    }

    @Test
    public void givenFileOnReadingFromFileShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        long entries = employeePayrollService.readEmployeePayrollData(FILE_IO).size();
        assertEquals(3, entries);
    }

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DB_IO);
        assertEquals(2, employeePayrollData.size());
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DB_IO);
        employeePayrollService.updateEmployeeSalary("sowmya", 1250000.0);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("sowmya");
        Assert.assertTrue(result);
    }

    @Test
    public void givenResultOfEmployee_WhenWithInDateRange_ShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.getEmployeeDataWithinDateRange(
                DB_IO,
                LocalDate.of(2025,12,1), LocalDate.of(2025, 12, 31)
        );
        assertEquals(2, employeePayrollData.size());
    }

    @Test
    public void givenPayrollData_WhenAverageSalaryRetrievedByGender_ShouldReturnProperValue() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollData(DB_IO);
        Map<String, Double> averageSalaryByGender = employeePayrollService.readAverageSalaryByGender(DB_IO);
        Assert.assertTrue(averageSalaryByGender.get("M").equals(1000000.00) &&
                averageSalaryByGender.get("F").equals(1375000.00));
    }

    @Test
    public void givenPayrollData_WhenAdded_ShouldReflectInDB() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.addEmployeePayrollData(
                DB_IO,
                "kumar",
                "M",
                1150000.00,
                LocalDate.of(2025, 12, 4)
        );
        employeePayrollService.readEmployeePayrollData(DB_IO);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("kumar");
        Assert.assertTrue(result);
    }

}
