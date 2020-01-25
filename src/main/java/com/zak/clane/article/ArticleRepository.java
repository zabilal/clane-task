package com.zak.clane.article;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<ArticleModel, Long> {

    @Query("SELECT c FROM AuthorModel c LEFT JOIN ArticleModel n ON c.id = n.authorId")
    List<ArticleModel> fetchArticlesByAuthorId(Long id);

}
