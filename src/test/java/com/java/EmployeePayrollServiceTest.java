package com.java;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static com.java.IOService.FILE_IO;

public class EmployeePayrollServiceTest {
    @Test
    public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries(){
        EmployeePayrollData[] arrayOfEmployees= {
                new EmployeePayrollData("Tom", 1, 700000),
                new EmployeePayrollData("Anna", 2, 800000),
                new EmployeePayrollData("Ravi", 3, 900000)
            };
            EmployeePayRollServices employeePayRollServices;
            employeePayRollServices= new EmployeePayRollServices(Arrays.asList(arrayOfEmployees));
            employeePayRollServices.writeEmployeePayrollData(FILE_IO);
            employeePayRollServices.printData(FILE_IO);
            long entries = employeePayRollServices.countEntries(FILE_IO);
            Assert.assertEquals(3,entries);

        }
    }

