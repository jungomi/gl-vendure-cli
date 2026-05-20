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

class GraphQLQueryProductListTest {
  private GraphQLQueryProductList queryProductList;

  @BeforeEach
  void setUp() {
    queryProductList = new GraphQLQueryProductList();
  }

  @Test
  void testGetQuery() {
    String expected =
        "query ProductList { products { items { variants { id name priceWithTax } } } }";
    assertEquals(expected, queryProductList.getQuery());
  }

  @Test
  void testParseJson() throws JSONException {
    String jsonStr =
        "{\"data\": {\"products\": {\"items\": [{\"variants\": ["
            + "{\"id\": 1, \"name\": \"Product 1\", \"priceWithTax\": 12.50}, "
            + "{\"id\": 2, \"name\": \"Product 2\", \"priceWithTax\": 33.00}, "
            + "{\"id\": 3, \"name\": \"Another Product\", \"priceWithTax\": 40500.00}"
            + "]}]}}}";
    List<Product> products = new ArrayList<>();
    products.add(new Product(1, "Product 1", 12.50));
    products.add(new Product(2, "Product 2", 33));
    products.add(new Product(3, "Another Product", 40500));
    ProductList expected = new ProductList(products);
    JSONObject json = new JSONObject(jsonStr);
    assertEquals(expected, queryProductList.parseJson(json));
  }
}
