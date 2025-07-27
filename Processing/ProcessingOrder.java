package Processing;

import Order.Order;
import OrderState.*;
import ShippingStrategy.*;
import Customer.Customer;

public abstract class ProcessingOrder{
    protected Order orders;
    protected Customer customers;
    protected IShippingStrategy shippingStrategy;
    protected IOrderState state;

    public ProcessingOrder(Order order, IShippingStrategy shippingStrategy) {
        this.orders = order; // This holds the decorated order
        this.shippingStrategy = shippingStrategy;
        this.customers = order.getCustomerOfThisOrder();
        this.state = new ProcesingState(); // Set initial state to Processing
        // Don't set state here - let Process() method handle it
    }

    public void Process(){
        if(verifyInventory(this.orders) && verifyCustomer(this.customers)){
            // Set state to processing before generating invoice
            this.orders.setState(new ProcesingState());
            generateInvoice();
            this.orders.setState(new ShippingState());
        }
        else{
            System.out.println("Your Order have not been verify");
            this.orders.setState(new CancelledState());
            this.setState(new CancelledState());
            state.cancelOrder(orders);
        }
    }

    
    public abstract boolean verifyCustomer(Customer customer);
    public abstract boolean verifyInventory(Order order);
    public abstract void generateInvoice();

    public void getState() {
        System.out.println("Current order state: " + state.getStateName());
    }

    public void setState(IOrderState state) {
        this.state = state;
        System.out.println("Order state changed to: " + state.getStateName());
    }

    public void getCustomers() {
        System.out.println("Customer details:\n" + customers.getCustomerDetails());
    }
}

