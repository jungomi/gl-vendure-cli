package graphql;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.Product;
import product.ProductList;

class GraphQLQueryProductTest {
  private GraphQLQueryProduct queryProduct;

  @BeforeEach
  void setUp() {
    queryProduct = new GraphQLQueryProduct(3);
  }

  @Test
  void testGetQuery() {
    String expected = "query GetProduct { product(id: 3) { variants { id name priceWithTax } } }";
    assertEquals(expected, queryProduct.getQuery());
  }

  @Test
  void testParseJson() throws JSONException {
    String jsonStr =
        "{\"data\": {\"product\": {\"variants\": ["
            + "{\"id\": 3, \"name\": \"Another Product\", \"priceWithTax\": 40500.00}"
            + "]}}}";
    List<Product> products = new ArrayList<>();
    products.add(new Product(3, "Another Product", 40500));
    ProductList expected = new ProductList(products);
    JSONObject json = new JSONObject(jsonStr);
    assertEquals(expected, queryProduct.parseJson(json));
  }
}
