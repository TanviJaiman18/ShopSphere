package com.shopsphere.util;
import java.sql.*;
public final class DB {
 private static final String URL = System.getenv().getOrDefault("SHOPSPHERE_DB_URL","jdbc:mysql://localhost:3306/shopsphere?useSSL=false&serverTimezone=Asia/Kolkata&allowPublicKeyRetrieval=true");
 private static final String USER = System.getenv().getOrDefault("SHOPSPHERE_DB_USER","root");
 private static final String PASS = System.getenv().getOrDefault("SHOPSPHERE_DB_PASSWORD","");
 private DB(){}
 public static Connection getConnection() throws SQLException { return DriverManager.getConnection(URL,USER,PASS); }
}
