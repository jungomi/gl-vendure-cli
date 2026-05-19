package format;

import product.Product;
import product.ProductList;

public class TableFormat implements FormatStrategy {
  /**
   * Convert the product list to a markdown table.
   *
   * <p>Not nicely aligned in the text form, but can be rendered correctly in markdown.
   *
   * @param productList The list of products to be formatted as a markdown table.
   * @return Markdown table of the products as string.
   */
  @Override
  public String formatProductList(ProductList productList) {
    StringBuilder sb = new StringBuilder();
    // Header of the table (id and price are right aligned)
    sb.append("|  ID  |      Name      |   Price   |\n");
    sb.append("|-----:|----------------|----------:|\n");
    for (Product product : productList.products) {
      sb.append(
          String.format(
              "| %d | %s | %.2f |\n", product.getId(), product.getName(), product.getPrice()));
    }
    return sb.toString();
  }
}
