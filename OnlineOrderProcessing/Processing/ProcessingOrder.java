package Processing;
import model.*;
import Order.Order;
import OrderState.*;

public abstract class ProcessingOrder{
    private Order orders;
    private Customer customers;
    private OrderState state = new NewOrderState();

    public boolean Processing(Order order){
        this.orders = order;
        if(verifyInventory(order)){
            generateInvoice(order);
            state.process(order);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean customerVerify(Customer customer){
        this.customers = customer;
        return verifyCustomer(customer);
    }
    public abstract boolean verifyCustomer(Customer customer);
    public abstract boolean verifyInventory(Order order);
    public abstract void generateInvoice(Order order);

}
