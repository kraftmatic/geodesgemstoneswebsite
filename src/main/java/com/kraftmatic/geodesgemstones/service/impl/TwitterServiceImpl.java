package com.kraftmatic.geodesgemstones.service.impl;

import com.kraftmatic.geodesgemstones.models.Article;
import com.kraftmatic.geodesgemstones.service.TwitterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class TwitterServiceImpl implements TwitterService{

    private Twitter twitter;

    public TwitterServiceImpl(@Value("${twitter.oauth.accesstoken}") String token,
                              @Value("${twitter.oauth.accesssecret}") String secret,
                              @Value("${twitter.oauth.appid}") String appId,
                              @Value("${twitter.oauth.appsecret}") String appSecret){
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(appId, appSecret);
        AccessToken accessToken = new AccessToken(token, secret);
        twitter.setOAuthAccessToken(accessToken);
    }

    @Override
    public void postTweet(String message) throws TwitterException{
        twitter.updateStatus(message);
    }

    @Override
    public void postImage(Article article) throws TwitterException, IOException {
        StatusUpdate update = new StatusUpdate(article.getContent());
        update.setMedia(convert(article.getImage()));
        twitter.updateStatus(update);
    }

    @Override
    public List<Status> readTweets() {
        List<Status> tweets = null;
        try {
            tweets = twitter.timelines().getHomeTimeline();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return tweets;
    }

    private File convert(MultipartFile file) throws IOException
    {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
