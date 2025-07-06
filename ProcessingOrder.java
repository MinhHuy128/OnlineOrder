abstract class ProcessingOrder{
    private Order orders;

    public void Processing(Order order){
        this.orders = order;
        System.out.println(verifyInventory(this.orders));
        System.out.println(calculateTotal(this.orders));
        generateInvoice(this.orders);
    }

    public abstract String verifyInventory(Order order);
    public abstract double calculateTotal(Order order);
    public abstract void generateInvoice(Order order);
    public abstract void notifyCustomer();

}
