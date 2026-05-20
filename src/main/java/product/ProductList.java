package product;

import graphql.GraphQLClient;
import graphql.GraphQLQueryProductList;
import java.io.IOException;
import java.util.List;
import org.json.JSONException;

public class ProductList {
  public List<Product> products;

  public ProductList(List<Product> products) {
    this.products = products;
  }

  public static ProductList retrieveProducts(String url) {
    GraphQLClient client = new GraphQLClient(url);
    GraphQLQueryProductList query = new GraphQLQueryProductList();
    try {
      return client.executeQuery(query);
    } catch (IOException | InterruptedException | JSONException e) {
      // TODO: Handle this exception properly.
      throw new RuntimeException(e);
    }
  }
  }
}
