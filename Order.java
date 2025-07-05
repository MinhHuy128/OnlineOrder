class Order {
  private String orderID;
  private OrderState currentState;
  public Order(String orderID){
    this.orderID = orderID;
    this.currentState = new NewOrderState();
  }
  



  // Này là của state nha ae
  public void setState(OrderState state){
    this.currentState = state;
  }

  public OrderState getState(){
    return this.currentState;
  }
   public void process() {
        currentState.process(this);
    }
    
    public void cancel() {
        currentState.cancel(this);
    }
    
    public void ship() {
        currentState.ship(this);
    }
    
    public void deliver() {
        currentState.deliver(this);
    }

    public String getStatusName() {
        return this.currentState.getStatus();
    }

}
