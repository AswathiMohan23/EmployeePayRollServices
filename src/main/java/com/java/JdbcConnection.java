package com.java;

import java.sql.*;
import java.util.Enumeration;

public class JdbcConnection {
    public static void main(String[] args) throws  SQLException, EmployeePayRollException {

        String DB_URL = "jdbc:mysql://localhost:3306/payrollservices"; // give database name
        String USER = "root";
        String PASS = "aswathi123";
        String query = "SELECT id,name,gender,Department,Address,phnNo,salary,BasicPay,Tax,TaxPayable,start FROM employee_payroll";
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
            System.out.println("Driver loaded");
        }catch (ClassNotFoundException e){
            throw new EmployeePayRollException();
        }
        listDrivers();
        try{
            System.out.println("connecting to database : "+DB_URL);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection is successful "+con);
        }catch (Exception e){
            e.printStackTrace();
        }
       /* System.out.println(con);
        System.out.println("Connection Established successfully");*/
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query); // Execute query
        while (rs.next()) {
            System.out.println("\t\t\t\tid:" + rs.getInt("id"));
            System.out.println("name:" + rs.getString("name"));
            System.out.println("gender:" + rs.getString("gender"));
            System.out.println("Department:" + rs.getString("Department"));
            System.out.println("Address:" + rs.getString("Address"));
            System.out.println("PhnNo:" + rs.getString("PhnNo"));
            System.out.println("salary:" + rs.getString("salary"));
            System.out.println("BasicPay:" + rs.getString("BasicPay"));
            System.out.println("Tax:" + rs.getString("Tax"));
            System.out.println("TaxPayable:" + rs.getString("TaxPayable"));
            System.out.println("start:" + rs.getString("start"));
            System.out.println();
        }
        st.close();
        con.close();
    }

    private static void listDrivers() {
        Enumeration<Driver>driverList=DriverManager.getDrivers();
        while
        (driverList.hasMoreElements()){
            Driver driverClass=(Driver) driverList.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }
    }
}