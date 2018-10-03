package com.kraftmatic.geodesgemstones.controller;

import com.kraftmatic.geodesgemstones.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.Status;

import java.util.List;

@Controller
public class SocialMediaController {

    @Autowired
    private TwitterService twitterService;

    @RequestMapping(path = "/blog", method = RequestMethod.GET)
    public String displaySocialMedia(Model model){

        List<Status> tweets = twitterService.readTweets();
        model.addAttribute("tweets", tweets);
        return "blog";

    }

}
