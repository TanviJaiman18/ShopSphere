package com.shopsphere.util;
import java.sql.Connection;
public class DBTest {

    public static void main(String[] args) {
        try {
            Connection con = DBConnection.getConnection();

            if (con != null) {
                System.out.println("MySQL Connection Successful!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
    }
}

