package Product;

public class Shoe extends Product {
    public void setShoe(String productName, String color, double price, double length, double width, String size, int amountInStore) {
        super.setProduct(productName, color, price, length, width, size, amountInStore);
    }
}
