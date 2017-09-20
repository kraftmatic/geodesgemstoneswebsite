package com.kraftmatic.geodesgemstones.controller;

import com.kraftmatic.geodesgemstones.models.Contact;
import com.kraftmatic.geodesgemstones.service.EmailService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String serveContactForm(Model model){

        model.addAttribute("form", new Contact());
        return "form";
    }

    @RequestMapping(path = "/contact/submitForm", method = RequestMethod.POST)
    public String submitContactForm(@ModelAttribute("form") Contact form, Model model){

        try {
            emailService.sendEmail(form);
            model.addAttribute("submitSuccess", true);
        } catch (UnirestException e) {
            model.addAttribute("submitSuccess", false);
            e.printStackTrace();
        }

        model.addAttribute("form", new Contact());
        return "form";
    }
}
