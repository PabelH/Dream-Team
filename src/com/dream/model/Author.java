package com.dream.model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Author {
    private String id;
    private String apiKey;
    private String hl;
    private int numResults;
    private String searchUrl;

    public Author(String id, String apiKey, String hl, int numResults, String searchUrl) {
        this.id = id;
        this.apiKey = apiKey;
        this.hl = hl;
        this.numResults = numResults;
        this.searchUrl = searchUrl;
    }

    public String getId() {
        return id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getHl() {
        return hl;
    }

    public int getNumResults() {
        return numResults;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public String performSearch() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String requestUrl = searchUrl + "&author_id=" + id + "&hl=" + hl + "&num=" + numResults + "&api_key=" + apiKey;
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(requestUrl)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}