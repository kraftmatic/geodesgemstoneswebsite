package com.kraftmatic.geodesgemstones.service;

import com.kraftmatic.geodesgemstones.models.Article;
import com.kraftmatic.geodesgemstones.models.Announcement;

import java.util.List;

/**
 * Created by nkraftor on 2/19/17.
 */
public interface ArticleService {

    void saveArticle(Article article);
    void saveAnnoucnement(Announcement announcement);
    List<Article> retrieveLast10Articles();
    List<Announcement> retrieveAnnouncements();

}
