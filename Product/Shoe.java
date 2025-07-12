package Product;

public class Shoe extends Product {
    public Shoe(String productName, String color, double price, double length, double width, int size, int amountInStore) {
        super(productName, color, price, length, width, String.valueOf(size), amountInStore);
    }
}
