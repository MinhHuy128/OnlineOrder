import model.*;
import Order.Order;

abstract class ProcessingOrder{
    private Order orders;
    private Customer customers;

    public boolean Processing(Order order){
        this.orders = order;
        if(verifyInventory(order)){
            generateInvoice(order);
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
    public abstract double calculateTotal(Order order);
    public abstract void generateInvoice(Order order);

}
