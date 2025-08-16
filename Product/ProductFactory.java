package Product;

public class ProductFactory {
    
    public static Product createProduct(String type, String name, String size, String color,
                                      double price, double width, double height, double length,
                                      int quantity, String... additionalParams) {
        
        Product.Builder builder = new Product.Builder(type, name)
                .size(size)
                .color(color)
                .price(price)
                .dimensions(width, height, length)
                .quantity(quantity);
        
        // Add type-specific attributes
        addTypeSpecificAttributes(builder, additionalParams);
        
        return builder.build();
    }
    
    private static void addTypeSpecificAttributes(Product.Builder builder, String[] additionalParams) {
        for (int i = 0; i < additionalParams.length; i++) {
            builder.addAttribute("attribute_" + (i + 1), additionalParams[i]);
        }
    }
}