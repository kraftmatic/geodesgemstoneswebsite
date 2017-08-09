package com.kraftmatic.geodesgemstones.controller;

import com.kraftmatic.geodesgemstones.models.Article;
import com.kraftmatic.geodesgemstones.service.ArticleService;
import com.kraftmatic.geodesgemstones.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.TwitterException;

import java.io.IOException;

@Controller
public class MainController {

	@Autowired
    ArticleService articleService;

	@Autowired
    TwitterService twitterService;

	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}

	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("articles", articleService.retrieveLast10Articles());
		return "index";
	}

	@RequestMapping("/user/index")
	public String userIndex(Model model) {
		model.addAttribute("article", new Article());
		return "user/index";
	}


	@RequestMapping(path = "admin/submitArticle", method = RequestMethod.POST)
	public String articleSubmit(@ModelAttribute Article article){

        try {
            if (article.getImage() == null) {
                twitterService.postTweet(article.getContent());
            } else {
                twitterService.postImage(article);
            }
        } catch (TwitterException | IOException e) {
            e.printStackTrace();
        }
        articleService.saveArticle(article);
		return "user/index";
	}
	/**
	 *  Login Methods
	 */

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postLogin() {
		// TODO Enable form login with Spring Security (trigger error for now)
		return "redirect:/login-error";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

}

