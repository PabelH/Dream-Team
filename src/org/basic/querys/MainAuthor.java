package org.basic.querys;


import org.json.JSONObject;
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

public class MainAuthor {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
         final String DB_URL = "jdbc:mysql://localhost:3306/authorsdata";
         final String USER = "root";
         final String PASS = "";
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://serpapi.com/search.json?engine=google_scholar_author";
        String apiKey = "abc123"; //your apiKey Ex: 123abc
        String author_id = "qc6CJjYAAAAJ"; //Albert Einstein
        String hl = "en";
        int numResults = 10;

        // https://serpapi.com/search.json?engine=google_scholar_author&author_id=LSsXyncAAAAJ&hl=en&api_key=123abc
        String requestUrl = url + "&author_id=" + author_id + "&hl=" + hl + "&num=" + numResults + "&api_key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(requestUrl)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // parse the JSON response

        String json_data = response.body();

        JSONObject data = new JSONObject(json_data);

        String name = data.getJSONObject("author").getString("name");
        String affiliations = data.getJSONObject("author").getString("affiliations");
        String email = data.getJSONObject("author").getString("email");
        String google_scholar_author_url = data.getJSONObject("search_metadata").getString("google_scholar_author_url");

        System.out.println(name);
        System.out.println(affiliations);
        System.out.println(email);
        System.out.println(google_scholar_author_url);



            // Open a connection
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()
            ) {
                // Execute a query
                // authors (author_id, articles, affiliations,email)
                System.out.println("Inserting records into the table...");
                String sql = "INSERT INTO authors (nombre, afiliaciones, email, url) VALUES ('"+name+"', '"+affiliations+"', '"+email+"', '"+google_scholar_author_url+"')";
                stmt.executeUpdate(sql);

                System.out.println("Inserted records into the table...");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

