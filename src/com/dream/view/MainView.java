package com.dream.view;



import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import com.dream.controller.AuthorController;
import com.dream.model.Author;

public class MainView {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter author ID: ");
        String author_id = scanner.nextLine();
        System.out.print("Enter API Key: ");
        String apiKey = scanner.nextLine();
        System.out.print("Enter language (e.g. en, es, fr): ");
        String hl = scanner.nextLine();
        System.out.print("Enter number of results: ");
        int numResults = scanner.nextInt();

        String url = "https://serpapi.com/search.json?engine=google_scholar_author";

        // https://serpapi.com/search.json?engine=google_scholar_author&author_id=LSsXyncAAAAJ&hl=en&api_key=123abc
        String searchUrl = url + "&hl=" + hl + "&api_key=" + apiKey;

        Author author = new Author(author_id, apiKey, hl, numResults, searchUrl);
        AuthorController controller = new AuthorController(author);

        String searchResult = controller.performSearch();
        System.out.println(searchResult);
    }
}