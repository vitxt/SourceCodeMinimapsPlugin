package requests;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class PostImageRequest {
    public static HttpResponse<String> uploadImage(String title, String base64Image) throws Exception {
        String url = "";
        String jsonBody = String.format(
                "{\"title\": \"%s\", \"image\": \"%s\"}",
                title.replace("\"", "\\\""),
                base64Image
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .build();

        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
