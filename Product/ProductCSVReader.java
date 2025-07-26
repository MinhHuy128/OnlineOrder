package Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Log.Logger;

public class ProductCSVReader {
    /**
     * Reads products from a CSV file and returns a list of Product objects.
     *
     * @param filePath the path to the CSV file
     * @return a list of Product objects
     * @throws IOException if an I/O error occurs
     */
    private static final Logger logger = Logger.getInstance("logFile.log");
    
    public static List<Product> readProductsFromCSV(String filePath) throws IOException {
        List<Product> products = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header
                }
                
                String[] values = line.split(",");
                if (values.length >= 9) {
                    try {
                        String type = values[0].trim();
                        String name = values[1].trim();
                        String size = values[2].trim();
                        String color = values[3].trim();
                        double price = Double.parseDouble(values[4].trim());
                        double width = Double.parseDouble(values[5].trim());
                        double height = Double.parseDouble(values[6].trim());
                        double length = Double.parseDouble(values[7].trim());
                        int quantity = Integer.parseInt(values[8].trim());
                        
                        // Additional parameters for specific product types
                        String[] additionalParams = new String[0];
                        if (values.length > 9) {
                            additionalParams = new String[values.length - 9];
                            for (int i = 9; i < values.length; i++) {
                                additionalParams[i - 9] = values[i].trim();
                            }
                        }
                        
                        // Use factory to create the appropriate product
                        Product product = ProductFactory.createProduct(type, name, size, 
                                                                       color, price, width, 
                                                                       height, length, quantity,
                                                                       additionalParams);
                        products.add(product);
                        
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                        logger.log("Error parsing line: " + line + " - " + e.getMessage(), 3); // Log as ERROR
                    }
                }
            }
        }
        return products;
    }
}