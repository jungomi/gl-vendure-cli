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

    /**
     * Convert the product list to JSON.
     *
     * The resulting JSON is not prettified, i.e. it is all on a single line.
     *
     * @return JSON of the products as string.
     */
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"products\": [");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            sb.append(String.format("{\"id\": %d, \"name\": \"%s\", \"price\": %.2f}", product.getId(), product.getName(), product.getPrice()));
            // A comma is not allowed after the last element in the list in JSON,
            // it would result in a parsing error.
            if (i != products.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    /**
     * Convert the product list to a markdown table.
     *
     * Not nicely aligned in the text form, but can be rendered correctly in markdown.
     *
     * @return Markdown table of the products as string.
     */
    public String toTable() {
        StringBuilder sb = new StringBuilder();
        // Header of the table (id and price are right aligned)
        sb.append("|  ID  |      Name      |   Price   |\n");
        sb.append("|-----:|----------------|----------:|\n");
        for (Product product : products) {
            sb.append(String.format("| %d | %s | %.2f |\n", product.getId(), product.getName(), product.getPrice()));
        }
        return sb.toString();
    }
}
