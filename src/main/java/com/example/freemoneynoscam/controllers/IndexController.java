package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.services.ValidateEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

@Controller
public class IndexController {
    static Statement stmt;
    static ResultSet rs;
    static String sqlString;
    static Connection con;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/failure")
    public String failure() {
        return "failure";
    }

    @PostMapping("/test")
    public String test(WebRequest dataFromForm) {
        ValidateEmailService validator = new ValidateEmailService();
        System.out.println(validator.isEmailValid(dataFromForm.getParameter("email")));
        System.out.println(dataFromForm.getParameter("email"));
        if (validator.isEmailValid(dataFromForm.getParameter("email")) == false) {

            return "redirect:/failure";

        } else {
            connect();
            instert(dataFromForm.getParameter("email"));
        }
        return "redirect:/";
    }
    public static void instert(String mail) {


        try {
            stmt = con.createStatement();

            sqlString = "Insert INTO emails" +
                    "(emails) VALUES ('" + mail + "')";
            stmt.executeUpdate(sqlString);

        } catch (Exception e) {
            e.printStackTrace();
        }
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


