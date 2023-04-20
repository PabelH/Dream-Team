package com.dream.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import com.dream.model.Author;

public class AuthorController {
    private Author author;

    public AuthorController(Author author) {
        this.author = author;
    }

    public String performSearch() throws URISyntaxException, IOException, InterruptedException {
        return author.performSearch();
    }
}
