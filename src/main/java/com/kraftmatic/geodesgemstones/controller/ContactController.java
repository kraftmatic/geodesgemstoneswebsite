package com.kraftmatic.geodesgemstones.controller;

import com.kraftmatic.geodesgemstones.models.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {

    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String serveContactForm(Model model){

        model.addAttribute("form", new Contact());
        return "form";
    }

    @RequestMapping(path = "/contact/submitForm", method = RequestMethod.POST)
    public String submitContactForm(@ModelAttribute("form") Contact form, Model model){

        model.addAttribute("submitSuccess", true);
        model.addAttribute("form", new Contact());
        return "form";
    }
}
