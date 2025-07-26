package Order;

import java.util.HashMap;
import java.util.List;

import OrderState.IOrderState;
import Product.Product;
import Product.ProductManager;
import Customer.Customer;
// import Log.Logger;


public class Order {
    private HashMap<Product, Integer> productsInCart;
    private ProductManager productManager;
    protected IOrderState currentState;
    private Customer customerOfThisOrder;
    private String orderId;
    private double totalAmount;
    private static int orderCounter = 0;
    // private static final Logger logger = Logger.getInstance("logFile.log");

    public Order(Customer customerOfThisOrder) {
        this.customerOfThisOrder = customerOfThisOrder;
        // Initialize the order with an empty cart and set the initial state
        this.currentState = new OrderState.NewOrderState(); 
        this.orderId = String.format("OId%5d", ++orderCounter);
        this.productsInCart = new HashMap<>();
        this.productManager = ProductManager.getInstance();
        System.out.println("New order created with state: " + currentState.getStateName());
    }

    public void addProductToCart(String productName, int quantity) {
        Product product = getProductByName(productName);
        if (product == null) {
            System.out.println("Product not found: " + productName);
            return;
        }
        if (quantity <= 0) {
            System.out.println("Invalid quantity for product: " + product.getName());
            this.calculateTotal(); // Recalculate total to reflect no change
            return;
        }
        this.productsInCart.put(product, 
            this.productsInCart.getOrDefault(product, 0) + quantity); // 0 if product not in cart
    }

    public void addProductToCart(Product product, int quantity) {
        if (product == null) {
            System.out.println("Product cannot be null.");
            return;
        }
        if (quantity <= 0) {
            System.out.println("Invalid quantity for product: " + product.getName());
            this.calculateTotal(); // Recalculate total to reflect no change
            return;
        }
        this.productsInCart.put(product, 
            this.productsInCart.getOrDefault(product, 0) + quantity); // 0 if product not in cart
    }

    public void removeProductFromCart(String productName) {
        Product product = getProductByName(productName);
        if (product == null) {
            System.out.println("Product not found: " + productName);
            return;
        }

        if (this.productsInCart.containsKey(product)) {
            this.productsInCart.remove(product);
            System.out.println("Removed product: " + product.getName());
        }
        else {
            System.out.println("Product not found in cart: " + product.getName());
        }
        this.calculateTotal(); // Recalculate total after removal
    }

    public void removeProductFromCart(Product product) {
        if (product == null) {
            System.out.println("Product cannot be null.");
            return;
        }

        if (this.productsInCart.containsKey(product)) {
            this.productsInCart.remove(product);
            System.out.println("Removed product: " + product.getName());
        }
        else {
            System.out.println("Product not found in cart: " + product.getName());
        }
        this.calculateTotal(); // Recalculate total after removal
    }

    public void removeProductFromCart(Product product, int quantity) {
        if (product == null) {
            System.out.println("Product cannot be null.");
            return;
        }

        if (this.productsInCart.containsKey(product)) {
            this.productsInCart.put(product, 
                this.productsInCart.get(product) - quantity);

            if (this.productsInCart.get(product) <= 0) {
                this.productsInCart.remove(product);
                // System.out.println("Removed product: " + product.getName());
            }
            else {
                // System.out.println("Reduced quantity of product: " + product.getName() + 
                    // " to " + this.productsInCart.get(product));
            }
        }
        else {
            System.out.println("Product not found in cart: " + product.getName());
        }
        this.calculateTotal(); // Recalculate total after removal
    }

    public void printProductsInCart() {
        System.out.println("Current cart contents:");
        if (productsInCart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        for (Product product : productsInCart.keySet()) {
            int quantity = productsInCart.get(product);
            System.out.println("Product: " + product.getName() + ", Quantity: " + quantity);
        }
    }

    public void setState(IOrderState state) {
        this.currentState = state;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Product product : productsInCart.keySet()) {
            total += product.getPrice() * productsInCart.get(product);
        }
        this.totalAmount = total;
        return this.totalAmount;
    }

    private Product getProductByName(String productName) {
        Product product = productManager.getProductByName(productName);
        if (product == null) {
            System.out.println("Product not found: " + productName);
        }
        return product;
    }

    public List<Product> getAvailableProductsList() {
        return productManager.getAllProducts();
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(orderId).append("\n");
        details.append("Customer: ").append(this.customerOfThisOrder.getName()).append("\n");
        details.append("Email: ").append(this.customerOfThisOrder.getEmail()).append("\n");
        details.append("Address: ").append(this.customerOfThisOrder.getAddress()).append("\n");
        details.append("Total Amount: $").append(String.format("%.2f", this.totalAmount)).append("\n");
        details.append("Current State: ").append(this.getCurrentState().getStateName()).append("\n");
        details.append("=======================================\n");
        details.append("Products:\n");
        
        for (Product product : productsInCart.keySet()) {
            int quantity = productsInCart.get(product);
            double subtotal = product.getPrice() * quantity;
            details.append(String.format("- %s x%d: $%.2f\n", 
                product.getName(), quantity, subtotal));
        }
        details.append(String.format("Total: $%.2f", calculateTotal()));
        details.append("\n=======================================\n");
        return details.toString();
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomerOfThisOrder() {
        return customerOfThisOrder;
    }

    public IOrderState getCurrentState() {
        return this.currentState;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCurrentState(IOrderState currentState) {
        this.currentState = currentState;
    }

    public void setCustomerOfThisOrder(Customer customerOfThisOrder) {
        this.customerOfThisOrder = customerOfThisOrder;
    }

    public void processOrder() {
        currentState.processOrder(this);
    }

    public void cancelOrder() {
        currentState.cancelOrder(this);
    }

    public void shipOrder() {
        currentState.shipOrder(this);
    }

    public void deliverOrder() {
        currentState.deliverOrder(this);
    }

    public HashMap<Product, Integer> getProductsInCart() {
        return new HashMap<>(productsInCart); // Return a copy to prevent external modification
    }
}
