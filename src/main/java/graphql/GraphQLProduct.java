package graphql;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import product.Product;

public class GraphQLProduct implements GraphQLObject<List<Product>> {
  @Override
  public String getGraphQLDefinition() {
    // Only the info of the variants are required to create the individual products.
    // We consider this class to be equivalent to a ProductVariant.
    return "{ variants { id name priceWithTax } }";
  }

  // This returns a list of products because the variants in the GraphQL always produce a list.
  @Override
  public List<Product> parseJson(JSONObject json) throws JSONException {
    List<Product> products = new ArrayList<>();
    JSONArray variants = json.getJSONArray("variants");
    for (int i = 0; i < variants.length(); i++) {
      JSONObject variant = variants.getJSONObject(i);
      Product product =
          new Product(
              variant.getInt("id"), variant.getString("name"), variant.getDouble("priceWithTax"));
      products.add(product);
    }
    return products;
  }
}
