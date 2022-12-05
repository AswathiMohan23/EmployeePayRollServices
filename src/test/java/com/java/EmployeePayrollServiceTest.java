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
    public void givenNewSalaryForEmployeeWhenUpdated_ShouldSyncWithDB() {
        UpdateDB updateDB=new UpdateDB();
        boolean result=updateDB.update();
        Assert.assertTrue(result);
    }
    @Test
    public void retrievingDataByName() throws SQLException, EmployeePayRollException {
        String query="Select * from employee_payroll where name ='Tom'";
        Assert.assertTrue(jdbcConnection(query));
    }
}

