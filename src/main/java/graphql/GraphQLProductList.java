package graphql;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import product.Product;
import product.ProductList;

public class GraphQLProductList implements GraphQLObject<ProductList> {
  private GraphQLProduct gqlProduct;

  public GraphQLProductList() {
    gqlProduct = new GraphQLProduct();
  }

  @Override
  public String getGraphQLDefinition() {
    return String.format("{ items %s }", gqlProduct.getGraphQLDefinition());
  }

  @Override
  public ProductList parseJson(JSONObject json) throws JSONException {
    JSONArray items = json.getJSONArray("items");
    List<Product> products = new ArrayList<>();
    for (int i = 0; i < items.length(); i++) {
      JSONObject item = items.getJSONObject(i);
      List<Product> productsOfItem = gqlProduct.parseJson(item);
      // The products are flattened, since each variant is considered a separate product
      // in the list.
      products.addAll(productsOfItem);
    }
    return new ProductList(products);
  }
}
