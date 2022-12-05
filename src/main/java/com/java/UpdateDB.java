package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateDB {
    public boolean update()
    {
        try
        {
            // create a java mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/payrollservices";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "aswathi123");

            // create the java mysql update preparedstatement
            String query = "update employee_payroll set basicPay= ? where name=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt  (1, 3000000);
            preparedStmt.setString(2, "Terrisa");

            // execute the java preparedstatement
            preparedStmt.executeUpdate();

            conn.close();
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public  void retrievingDataByName(String name){

    }
}

