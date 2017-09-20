package com.kraftmatic.geodesgemstones.controller;

import com.kraftmatic.geodesgemstones.models.Article;
import com.kraftmatic.geodesgemstones.models.Photo;
import com.kraftmatic.geodesgemstones.models.PhotoSubmission;
import com.kraftmatic.geodesgemstones.service.ArticleService;
import com.kraftmatic.geodesgemstones.service.ImageService;
import com.kraftmatic.geodesgemstones.service.TwitterService;
import com.kraftmatic.geodesgemstones.util.TokenHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import twitter4j.TwitterException;

import java.io.IOException;

@Controller
public class MainController {

	@Autowired
    ArticleService articleService;

	@Autowired
    TwitterService twitterService;

	@Autowired
	ImageService imageService;

	@Autowired
	TokenHolder tokenHolder;

	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}

	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("articles", articleService.retrieveLast10Articles());
		return "index";
	}

	@RequestMapping("/admin")
	public String userIndex(Model model) {

		if (StringUtils.isBlank(tokenHolder.getAccessToken())){
			return "redirect:" + "http://api.imgur.com/oauth2/authorize?client_id=7872643312136fd&response_type=token";
		}

		model.addAttribute("article", new Article());
		model.addAttribute("photoSubmit", new PhotoSubmission());
		return "admin";
	}

	@RequestMapping("/user/index/token")
	public String userIndexToken(Model model,
                                 @RequestParam("access_token") String accessToken,
                                 @RequestParam("refresh_token") String refreshToken) {

		captureTokens(accessToken, refreshToken);
        model.addAttribute("article", new Article());
        model.addAttribute("photoSubmit", new PhotoSubmission());
        return "admin";
	}

	@RequestMapping("/token-capture")
    public String captureToken(){
	    return "token-capture";
    }


	@RequestMapping(path = "admin/submitArticle", method = RequestMethod.POST)
	public String articleSubmit(@ModelAttribute("article") Article article){

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


		return "admin";
	}

	@RequestMapping(path = "admin/submitPhoto", method = RequestMethod.POST)
	public String photoSubmit( @ModelAttribute("photoSubmit")PhotoSubmission photoSubmit, Model model){
		try {
			imageService.storeImage(photoSubmit);
            model.addAttribute("submitSuccess", true);
		} catch (IOException e) {
            model.addAttribute("submitSuccess", false);
		}
        model.addAttribute("article", new Article());
        model.addAttribute("photoSubmit", new PhotoSubmission());
        return "admin";
	}

	private void captureTokens(String accessToken, String refreshToken) {
		if (StringUtils.isNotBlank(accessToken) || StringUtils.isNotBlank(refreshToken)){
			tokenHolder.setAccessToken(accessToken);
			tokenHolder.setRefreshToken(refreshToken);
		}
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

