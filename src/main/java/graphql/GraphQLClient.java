package graphql;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

/// A GraphQL client that sends an HTTP POST request to the given endpoint and returns the result
/// based on the well-typed query class.
public class GraphQLClient {
  private String url;
  private HttpClient client;

  public GraphQLClient(String url) {
    this.url = url;
    this.client = HttpClient.newHttpClient();
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public <T> T executeQuery(GraphQLQuery<T> query)
      throws IOException, InterruptedException, JSONException {
    String payload = String.format("{\"query\": \"%s\"}", query.getQuery());
    // Perform the HTTP POST request.
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(getUrl()))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(payload))
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    // If the request failed, the body is still valid, but the data won't be there, hence this needs
    // to bail.
    if (response.statusCode() != 200) {
      // TODO: This should be a concrete exception, but there is no built-in one for this.
      throw new RuntimeException(String.format("HTTP request failed: %s", response.body()));
    }
    JSONObject jsonResponse = new JSONObject(response.body());
    // Parse the response into the corresponding Java class.
    return query.parseJson(jsonResponse);
  }
}
