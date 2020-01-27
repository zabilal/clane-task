package com.zak.clane.authors;

import java.util.List;

public interface AuthorService {

    Author save(Author authorModel);
    List<Author> fetchAllAuthors();
    Author existsByEmail(String email);
}
