package com.kraftmatic.geodesgemstones.database;

import com.kraftmatic.geodesgemstones.models.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by nkraftor on 2/18/17.
 */

public interface ArticleRepository extends CrudRepository<Article, Long>{

    List<Article> findTop10BySubmitter(String submitter);

}
