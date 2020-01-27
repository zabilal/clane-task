package com.zak.clane.authors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authors")
public class Author implements Serializable {

    @ApiModelProperty(notes = "The generated id for an author")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(notes = "The author's email")
    @Column(unique = true)
    private String email;

    @ApiModelProperty(notes = "The author's name")
    private String name;

    @ApiModelProperty(notes = "The author's password")
    private String password;

    @ApiModelProperty(notes = "The author's biography")
    private String bio;

    public Author() {
    }

    public Author(String email, String encodedPassword, String name, String bio) {
        this.email = email;
        this.password = encodedPassword;
        this.name = name;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
