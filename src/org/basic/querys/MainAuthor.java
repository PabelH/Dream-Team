package org.basic.querys;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainAuthor {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://serpapi.com/search.json?engine=google_scholar_author";
        String apiKey = "123abc"; //your apiKey Ex: 123abc
        String author_id = "qc6CJjYAAAAJ"; //Albert Einstein
        String hl = "en";
        int numResults = 10;

        // https://serpapi.com/search.json?engine=google_scholar_author&author_id=LSsXyncAAAAJ&hl=en&api_key=123abc
        String requestUrl = url + "&author_id=" + author_id + "&hl=" + hl + "&num=" + numResults + "&api_key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(requestUrl)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}