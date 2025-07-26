package Processing;

import java.util.HashMap;

import Order.Order;
// import OrderState.*;
import Product.Product;
import ShippingStrategy.IShippingStrategy;
import Customer.*;

public class OnlineOrderProcessing extends ProcessingOrder {

    public OnlineOrderProcessing(Order DectoratedOrder, IShippingStrategy shippingStrategy) {
        super(DectoratedOrder, shippingStrategy);
    }

    @Override
    public boolean verifyInventory(Order DectoratedOrder){ //xác minh sản phẩm trong đơn hàng (số lượng hàng hóa lớn hơn 0)
        HashMap<Product, Integer> productsInCart = DectoratedOrder.getProductsInCart();
        for (Product product : productsInCart.keySet()) {
            if (product.getQuantity() <= 0) {
                System.out.println("Product " + product.getName() + " is out of stock.");
                return false;
            }
            else if (productsInCart.get(product) > product.getQuantity()) {
                System.out.println("Not enough stock for product " + product.getName() + ". Requested: " + productsInCart.get(product) + ", Available: " + product.getQuantity());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean verifyCustomer(Customer customer) { //xác minh khách hàng
        CustomerManager manager = new CustomerManager();
        Customer customerOfThisOrder = customer;
        Customer customerFound = manager.findCustomerById(customerOfThisOrder.getCustomerId());
        if (customerFound == null) {
            System.out.println("Customer not found.");
            return false;
        }
        else if (customerFound.getEmail() == null || customerFound.getEmail().isEmpty()) {
            System.out.println("Customer email is not provided.");
            return false;
        } else if (customerFound.getPhoneNumber() == null || customerFound.getPhoneNumber().isEmpty()) {
            System.out.println("Customer phone number is not provided.");
            return false;
        }
        else {
            if (!customerFound.getCustomerId().equals(customerOfThisOrder.getCustomerId())) {
                System.out.println("Customer ID does not match the order.");
                return false;
            }
            if (!customerFound.getEmail().equals(customerOfThisOrder.getEmail())) {
                System.out.println("Customer email does not match the order.");
                return false;
            }
            if (!customerFound.getPhoneNumber().equals(customerOfThisOrder.getPhoneNumber())) {
                System.out.println("Customer phone number does not match the order.");
                return false;
            }
            if (!customerFound.getAddress().equals(customerOfThisOrder.getAddress())) {
                System.out.println("Customer address does not match the order.");
                return false;
            }
            if (!customerFound.getName().equals(customerOfThisOrder.getName())) {
                System.out.println("Customer name does not match the order.");
                return false;
            }
        }
        System.out.println("Customer verified successfully.");
        return true;
    }

    @Override
    public void generateInvoice() {
        // Use this.orders (from constructor) instead of parameter
        Customer customer = this.orders.getCustomerOfThisOrder();
        HashMap<Product, Integer> products = this.orders.getProductsInCart();
        double subtotal = this.orders.calculateTotal(); // This already includes decorator effects
        double shippingCost = this.shippingStrategy.calculateShippingFee(subtotal);
        double totalAmount = subtotal + shippingCost;
        
        System.out.println("=======================================");
        System.out.println("           ONLINE ORDER INVOICE       ");
        System.out.println("=======================================");
        System.out.println("Order ID: " + this.orders.getOrderId());
        System.out.println("Date: " + java.time.LocalDateTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println();
        
        // Customer Information
        System.out.println("CUSTOMER DETAILS:");
        System.out.println(customer.getCustomerDetails());
        System.out.println();
        
        // Product Details
        System.out.println("ITEMS ORDERED:");
        System.out.println("---------------------------------------");
        for (Product product : products.keySet()) {
            int quantity = products.get(product);
            double itemTotal = product.getPrice() * quantity;
            System.out.printf("%-20s x%-3d $%8.2f  $%8.2f%n", 
                product.getName(), quantity, product.getPrice(), itemTotal);
        }
        System.out.println("---------------------------------------");
        
        // Order details (includes decorator effects)
        System.out.println(this.orders.getOrderDetails());
        System.out.println();
        
        // Shipping and totals
        System.out.println("SHIPPING DETAILS:");
        System.out.println("Method: " + shippingStrategy.getMethodName());
        System.out.printf("Shipping Cost: $%.2f%n", shippingCost);
        System.out.println("---------------------------------------");
        System.out.printf("FINAL TOTAL: $%.2f%n", totalAmount);
        System.out.println("=======================================");
        
        System.out.println("Thank you for your order!");
    }
    
}
