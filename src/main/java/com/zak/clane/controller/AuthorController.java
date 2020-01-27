package com.zak.clane.controller;

import com.zak.clane.authors.Author;
import com.zak.clane.authors.AuthorService;
import com.zak.clane.errors.ValidationException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    /**
     * Add new Author, this controller
     * is a public controller not needing
     * authentication
     *
     * @param author {@link Author}
     * @return Returns the newly added author
     */
    @ApiOperation(value = "Add an Author")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created an Author"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor (@RequestBody Author author){

        String email = author.getEmail();
        if (!email.contains("@")){
            throw new ValidationException("Invalid Email format");
        }
        Author auth = authorService.existsByEmail(email);
        if (auth != null){
            throw new ValidationException("Email already exist");
        }

        String password = author.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);

        author.setPassword(encodedPassword);
        Author model = authorService.save(author);
        return ResponseEntity.ok(model);
    }

    /**
     * getAllAuthor controller retrieves all
     * authors and returns it as a list. Its a
     * public api not needing authentication
     *
     * @return List of authors
     */
    @ApiOperation(value = "Fetch a list of all Authors")
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthor(){
        return ResponseEntity.ok(authorService.fetchAllAuthor());
    }
}
