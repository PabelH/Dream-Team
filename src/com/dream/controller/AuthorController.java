package com.dream.controller;

import org.json.JSONObject;
import com.dream.model.Author;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorController {

    private final String authorId;
    private final String language;

    public AuthorController(String authorId, String language) {
        this.authorId = authorId;
        this.language = language;
    }

    public void insertAuthor() throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();

        String url = "https://serpapi.com/search.json?engine=google_scholar_author";
        String apiKey = "abc123"; // your apiKey Ex: 123abc
        int numResults = 5;

        // Construct the request URL
        String requestUrl = url + "&author_id=" + authorId + "&hl=" + language + "&num=" + numResults + "&api_key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(requestUrl)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse the JSON response
        String json_data = response.body();
        JSONObject data = new JSONObject(json_data);

        String name = data.getJSONObject("author").getString("name");
        String affiliations = data.getJSONObject("author").getString("affiliations");
        String email = data.getJSONObject("author").getString("email");
        String googleScholarAuthorUrl = data.getJSONObject("search_metadata").getString("google_scholar_author_url");

        Author author = new Author(name, affiliations, email, googleScholarAuthorUrl);

        // Insert author into the database
        insertAuthorIntoDatabase(author);
    }

    private void insertAuthorIntoDatabase(Author author) {
        String DB_URL = "jdbc:mysql://localhost:3306/authorsdata";
        String USER = "root";
        String PASS = "";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            // Execute the SQL query
            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO authors (nombre, afiliaciones, email, url) VALUES ('" +
                    author.getName() + "', '" +
                    author.getAffiliations() + "', '" +
                    author.getEmail() + "', '" +
                    author.getGoogleScholarAuthorUrl() + "')";
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


