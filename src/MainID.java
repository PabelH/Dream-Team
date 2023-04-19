import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainID {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://serpapi.com/search.json?engine=google_scholar_profiles";
        String apiKey = "123abc"; //your apiKey Ex: 123abc
        String mauthors = "albert";
        String hl = "en";
        int numResults = 10;





        // https://serpapi.com/search.json?engine=google_scholar_profiles&mauthors=Albert&hl=en&api_key=123abc

        String requestUrl = url + "&mauthors=" + mauthors + "&hl=" + hl + "&num=" + numResults + "&api_key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(requestUrl)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
