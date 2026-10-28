package com.ContactManagement;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        Connection con = DBConnection.getConnection();

        if (con != null) {
            System.out.println("MySQL Connected Successfully!");
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("MySQL Connection Failed!");
        }
    }
}