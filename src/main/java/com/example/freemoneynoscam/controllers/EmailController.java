package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.services.EmailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {
    @GetMapping("/list")
    public String emailList(Model model) {
        model.addAttribute("mail", EmailRepository.fetchSingleEmail());
        model.addAttribute("mails", EmailRepository.fetch4Emails());
        return "email_list";
    }


    }

