package com.zak.clane.article;

import com.zak.clane.authors.AuthorModel;
import com.zak.clane.authors.AuthorService;
import com.zak.clane.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;
    private AuthorService authorService;
    private JwtTokenUtil tokenUtil;

    @Autowired
    public ArticleController(ArticleService articleService, AuthorService authorService, JwtTokenUtil tokenUtil){
        this.articleService = articleService;
        this.authorService = authorService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * Add new Article
     * @param article {@link ArticleModel}
     * @return Returns the newly added article
     */
    @PostMapping("/article")
    @ResponseBody
    public ArticleModel createArticle (@RequestHeader (name="Authorization") String token, @RequestBody ArticleModel article){
        String emailFromToken = tokenUtil.getEmailFromToken(token.substring(7));
        AuthorModel authorModel = authorService.existsByEmail(emailFromToken);
        article.setAuthorId(authorModel.getId());
        return articleService.save(article);
    }

    @GetMapping("/articles")
    @ResponseBody
    public List<ArticleModel> getAllArticle(){
        return articleService.findAllArticles();
    }

    @GetMapping("/article")
    @ResponseBody
    public List<ArticleModel> getAllAuthorArticles(@RequestHeader (name="Authorization") String token){
        String emailFromToken = tokenUtil.getEmailFromToken(token.substring(7));
        AuthorModel authorModel = authorService.existsByEmail(emailFromToken);
        return articleService.fetchArticlesByAuthorId(authorModel.getId());
    }

    @PutMapping("/article")
    @ResponseBody
    public ArticleModel editArticle(@RequestBody ArticleModel webRequest){
        return  articleService.editArticle(webRequest);
    }

    @DeleteMapping("/article/{id}")
    @ResponseBody
    public void editArticle(@PathVariable String id){
        Long articleId = Long.parseLong(id);
        articleService.deleteArticle(articleId);
    }
}
