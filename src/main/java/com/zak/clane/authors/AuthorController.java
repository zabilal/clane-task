package com.zak.clane.authors;

import com.zak.clane.errors.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    /**
     * Add new Author, this controller
     * is a public controller not needimg
     * authentication
     *
     * @param author {@link AuthorModel}
     * @return Returns the newly added author
     */
    @PostMapping("/author")
    @ResponseBody
    public ResponseEntity createAuthor (@RequestBody AuthorModel author){

        String email = author.getEmail();
        AuthorModel auth = authorService.existsByEmail(email);
        if (auth != null){
            throw new ValidationException("Email already exist");
        }

        String password = author.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);

        author.setPassword(encodedPassword);
        AuthorModel model = authorService.save(author);
        model.setPassword("");
        return ResponseEntity.ok(model);
    }

    /**
     * getAllAuthor controller retrieves all
     * authors and returns it as a list. Its a
     * public api not needing authentication
     *
     * @return List of authors
     */
    @GetMapping("/authors")
    @ResponseBody
    public List<AuthorModel> getAllAuthor(){
        return authorService.fetchAllAuthors();
    }
}
