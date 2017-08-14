package com.kraftmatic.geodesgemstones.service;

import com.kraftmatic.geodesgemstones.models.Article;
import twitter4j.TwitterException;

import java.io.IOException;

public interface TwitterService {

    void postTweet(String message) throws TwitterException;
    void postImage(Article article) throws TwitterException, IOException;
}
