package com.java;

import java.sql.*;
import java.util.Enumeration;

public class JdbcConnection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String DB_URL = "jdbc:mysql://localhost:3306/payrollservices"; // give database name
        String USER = "root";
        String PASS = "aswathi123";
        Connection con ;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
            System.out.println("Driver loaded");
        }catch (ClassNotFoundException e){
            throw new IllegalStateException("Cannot find driver in the classpath",e);
        }
        listDrivers();
        try{
            System.out.println("connecting to database : "+DB_URL);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection is successful "+con);
        }catch (Exception e){
            e.printStackTrace();
        }
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