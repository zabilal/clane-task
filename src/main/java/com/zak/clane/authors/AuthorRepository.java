package com.zak.clane.authors;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {

    AuthorModel findByEmail(String email);
}
