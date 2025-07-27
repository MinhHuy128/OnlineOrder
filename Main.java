import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import Customer.*;
import Order.*;
import Product.*;
import Processing.*;
import ShippingStrategy.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerManager customerManager = new CustomerManager();
    private static final ProductManager productManager = ProductManager.getInstance();
    public static void main(String[] args) {
        Customer response = signInUpMenu();
        if (response == null) {return;} 

        // Loop to allow creating new orders after cancellation
        while (true) {
            Order orderResponse = orderMenu(response);
            if (orderResponse == null) {
                // User chose to exit from order menu
                break;
            }

            Order wrapOrderResponse = wrapOrder(orderResponse, response);
            if (wrapOrderResponse == null) {
                System.out.println("Order wrapping cancelled. Starting over...");
                continue;
            }

            IShippingStrategy shippingStrategy = shippingStrategyMenu();
            if (shippingStrategy == null) {
                System.out.println("Shipping strategy selection cancelled. Starting over...");
                continue;
            }

            processWrappedOrder(wrapOrderResponse, shippingStrategy);
            
            System.out.println("Would you like to create another order?");
            System.out.println("1. Yes, create another order");
            System.out.println("2. No, exit");
            System.out.print("Enter your choice: ");
            
            int continueChoice = scanner.nextInt();
            scanner.nextLine();
            
            if (continueChoice != 1) {
                break; // Exit the loop
            }
        }
        
        System.out.println("Thank you for using our Online Order Processing System!");
    }

    private static void clearTerminal() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processWrappedOrder(Order order, IShippingStrategy shippingStrategy) {
        int choice = 0;
        boolean orderProcessed = false;
        ProcessingOrder processingOrder = null;
        
        do {
            clearTerminal();
            System.out.println("============== Order Processing Menu ==============");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer: " + order.getCustomerOfThisOrder().getName());
            System.out.println("Total: $" + String.format("%.2f", order.calculateTotal()));
            System.out.println("Shipping Method: " + shippingStrategy.getMethodName());
            System.out.println("Status: " + (orderProcessed ? "PROCESSED" : "PENDING"));
            System.out.println();
            
            if (!orderProcessed) {
                System.out.println("1. Process Order");
                System.out.println("2. View Order Details");
                System.out.println("3. Cancel Order");
                System.out.println("-1. Exit");
            } else {
                System.out.println("1. View Invoice");
                System.out.println("2. View Order Details");
                System.out.println("-1. Exit");
            }
            
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (!orderProcessed) {
                        // Process the order
                        clearTerminal();
                        System.out.println("============== Processing Your Order ==============");
                        System.out.println("Processing your order...");
                        System.out.println("Press Enter to proceed with processing...");
                        scanner.nextLine();
                        
                        try {
                            processingOrder = new OnlineOrderProcessing(order, shippingStrategy);
                            processingOrder.Process();
                            orderProcessed = true;
                            
                            System.out.println("\n✓ Order processing completed successfully!");
                            System.out.println("Press Enter to continue...");
                            scanner.nextLine();
                            
                        } catch (Exception e) {
                            System.err.println("✗ Error processing order: " + e.getMessage());
                            System.out.println("Press Enter to continue...");
                            scanner.nextLine();
                        }
                    } else {
                        // View invoice
                        clearTerminal();
                        if (processingOrder != null) {
                            // Re-generate invoice for viewing
                            processingOrder.generateInvoice();
                        }
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                    }
                    break;
                    
                case 2:
                    // View order details
                    clearTerminal();
                    System.out.println("============== Order Details ==============");
                    System.out.println(order.getOrderDetails());
                    scanner.nextLine();
                    break;
                    
                case 3:
                    if (!orderProcessed) {
                        // Cancel order
                        clearTerminal();
                        System.out.println("============== Cancel Order ==============");
                        System.out.println("Are you sure you want to cancel this order?");
                        System.out.println("Order ID: " + order.getOrderId());
                        System.out.println("Total: $" + String.format("%.2f", order.calculateTotal()));
                        System.out.println();
                        System.out.println("1. Yes, cancel the order");
                        System.out.println("2. No, go back");
                        System.out.print("Enter your choice: ");
                        
                        int cancelChoice = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (cancelChoice == 1) {
                            System.out.println("Order cancelled successfully.");
                            System.out.println("Thank you for using our service!");
                            System.out.println("Press Enter to exit...");
                            scanner.nextLine();
                            choice = -2;
                            break;
                        } else {
                            System.out.println("Order cancellation aborted.");
                            System.out.println("Press Enter to continue...");
                            scanner.nextLine();
                        }
                    } else {
                        // View order status
                        clearTerminal();
                        System.out.println("============== Order Status ==============");
                        if (processingOrder != null) {
                            processingOrder.getState();
                            processingOrder.getCustomers();
                        }
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                    }
                    break;
                    
                case -1:
                    if (orderProcessed) {
                        System.out.println("Thank you for your order!");
                        System.out.println("Your order has been processed successfully.");
                    } else {
                        System.out.println("Exiting without processing the order.");
                        System.out.println("Your order is still pending.");
                    }
                    System.out.println("Press Enter to exit...");
                    scanner.nextLine();
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
            }
        } while (choice != -1 && choice != -2); // Exit on -1 (normal exit) or -2 (cancelled)
        if (choice == -2) {
            return;
        }
    }

    private static IShippingStrategy shippingStrategyMenu() {
        int choice = 0;
        IShippingStrategy shippingStrategy = null;
        do {
            clearTerminal();
            System.out.println("============== Select Shipping Strategy ==============");
            System.out.println("1. Economy Shipping");
            System.out.println("2. Standard Shipping");
            System.out.println("3. Express Shipping");
            System.out.println("4. Done");
            System.out.println("-1. Exit");
            System.out.println("Current Shipping Strategy: " + 
                (shippingStrategy != null ? shippingStrategy.getMethodName() : "None"));
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == -1) {
                System.out.println("Exiting the system. Goodbye!");
                return null; // Exit the menu
            }

            System.out.println("\n");

            switch (choice) {
                case 1:
                    if (shippingStrategy != null && shippingStrategy instanceof EconomyShipping) {
                        System.out.println("Economy Shipping is already selected.");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                    } else {
                        if (shippingStrategy != null) {
                            System.out.println("Switching to Economy Shipping from " + shippingStrategy.getMethodName());
                        } else {
                            System.out.println("Selected Economy Shipping");
                        }
                        shippingStrategy = new EconomyShipping();
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                    }
                    continue;

                case 2:
                    if (shippingStrategy != null && shippingStrategy instanceof StandardShipping) {
                        System.out.println("Standard Shipping is already selected.");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                    } else {
                        if (shippingStrategy != null) {
                            System.out.println("Switching to Standard Shipping from " + shippingStrategy.getMethodName());
                        } else {
                            System.out.println("Selected Standard Shipping");
                        }
                        shippingStrategy = new StandardShipping();
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                    }
                    continue;
                    
                case 3:
                    if (shippingStrategy != null && shippingStrategy instanceof ExpressShipping) {
                        System.out.println("Express Shipping is already selected.");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                    } else {
                        if (shippingStrategy != null) {
                            System.out.println("Switching to Express Shipping from " + shippingStrategy.getMethodName());
                        } else {
                            System.out.println("Selected Express Shipping");
                        }
                        shippingStrategy = new ExpressShipping();
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                    }
                    continue;
                case 4:
                    if (shippingStrategy == null) {
                        System.out.println("No shipping strategy selected. Please select one before proceeding.");
                        scanner.nextLine();
                        continue;
                    }
                    System.out.println("You have selected: " + shippingStrategy.getMethodName());
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    return shippingStrategy;
                case -1:
                    System.out.println("Exiting the shipping strategy selection. Goodbye!");
                    scanner.nextLine();
                    return null; // Exit the menu
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue; // Go back to the menu
            }
        } while (choice != -1);
        return shippingStrategy; // Return the selected shipping strategy
    }

    private static Order wrapOrder(Order order, Customer customer) {
        int wrapChoice = 0;
        boolean discountApplied = false;
        boolean giftWrapApplied = false;
        boolean insuranceWrapApplied = false;
        Order wrappedOrder = order; // Start with the original order
        
        do {
            clearTerminal();
            System.out.println("============== Wrap Your Order ==============");
            System.out.println("Wrapping order for customer: " + customer.getName());
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Current total: $" + String.format("%.2f", wrappedOrder.calculateTotal()));
            System.out.println("Products in cart: " + order.getProductsInCart().size());
            System.out.println("\n");
            
            // Show current decorators applied
            if (discountApplied || giftWrapApplied || insuranceWrapApplied) {
                System.out.println("Applied decorators:");
                if (discountApplied) System.out.println("✓ Discount (10%)");
                if (giftWrapApplied) System.out.println("✓ Gift Wrap ($15.99)");
                if (insuranceWrapApplied) System.out.println("✓ Insurance ($5.99)");
                System.out.println();
            }

            System.out.println("Wrap options:");
            System.out.println("1. " + (discountApplied ? "[APPLIED] " : "") + "Discount Wrap (10% off)");
            System.out.println("2. " + (giftWrapApplied ? "[APPLIED] " : "") + "Gift Wrap ($15.99)");
            System.out.println("3. " + (insuranceWrapApplied ? "[APPLIED] " : "") + "Insurance Wrap ($5.99)");
            System.out.println("4. View order details");
            System.out.println("-1. Done Wrapping");
            System.out.print("Enter your choice: ");
            wrapChoice = scanner.nextInt();
            scanner.nextLine();

            System.out.println("\n");

            switch (wrapChoice) {
                case 1:
                    if (discountApplied) {
                        System.out.println("Discount already applied. Please choose another wrap option.");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                        continue;
                    }
                    System.out.println("Applying 10% Discount Wrap...");
                    wrappedOrder = new DiscountDecorator(wrappedOrder, 0.1); // Stack on current order
                    discountApplied = true;
                    System.out.println("Discount applied! New total: $" + String.format("%.2f", wrappedOrder.calculateTotal()));
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                    
                case 2:
                    if (giftWrapApplied) {
                        System.out.println("Gift Wrap already applied. Please choose another wrap option.");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                        continue;
                    }
                    System.out.println("Applying Gift Wrap...");
                    wrappedOrder = new GiftDecorator(wrappedOrder, 15.99); // Stack on current order
                    giftWrapApplied = true;
                    System.out.println("Gift wrap applied! New total: $" + String.format("%.2f", wrappedOrder.calculateTotal()));
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;

                case 3:
                    if (insuranceWrapApplied) {
                        System.out.println("Insurance Wrap already applied. Please choose another wrap option.");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                        continue;
                    }
                    System.out.println("Applying Insurance Wrap...");
                    wrappedOrder = new InsuranceDecorator(wrappedOrder, 5.99); // Stack on current order
                    insuranceWrapApplied = true;
                    System.out.println("Insurance applied! New total: $" + String.format("%.2f", wrappedOrder.calculateTotal()));
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                    
                case 4:
                    System.out.println("Current Order Details:");
                    System.out.println(wrappedOrder.getOrderDetails());
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                    
                case -1:
                    System.out.println("Wrapping complete!");
                    if (discountApplied || giftWrapApplied || insuranceWrapApplied) {
                        System.out.println("Final order details:");
                        System.out.println(wrappedOrder.getOrderDetails());
                    }
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
            }
        } while (wrapChoice != -1);
        
        return wrappedOrder; // Return the decorated order
    }

    private static Order orderMenu(Customer customer) {
        int choice = 0;
        Order order = null;
        do {
            clearTerminal();
            System.out.println("============== Create a new order ==============");
            System.out.println("1. Create a new order");
            System.out.println("2. View your order");
            System.out.println("3. Checkout your order");
            System.out.println("-1. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == -1) {
                System.out.println("Exiting the system. Goodbye!");
                System.out.println("Press Enter to exit...");
                scanner.nextLine();
                return null;
            }

            System.out.println("\n");

            switch (choice) {
                case 1:
                    order = createNewOrder(customer);
                    continue;
                case 2:
                    if (order == null) {
                        System.out.println("You have no order to view.");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                        continue;
                    }
                    System.out.println("Your order details:");
                    System.out.println(order.getOrderDetails());
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    continue;
                case 3:
                    if (order == null) {
                        System.out.println("You have no order to checkout.");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                        continue;
                    }
                    System.out.println("Proceeding to checkout...");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    choice = -1; // Exit the loop to proceed with checkout
                    return order; // Complete the order
            
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    continue; // Go back to the main menu
            }
        } while (choice != -1);
        return order; // Return the order if created
    }


    private static Order createNewOrder(Customer customer) {
        clearTerminal();
        System.out.println("Creating a new order for customer: " + customer.getName());
        Order newOrder = new Order(customer);
        System.out.println("New order created with ID: " + newOrder.getOrderId());
        System.out.println("Press Enter to continue...");
        scanner.nextLine();

        while (true) {
            clearTerminal();
            System.out.println("============== Product Selection ==============");
            
            // Dynamically display all available products
            List<Product> availableProducts = productManager.getAvailableProducts();
            if (availableProducts.isEmpty()) {
                System.out.println("No products available at the moment.");
                scanner.nextLine();
                return null;
            }
            
            // Display products dynamically
            for (int i = 0; i < availableProducts.size(); i++) {
                Product product = availableProducts.get(i);
                System.out.println((i + 1) + ". " + product.getName() + 
                    " - $" + String.format("%.2f", product.getPrice()) + 
                    " (Stock: " + product.getAmountInStock() + ")");
            }
            System.out.println("\n\n");
            System.out.println((availableProducts.size() + 1) + ". View cart");
            System.out.println((availableProducts.size() + 2) + ". Remove product from cart");
            System.out.println((availableProducts.size() + 3) + ". Done with order");
            System.out.println("-1. Cancel order");
            System.out.print("Enter your choice: ");
            
            int productChoice = scanner.nextInt();
            scanner.nextLine();

            if (productChoice == -1) {
                System.out.println("Order cancelled.");
                return null;
            }

            // Handle product selection dynamically
            if (productChoice >= 1 && productChoice <= availableProducts.size()) {
                Product selectedProduct = availableProducts.get(productChoice - 1);
                addProductToCart(newOrder, selectedProduct.getName());
                continue;
            }
            
            // Handle menu options
            int viewCartOption = availableProducts.size() + 1;
            int removeOption = availableProducts.size() + 2;
            int checkoutOption = availableProducts.size() + 3;
            
            if (productChoice == viewCartOption) {
                // View cart
                clearTerminal();
                System.out.println("============== Your Cart ==============");
                newOrder.printProductsInCart();
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                continue;
                
            } else if (productChoice == removeOption) {
                // Remove product from cart
                if (newOrder.getProductsInCart().isEmpty()) {
                    System.out.println("Your cart is empty. Please add products before removing.");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    continue;
                }
                
                handleRemoveFromCart(newOrder);
                continue;
                
            } else if (productChoice == checkoutOption) {
                // Checkout
                if (newOrder.getProductsInCart().isEmpty()) {
                    System.out.println("Your cart is empty. Please add products before checking out.");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    continue;
                }
                System.out.println("Back to order menu...");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                return newOrder;
                
            } else {
                System.out.println("Invalid choice. Please try again.");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private static void handleRemoveFromCart(Order order) {
        clearTerminal();
        System.out.println("============== Remove Products ==============");
        
        // Get products currently in cart
        HashMap<Product, Integer> cartProducts = order.getProductsInCart();
        List<Product> productsInCart = new ArrayList<>(cartProducts.keySet());
        
        if (productsInCart.isEmpty()) {
            System.out.println("Your cart is empty.");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            return;
        }
        
        System.out.println("Select a product to remove from cart:");
        for (int i = 0; i < productsInCart.size(); i++) {
            Product product = productsInCart.get(i);
            int quantity = cartProducts.get(product);
            System.out.println((i + 1) + ". " + product.getName() + 
                " (Quantity in cart: " + quantity + ")");
        }
        System.out.println("-1. Cancel");
        System.out.print("Enter your choice: ");
        
        int removeChoice = scanner.nextInt();
        scanner.nextLine();

        if (removeChoice == -1) {
            System.out.println("Cancelled removing product.");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        if (removeChoice >= 1 && removeChoice <= productsInCart.size()) {
            Product selectedProduct = productsInCart.get(removeChoice - 1);
            removeProductFromCart(order, selectedProduct.getName());
            System.out.println(
                String.format("Removed %s from cart.", selectedProduct.getName())
            );
            scanner.nextLine();

        } else {
            System.out.println("Invalid choice. Please try again.");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }

    private static int addProductToCart(Order order, String productName) {
        Product productToAdd = productManager.getProductByName(productName);
        if (productToAdd == null) {
            System.out.println("Product not found: " + productName);
            scanner.nextLine();
            return -1;
        }
        System.out.print("How many " + productName + "(s) do you want to add? (1-" + productToAdd.getAmountInStock() + "): ");
        int amountToAdd = scanner.nextInt();
        scanner.nextLine();
        if (amountToAdd < 1 || amountToAdd > productToAdd.getAmountInStock()) {
            System.out.println("Invalid amount. Please try again.");
            scanner.nextLine();
            return -1;
        }
        order.addProductToCart(productToAdd, amountToAdd);
        System.out.println(
            String.format("Added %d %s(s) to cart for $%.2f each -> Total: $%.2f",
                amountToAdd, 
                productName, 
                productToAdd.getPrice(), 
                productToAdd.getPrice() * amountToAdd)
        );
        scanner.nextLine();
        return 1;
    }

    private static int removeProductFromCart(Order order, String productName) {
        Product productToRemove = productManager.getProductByName(productName);
        if (productToRemove == null) {
            System.out.println("Product not found: " + productName);
            scanner.nextLine();
            return -1;
        }
        
        // Check if product is in cart before removing
        if (!order.getProductsInCart().containsKey(productToRemove)) {
            System.out.println("Product " + productName + " is not in your cart.");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            return -1;
        }
        
        int amountbought = order.getProductsInCart().get(productToRemove);
        if (amountbought > 1) {
            System.out.print(
                String.format("How many %s(s) do you want to remove? (1-%d)", productName, amountbought)
            );

            int amountToRemove = scanner.nextInt();
            scanner.nextLine();
            if (amountToRemove < 1 || amountToRemove > amountbought) {
                System.out.println("Invalid amount. Please try again.");
                scanner.nextLine();
                return -1;
            }
            order.removeProductFromCart(productToRemove, amountToRemove);
            System.out.println(
                String.format("Removed %d %s(s) from cart.", amountToRemove, productName)
            );
            return 1;
        }
        else {
            System.out.println(
                String.format("Removed %s from cart.", productName)
            );
            return 1;
        }
    }

    private static Customer signInUpMenu() {
        int choice = 0;
        // Sign in or sign up menu
        // Loop until user chooses to exit or successfully signs in or signs up
        do {
            clearTerminal();
            System.out.println("============== Welcome to the Online Order Processing System ==============");
            System.out.println("1. Sign in");
            System.out.println("2. Sign up");
            System.out.println("-1. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == -1) {
                System.out.println("Exiting the system. Goodbye!");
                System.out.println("Press Enter to exit...");
                scanner.nextLine();
                return null;
            }

            switch (choice) {
                case 1:
                    Customer customer = signIn();
                    if (customer != null) {
                        System.out.println("Welcome back, " + customer.getName() + "!");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                        return customer; // Return the signed-in customer
                    }
                    else {
                        System.out.println("Sign in cancelled.");
                        scanner.nextLine();
                        continue; 
                    }
                case 2:
                    Customer newCustomer = signUp();
                    if (newCustomer != null) {
                        System.out.println("Welcome, " + newCustomer.getName() + "!");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                        return newCustomer;
                    }
                    else {
                        System.out.println("Sign up cancelled.");
                        scanner.nextLine();
                        continue;
                    }
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    continue; // Go back to the main menu
            }
        } while (choice != -1);
        return null;
    }

    private static Customer signIn() {
        while (true) {
            clearTerminal();
            System.err.println("Welcome to the Sign In Page");
            System.out.println("TYPE -1 TO EXIT");
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            if (email.equals("-1")) {
                return null;
            }

            System.out.print("Enter your phone number: ");
            String phone = scanner.nextLine();
            if (phone.equals("-1")) {
                return null; // User wants to exit
            }
            
            if (email.isEmpty() || phone.isEmpty()) {
                System.out.println("Email and phone number cannot be empty.");
                System.out.println("Press Enter to try again...");
                scanner.nextLine();
                continue;
            }

            Customer customerByEmail = customerManager.findCustomerByEmail(email);
            Customer customerByPhone = customerManager.findCustomerByPhone(phone);

            if (customerByEmail == null || customerByPhone == null) {
                System.out.println("Invalid credentials. Customer not found.");
                System.out.println("Press Enter to try again...");
                scanner.nextLine();
                continue;
            }
            
            if (!customerByEmail.getCustomerId().equals(customerByPhone.getCustomerId())) {
                System.out.println("Email and phone number belong to different accounts. Please check your credentials.");
                System.out.println("Press Enter to try again...");
                scanner.nextLine();
                continue;
            }
            
            clearTerminal();
            System.out.println("Sign in successful!");
            System.out.println(customerByEmail.getCustomerDetails());
            return customerByEmail;
        }
    }

    private static Customer signUp() {
        while (true) {
            clearTerminal();
            System.err.println("Welcome to the Sign Up Page");
            System.out.println("TYPE -1 TO EXIT");
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            if (name.equals("-1")) {
                return null; // User wants to exit
            }

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            if (email.equals("-1")) {
                return null;
            }

            System.out.print("Enter your phone number: ");
            String phone = scanner.nextLine();
            if (phone.equals("-1")) {
                return null; 
            }

            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            if (address.equals("-1")) {
                return null;
            }

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                System.out.println("All fields are required.");
                System.out.println("Press Enter to try again...");
                scanner.nextLine();
                continue;
            }

            // Check if customer already exists
            Customer existingCustomerEmail = customerManager.findCustomerByEmail(email);
            Customer existingCustomerPhone = customerManager.findCustomerByPhone(phone);
            if (existingCustomerEmail != null) {
                System.out.println("Email already exists. Please try a different email.");
                System.out.println("Press Enter to try again...");
                scanner.nextLine();
                continue;
            }
            else if (existingCustomerPhone != null) {
                System.out.println("Phone number already exists. Please try a different phone number.");
                System.out.println("Press Enter to try again...");
                scanner.nextLine();
                continue;
            }

            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("Invalid email format. Please enter a valid email.");
                System.out.println("Press Enter to try again...");
                scanner.nextLine();
                continue;
            }

            Customer newCustomer = new Customer(name, email, phone, address);
            customerManager.addCustomer(newCustomer);
            
            clearTerminal();
            System.out.println("Sign up successful!");
            System.out.println(newCustomer.getCustomerDetails());
            return newCustomer;
        }
    }
}

