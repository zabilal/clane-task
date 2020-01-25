package com.zak.clane.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "articleService")
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public ArticleModel save(ArticleModel articleModel) {
        return articleRepository.save(articleModel);
    }

    /**
     * This controller return all published articles
     * Its a public api not requiring authentication
     *
     * @return All published Articles
     */
    @Override
    public List<ArticleModel> findAllArticles() {
        Iterable<ArticleModel> all = articleRepository.findAll();
        List articles = new ArrayList<ArticleModel>();
        all.forEach(e -> articles.add(e));
        
        return articles;
    }

    /**
     * Controller for fetching articles
     * by authors after authentication
     *
     * @param id
     * @return List of articles
     */
    @Override
    public List<ArticleModel> fetchArticlesByAuthorId(Long id) {
        Iterable<ArticleModel> allById = articleRepository.fetchArticlesByAuthorId(id);
        List articles = new ArrayList<ArticleModel>();
        allById.forEach(e -> articles.add(e));

        return articles;
    }

    /**
     * Authors can edit their Articles
     * after authentication using this
     * controller
     *
     * @param articleModel
     * @return
     */
    @Override
    public ArticleModel editArticle(ArticleModel articleModel) {
        ArticleModel model = articleRepository.findById(articleModel.getId()).get();
        model.setTitle(articleModel.getTitle());
        model.setContent(articleModel.getContent());
        return articleRepository.save(articleModel);
    }

    /**
     * Authors can delete their Articles
     * after authentication using this
     * controller
     *
     * @param id
     */
    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
