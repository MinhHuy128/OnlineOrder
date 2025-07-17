package Product;

public class Shirt extends Product {
    public void setShirt(String productName, String color, double price, double length, double width, String size, int amountInStore) {
        super.setProduct(productName, color, price, length, width, size, amountInStore);
    }
}
