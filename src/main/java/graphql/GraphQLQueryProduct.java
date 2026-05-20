package graphql;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import product.Product;
import product.ProductList;

public class GraphQLQueryProduct implements GraphQLQuery<ProductList> {
  private GraphQLProduct gqlProduct;
  private int id;

  public GraphQLQueryProduct(int id) {
    this.gqlProduct = new GraphQLProduct();
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getQuery() {
    return String.format(
        "query GetProduct { product(id: %d) %s }", id, gqlProduct.getGraphQLDefinition());
  }

  // This returns a ProductList as there are multiple variants and this simplifies the usage, as the
  // CLI only needs to handle one data type to display.
  @Override
  public ProductList parseJson(JSONObject json) throws JSONException {
    JSONObject data = json.getJSONObject("data");
    JSONObject jsonProduct = data.getJSONObject("product");
    List<Product> products = gqlProduct.parseJson(jsonProduct);
    return new ProductList(products);
  }
}
