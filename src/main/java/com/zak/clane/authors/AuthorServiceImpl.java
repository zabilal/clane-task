package com.zak.clane.authors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AuthorModel save(AuthorModel authorModel) {
        return authorRepository.save(authorModel);
    }

    @Override
    public List<AuthorModel> fetchAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public AuthorModel findOne(Long id) {
        return authorRepository.getOne(id);
    }

    @Override
    public AuthorModel existsByEmail(String email) {
        return authorRepository.findByEmail(email);
    }


}
