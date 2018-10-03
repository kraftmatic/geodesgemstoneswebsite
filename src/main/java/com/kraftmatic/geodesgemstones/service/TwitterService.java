package com.kraftmatic.geodesgemstones.service;

import com.kraftmatic.geodesgemstones.models.Article;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.List;

public interface TwitterService {

    void postTweet(String message) throws TwitterException;
    void postImage(Article article) throws TwitterException, IOException;

    List<Status> readTweets();
}
