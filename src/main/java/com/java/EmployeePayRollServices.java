package com.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayRollServices {
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
        employeePayRollServices.writeEmployeePayrollData();
    }

    private void writeEmployeePayrollData() {
        System.out.println("\nWriting employee payRoll data o console\n"+employeePayrollList);
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
}

