package com.zak.clane.controller;

import com.zak.clane.article.ArticleModel;
import com.zak.clane.article.ArticleService;
import com.zak.clane.authors.Author;
//import com.zak.clane.config.JwtTokenUtil;
import com.zak.clane.authors.AuthorService;
import com.zak.clane.config.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
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
    @ApiOperation(value = "Add a new article")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Created the article"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/article")
    public ResponseEntity<ArticleModel> createArticle (@RequestHeader (name="Authorization") String token, @RequestBody ArticleModel article){
        String emailFromToken = tokenUtil.getEmailFromToken(token.substring(7));
        Author authorModel = authorService.existsByEmail(emailFromToken);
        article.setAuthorId(authorModel.getId());
        ArticleModel savedArticle = articleService.save(article);
        return ResponseEntity.ok().body(savedArticle);
    }

    @ApiOperation(value = "Get a list of all articles")
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleModel>> getAllArticle(){
        return ResponseEntity.ok(articleService.findAllArticles());
    }

    @ApiOperation(value = "Get a list of all articles by an author")
    @GetMapping("/article")
    public ResponseEntity<List<ArticleModel>> getAllAuthorArticles(@RequestHeader (name="Authorization") String token){
        String emailFromToken = tokenUtil.getEmailFromToken(token.substring(7));
        Author authorModel = authorService.existsByEmail(emailFromToken);
        List<ArticleModel> articleModels = articleService.fetchArticlesByAuthorId(authorModel.getId());
        return ResponseEntity.ok(articleModels);
    }

    @ApiOperation(value = "Edit an article")
    @PutMapping("/article")
    public ResponseEntity<ArticleModel> editArticle(@RequestBody ArticleModel webRequest){
        ArticleModel editArticle = articleService.editArticle(webRequest);
        return ResponseEntity.ok(editArticle);
    }

    @ApiOperation(value = "Delete an article")
    @DeleteMapping("/article/{id}")
    public Map<String, Boolean> editArticle(@PathVariable String id){
        Long articleId = Long.parseLong(id);
        articleService.deleteArticle(articleId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
