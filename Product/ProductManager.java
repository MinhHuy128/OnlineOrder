package Product;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductManager {
    private static ProductManager instance;
    private List<Product> allProducts;
    private static final String PRODUCTS_CSV_FILE = Paths.get("CSVs", "products.csv").toString();

    private ProductManager() {
        loadProducts();
    }

    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    private void loadProducts() {
        try {
            this.allProducts = ProductCSVReader.readProductsFromCSV(PRODUCTS_CSV_FILE);
        } catch (IOException e) {
            System.err.println("Error loading products: " + e.getMessage());
            this.allProducts = new ArrayList<>(); // Empty list if loading fails
        }
    }

    public Product getProductByName(String productName) {
        return allProducts.stream()
                .filter(product -> product.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(allProducts);
    }

    public List<Product> getAvailableProducts() {
        return allProducts.stream()
                .filter(product -> product.getAmountInStock() > 0)
                .collect(Collectors.toList());
    }

    public void printAllProducts() {
        List<Product> available = getAvailableProducts();
        for (int i = 0; i < available.size(); i++) {
            Product product = available.get(i);
            System.out.println((i + 1) + ". " + product.getName() + 
                " - $" + String.format("%.2f", product.getPrice()) + 
                " (Stock: " + product.getAmountInStock() + ")");
        }
    }

    public void refreshProducts() {
        loadProducts();
    }
}