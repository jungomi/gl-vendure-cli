package product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductListTest {
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
    void testToJson() {
        String json = productList.toJson();
        String expected = "{\"products\": [{\"id\": 1, \"name\": \"Product 1\", \"price\": 12.50}, {\"id\": 2, \"name\": \"Product 2\", \"price\": 33.00}, {\"id\": 3, \"name\": \"Another Product\", \"price\": 40500.00}]}";
        assertEquals(expected, json);
    }

    @Test
    void testToTable() {
        String table = productList.toTable();
        String expected = """
        |  ID  |      Name      |   Price   |
        |-----:|----------------|----------:|
        | 1 | Product 1 | 12.50 |
        | 2 | Product 2 | 33.00 |
        | 3 | Another Product | 40500.00 |
        """;
        assertEquals(expected, table);
    }
}