package graphql;

import org.json.JSONException;
import org.json.JSONObject;
import product.ProductList;

public class GraphQLQueryProductList implements GraphQLQuery<ProductList> {
  private GraphQLProductList gqlProductList;

  public GraphQLQueryProductList() {
    this.gqlProductList = new GraphQLProductList();
  }

  @Override
  public String getQuery() {
    return String.format(
        "query ProductList { products %s }", gqlProductList.getGraphQLDefinition());
  }

  @Override
  public ProductList parseJson(JSONObject json) throws JSONException {
    JSONObject data = json.getJSONObject("data");
    JSONObject products = data.getJSONObject("products");
    return gqlProductList.parseJson(products);
  }

  static void main() {
    IO.println(new GraphQLQueryProductList().getQuery());
  }
}
