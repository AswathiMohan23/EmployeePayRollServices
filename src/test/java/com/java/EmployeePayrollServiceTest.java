package com.java;

import org.junit.Assert;
import org.junit.Test;


import java.sql.SQLException;
import java.util.Arrays;

import static com.java.IOService.FILE_IO;
import static com.java.JdbcConnection.jdbcConnection;

public class EmployeePayrollServiceTest {
    public JdbcConnection jdbcConnection=new JdbcConnection();

    @Test
    public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        EmployeePayrollData[] arrayOfEmployees = {
                new EmployeePayrollData("Tom", 1, 700000),
                new EmployeePayrollData("Anna", 2, 800000),
                new EmployeePayrollData("Ravi", 3, 900000)
        };
        EmployeePayRollServices employeePayRollServices;
        employeePayRollServices = new EmployeePayRollServices(Arrays.asList(arrayOfEmployees));
        employeePayRollServices.writeEmployeePayrollData(FILE_IO);
        employeePayRollServices.printData(FILE_IO);
        long entries = employeePayRollServices.countEntries(FILE_IO);
        Assert.assertEquals(3, entries);

    }

    @Test
    public void givenNewSalaryForEmployeeWhenUpdated_ShouldSyncWithDB() throws SQLException, EmployeePayRollException {
        String query = "SELECT * FROM employee_payroll";
        Assert.assertTrue(jdbcConnection(query));
    }
    @Test
    public void retrievingDataByName() throws SQLException, EmployeePayRollException {
        String query="Select * from employee_payroll where name ='Tom'";
        Assert.assertTrue(jdbcConnection(query));
    }
    @Test
    public void retrievingEmployeeDataByDate() throws SQLException, EmployeePayRollException {
        String query="Select * from employee_payroll where start ='2013-12-09'";
        Assert.assertTrue(jdbcConnection(query));
    }
    @Test
    public void retrievingEmployeeDataWithInGivenRangeOfDate() throws SQLException, EmployeePayRollException {
        String query="SELECT * FROM employee_payroll WHERE start BETWEEN CAST('2016-09-22' AS DATE) AND DATE(NOW())";
        Assert.assertTrue(jdbcConnection(query));
    }
    @Test
    public void abilityToFindSumOfSalaryOfMaleEmployees() throws SQLException, EmployeePayRollException {
        CalculatingAverageAndSum updateDb=new CalculatingAverageAndSum();
        String query="SELECT SUM(salary) AS totalSalary FROM employee_payroll where gender ='M'";
        updateDb.calculateTheValues(query);
    }
    @Test
    public void abilityToFindSumOfSalaryOfFemaleEmployees() throws SQLException, EmployeePayRollException {
        CalculatingAverageAndSum updateDb=new CalculatingAverageAndSum();
        String query="SELECT SUM(salary) AS totalSalary FROM employee_payroll where gender ='F'";
        updateDb.calculateTheValues(query);
    }
}

