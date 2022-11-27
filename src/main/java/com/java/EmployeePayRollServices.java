package com.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.java.IOService.FILE_IO;

public class EmployeePayRollServices {
    public static String PAYROLL_FILE_NAME = "payrollFile.txt";

    public EmployeePayRollServices(List<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    private List< EmployeePayrollData>employeePayrollList;
    public EmployeePayRollServices(ArrayList<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList=employeePayrollList;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Employee Pay roll Services");
        ArrayList<EmployeePayrollData>employeePayrollList=new ArrayList<EmployeePayrollData>();
        EmployeePayRollServices employeePayRollServices =new EmployeePayRollServices(employeePayrollList);
        Scanner sc=new Scanner(System.in);
        employeePayRollServices.readEmployeePayrollData(sc);
        employeePayRollServices.writeEmployeePayrollData(FILE_IO);
    }

    void writeEmployeePayrollData(IOService fileIo) {
        if(fileIo.equals(IOService.CONSOLE_IO))
             System.out.println("\nWriting employee payRoll data o console\n"+employeePayrollList);
        else if(fileIo.equals(FILE_IO))
            new EmployeePayrollFileIOService().writeData(employeePayrollList);
    }

    private void readEmployeePayrollData(Scanner sc) {
        System.out.println("Enter employee Name : ");
        String name=sc.next();
        System.out.println("Enter employee id : ");
        int id =sc.nextInt();
        System.out.println("Enter employee Salary : ");
        double salary=sc.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(name,id,salary));
    }

    public void printData(IOService fileIo){
        try {
            Files.lines(new File("payrollFile.txt").toPath())
                    .forEach(System.out::println);
        }catch (IOException e){}
    }

    public long countEntries(IOService fileIo){
        long entries=0;
        try {
            entries=Files.lines(new File("payrollFile.txt").toPath())
                    .count();
        }catch (IOException e){}
        return entries;
    }
}

