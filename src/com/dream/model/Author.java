package com.dream.model;

public class Author {

    private final String name;
    private final String affiliations;
    private final String email;
    private final String googleScholarAuthorUrl;

    public Author(String name, String affiliations, String email, String googleScholarAuthorUrl) {
        this.name = name;
        this.affiliations = affiliations;
        this.email = email;
        this.googleScholarAuthorUrl = googleScholarAuthorUrl;
    }

    public String getName() {
        return name;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public String getEmail() {
        return email;
    }

    public String getGoogleScholarAuthorUrl() {
        return googleScholarAuthorUrl;
    }
}