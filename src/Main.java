import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://serpapi.com/search.json?engine=google_scholar";
        String apiKey = "abc123"; //your apiKey Ex: 123abc
        String query = "coffee";
        String hl = "en";
        int numResults = 10;
        String author = "Cano";
        String journal = "Elsevier";

        // if a value is provided for author
        if (args.length > 0) {
            author = "&author=" + args[0];
        }

        // if a value is provided for the journal
        if (args.length > 1) {
            journal = "&journal=" + args[1];
        }

        // build the query string
        // Basic query
        // https://serpapi.com/search.json?engine=google_scholar&q=brain&hl=en&num=10&api_key=123abc
        String requestUrl = url + "&q=" + query + "&hl=" + hl + "&num=" + numResults + author + journal + "&api_key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(requestUrl)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}