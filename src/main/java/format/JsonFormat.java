package format;

import product.Product;
import product.ProductList;

public class JsonFormat implements FormatStrategy {
  /**
   * Convert the product list to JSON.
   *
   * <p>The resulting JSON is not prettified, i.e. it is all on a single line.
   *
   * @param productList The list of products to be formatted as a JSON.
   * @return JSON of the products as string.
   */
  @Override
  public String formatProductList(ProductList productList) {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"products\": [");
    for (int i = 0; i < productList.products.size(); i++) {
      Product product = productList.products.get(i);
      sb.append(
          String.format(
              "{\"id\": %d, \"name\": \"%s\", \"price\": %.2f}",
              product.getId(), product.getName(), product.getPrice()));
      // A comma is not allowed after the last element in the list in JSON,
      // it would result in a parsing error.
      if (i != productList.products.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append("]}");
    return sb.toString();
  }
}
