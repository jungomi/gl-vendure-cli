package product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
  public List<Product> products;

  public ProductList(List<Product> products) {
    this.products = products;
  }

  public static ProductList retrieveProducts(String url) {
    // Example products to test
    List<Product> products = new ArrayList<>();
    products.add(new Product(1, "Laptop 13 inch 8GB", 129900));
    products.add(new Product(2, "Laptop 15 inch 8GB", 139900));
    products.add(new Product(5, "Tablet 32GB", 32900));
    products.add(new Product(6, "Tablet 128GB", 44500));
    return new ProductList(products);
  }
}
