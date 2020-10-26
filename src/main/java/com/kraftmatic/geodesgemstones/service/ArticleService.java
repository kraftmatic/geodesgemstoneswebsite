package com.kraftmatic.geodesgemstones.service;

import com.kraftmatic.geodesgemstones.models.Article;
import com.kraftmatic.geodesgemstones.models.Announcement;

import java.util.List;

/**
 * Created by nkraftor on 2/19/17.
 */
public interface ArticleService {

    public void saveArticle(Article article
    );

    public List<Article> retrieveLast10Articles();
    public List<Announcement> retrieveAnnouncements();

}
