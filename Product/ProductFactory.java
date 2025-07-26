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
        addTypeSpecificAttributes(builder, type, additionalParams);
        
        return builder.build();
    }
    
    private static void addTypeSpecificAttributes(Product.Builder builder, String type, String[] additionalParams) {
        switch (type.toLowerCase()) {
            case "shoe":
                if (additionalParams.length > 0) {
                    builder.addAttribute("brand", additionalParams[0]);
                }
                if (additionalParams.length > 1) {
                    builder.addAttribute("material", additionalParams[1]);
                }
                break;
            case "pants":
                if (additionalParams.length > 0) {
                    builder.addAttribute("style", additionalParams[0]);
                }
                if (additionalParams.length > 1) {
                    builder.addAttribute("fit", additionalParams[1]);
                }
                break;
            case "shirt":
                if (additionalParams.length > 0) {
                    builder.addAttribute("sleeve_type", additionalParams[0]);
                }
                if (additionalParams.length > 1) {
                    builder.addAttribute("collar_type", additionalParams[1]);
                }
                break;
            default:
                for (int i = 0; i < additionalParams.length; i++) {
                    builder.addAttribute("attribute_" + (i + 1), additionalParams[i]);
                }
                break;
        }
    }
    
    // Factory methods for specific product types
    public static Product createShoe(String name, String size, String color, double price,
                                   double width, double height, double length, int quantity,
                                   String brand, String material) {
        return new Product.Builder("shoe", name)
                .size(size)
                .color(color)
                .price(price)
                .dimensions(width, height, length)
                .quantity(quantity)
                .addAttribute("brand", brand)
                .addAttribute("material", material)
                .build();
    }
    
    public static Product createShirt(String name, String size, String color, double price,
                                    double width, double height, double length, int quantity,
                                    String sleeveType, String collarType) {
        return new Product.Builder("shirt", name)
                .size(size)
                .color(color)
                .price(price)
                .dimensions(width, height, length)
                .quantity(quantity)
                .addAttribute("sleeve_type", sleeveType)
                .addAttribute("collar_type", collarType)
                .build();
    }
}