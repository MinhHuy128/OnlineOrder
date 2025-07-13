package Product;

public class Pants extends Product {
    public void setPants(String productName, String color, double price, double length, double width, String size, int amountInStore) {
        super.setProduct(productName, color, price, length, width, size, amountInStore);
        super.updateCSV();
    }
}
