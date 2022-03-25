package com.example.freemoneynoscam.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EmailRepository {
    static Statement stmt;
    static ResultSet rs;
    static String sqlString;
    static Connection con;

    public static String fetchSingleEmail(){
        String mail = "";
        try {
            connect();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            sqlString = "SELECT * FROM emails LIMIT 1";
            rs = stmt.executeQuery(sqlString);

            System.out.println("Displaying one fetched email:");
            while (rs.next()) {
                String col2 = rs.getString("emails");
                mail = col2;
                System.out.println("email = " + col2);
            }

        } catch (Exception e) {
            System.out.println("Der skete en fejl");
            System.out.println(e);
        }
        return mail;
    }
    public static ArrayList<String> fetch4Emails(){
        ArrayList<String> emails = new ArrayList<String>();

        try {
            connect();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            sqlString = "SELECT * FROM emails LIMIT 4";
            rs = stmt.executeQuery(sqlString);

            System.out.println("Displaying one fetched email:");
            while (rs.next()) {
                String col2 = rs.getString("emails");
                emails.add(col2);
            }

        } catch (Exception e) {
            System.out.println("Der skete en fejl");
            System.out.println(e);
        }
        return emails;
    }
    public static void connect() {
        String url = "jdbc:mysql://localhost:3306/free_money";

        try {
            con = DriverManager.getConnection(url, "root", "Fca52uym");
        } catch (Exception e) {
            System.out.println("Shit pommes frites");
        }
        System.out.println("URL: " + url);
        System.out.println("Vi har en connection");
    }
}


