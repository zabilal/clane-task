package com.zak.clane.authors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author save(Author authorModel) {
        return authorRepository.save(authorModel);
    }

    @Override
    public List<Author> fetchAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author existsByEmail(String email) {
        return authorRepository.findByEmail(email);
    }


}
