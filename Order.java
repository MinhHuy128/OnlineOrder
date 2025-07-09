import java.util.ArrayList;
import java.util.List;

import model.Items;

class Order {
  private String orderID;
  private List<Items> items = new ArrayList<>();
  private OrderState currentState;
  public Order(String orderID){
    this.orderID = orderID;
    this.currentState = new NewOrderState();
  }
  public void addItem(Items item){ //thêm sản phẩm vào đơn hàng
        items.add(item);
    }
  public List<Items> getProductList(){ //lấy danh sách sản phẩm trong đơn hàng
    return this.items;
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
