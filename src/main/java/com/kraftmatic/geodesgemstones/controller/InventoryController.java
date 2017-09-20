package com.kraftmatic.geodesgemstones.controller;

import com.kraftmatic.geodesgemstones.database.PhotoRepository;
import com.kraftmatic.geodesgemstones.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class InventoryController {

    @Autowired
    private PhotoRepository repository;

    @RequestMapping(path = "/database", method = RequestMethod.GET)
    public String fetchDatabaseItems(Model model){

        List<Photo> photos = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
        model.addAttribute("photos", photos);
        return "photos";
    }
}
