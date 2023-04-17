import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        //System.out.println("Hello world!");
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://serpapi.com/search.json?engine=google_scholar";
        String apiKey = "123abc"; //your apiKey Ex: 123abc
        String query = "coffee";
        String hl = "en";
        int numResults = 10;
        //https://serpapi.com/search.json?engine=google_scholar&q=brain&hl=en&num=10&api_key=123abc
        String requestUrl = url + "&q=" + query + "&hl=" + hl + "&num=" + numResults + "&api_key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(requestUrl)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}