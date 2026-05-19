package format;

import product.ProductList;

public interface FormatStrategy {
  /**
   * Format a list of products with the desired strategy, such as a table.
   *
   * @param productList List of products to format.
   * @return The formatted list given as a string.
   */
  String formatProductList(ProductList productList);
}
