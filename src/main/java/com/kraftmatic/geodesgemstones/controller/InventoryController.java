package com.kraftmatic.geodesgemstones.controller;

import com.kraftmatic.geodesgemstones.database.PhotoRepository;
import com.kraftmatic.geodesgemstones.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private PhotoRepository repository;

    @RequestMapping(path = "/database", method = RequestMethod.GET)
    public String fetchDatabaseItems(@PageableDefault(size = 8, sort = {"name"}) Pageable pageable,
                                     @RequestParam(name = "query", required = false) String query,
                                     Model model){
        if (query != null){
            List<Photo> photos = repository.findPhotosByNameContains(query);
            Page<Photo> pagePhotos = new PageImpl<Photo>(photos, pageable, photos.size());
            model.addAttribute("photos", pagePhotos);
        } else {
            Page<Photo> photos = repository.findAll(pageable);
            model.addAttribute("photos", photos);
        }

        return "photos";
    }
    @RequestMapping(path = "/database/{query}", method = RequestMethod.GET)
    public String queryDatabaseItems(@PathVariable("query") String query,
                                     @PageableDefault(size = 8, sort = {"name"}) Pageable pageable,
                                     Model model){
        List<Photo> photos = repository.findPhotosByNameContains(query);
        Page<Photo> pagePhotos = new PageImpl<Photo>(photos, pageable, photos.size());
        model.addAttribute("photos", pagePhotos);
        return "photos";
    }
}
