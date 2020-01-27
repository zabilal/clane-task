package com.zak.clane.authors;

import java.util.List;

public interface AuthorService {

    Author save(Author authorModel);
    List<Author> fetchAllAuthor();
    Author existsByEmail(String email);
}
