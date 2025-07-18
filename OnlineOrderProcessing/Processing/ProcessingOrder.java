package Processing;
import model.*;
import Order.Order;
import OrderState.*;
import StrateggyShipping.*;

public abstract class ProcessingOrder{
    private Order orders;
    private Customer customers;
    private ShippingStrategy transport;
    private OrderState state = new NewOrderState();

    public void Processing(Order order, ShippingStrategy transport){
        this.orders = order;
        this.transport = transport;
        if(verifyInventory(order)){
            generateInvoice(order, transport);
            state.process(order);
        }
        else{
            System.out.println("Your Order have not been verify");
        }
    }
    public boolean customerVerify(Customer customer){
        this.customers = customer;
        return verifyCustomer(customer);
    }
    public abstract boolean verifyCustomer(Customer customer);
    public abstract boolean verifyInventory(Order order);
    public abstract void generateInvoice(Order order, ShippingStrategy transport);

}
