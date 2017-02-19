package com.kraftmatic.geodesgemstones.controller;

import com.kraftmatic.geodesgemstones.database.ArticleRepository;
import com.kraftmatic.geodesgemstones.models.Article;
import com.kraftmatic.geodesgemstones.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@Autowired
    ArticleService articleService;

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

