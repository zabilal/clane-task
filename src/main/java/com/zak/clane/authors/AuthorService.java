package com.zak.clane.authors;

import java.util.List;

public interface AuthorService {

    AuthorModel save(AuthorModel authorModel);
    List<AuthorModel> fetchAllAuthors();
    AuthorModel findOne(Long id);
    AuthorModel existsByEmail(String email);
}
