package com.kraftmatic.geodesgemstones.service.impl;

import com.kraftmatic.geodesgemstones.database.ArticleRepository;
import com.kraftmatic.geodesgemstones.models.Article;
import com.kraftmatic.geodesgemstones.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nkraftor on 2/19/17.
 */

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public List<Article> retrieveLast10Articles() {
        return articleRepository.findTop10BySubmitter("test");
    }
}