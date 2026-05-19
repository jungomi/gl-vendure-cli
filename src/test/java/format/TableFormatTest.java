package format;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.Product;
import product.ProductList;

class TableFormatTest {
  private ProductList productList;

  @BeforeEach
  void setUp() {
    List<Product> products = new ArrayList<>();
    products.add(new Product(1, "Product 1", 12.50));
    products.add(new Product(2, "Product 2", 33));
    products.add(new Product(3, "Another Product", 40500));
    productList = new ProductList(products);
  }

  @Test
  void testFormatProductList() {
    TableFormat formatter = new TableFormat();
    String table = formatter.formatProductList(productList);
    String expected =
        """
        |  ID  |      Name      |   Price   |
        |-----:|----------------|----------:|
        | 1 | Product 1 | 12.50 |
        | 2 | Product 2 | 33.00 |
        | 3 | Another Product | 40500.00 |
        """;
    assertEquals(expected, table);
  }
}
