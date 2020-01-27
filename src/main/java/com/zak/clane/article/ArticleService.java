package com.zak.clane.article;

import com.zak.clane.article.ArticleModel;

import java.util.List;

public interface ArticleService {

    ArticleModel save(ArticleModel articleModel);
    List<ArticleModel> findAllArticles();
    List<ArticleModel> fetchArticlesByAuthorId(Long id);
    ArticleModel editArticle(ArticleModel articleModel);
    void deleteArticle(Long id);
}
