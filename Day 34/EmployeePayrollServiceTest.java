package com;

import com.javapractice.iostream.EmployeePayrollData;
import com.javapractice.iostream.EmployeePayrollService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.javapractice.iostream.EmployeePayrollService.IOService.*;

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
        Assert.assertEquals(3, entries);
    }

    @Test
    public void givenFileOnReadingFromFileShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        long entries = employeePayrollService.readEmployeePayrollData(FILE_IO).size();
        Assert.assertEquals(3, entries);
    }

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DB_IO);
        Assert.assertEquals(2, employeePayrollData.size());
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
                LocalDate.of(2025,12,1), LocalDate.of(2025, 12, 31)
        );
        Assert.assertEquals(2, employeePayrollData.size());
    }
}
