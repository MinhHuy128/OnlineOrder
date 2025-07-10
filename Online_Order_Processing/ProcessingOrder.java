import model.Customer;

abstract class ProcessingOrder{
    private Order orders;
    private Customer customers;

    public void Processing(Customer customer, Order order){
        this.orders = order;
        this.customers = customer;
        if(verifyCustomer(this.customers)){
            if(verifyInventory(this.orders)){
                generateInvoice(this.orders);
            }
        }
    }

    public abstract boolean verifyCustomer(Customer customer);
    public abstract boolean verifyInventory(Order order);
    public abstract double calculateTotal(Order order);
    public abstract void generateInvoice(Order order);

}
