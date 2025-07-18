import Order.Order;
import Processing.OnlineProcessingOrder;
import Processing.ProcessingOrder;
import Product.Pants;
import Product.Product;
import Product.Shirt;
import Product.Shoe;
import StrateggyShipping.*;
import java.util.Scanner;
import model.*;

public class Main {
    public static void main(String[] args) {
        int choose = -1;
        int cho = -1;
        int transport = -1;
        ShippingStrategy eco = new EconomyShipping();
        ShippingStrategy exp = new ExpressShipping();
        ShippingStrategy sta = new StandardShipping();
        ProcessingOrder pr = new OnlineProcessingOrder();
        Scanner input = new Scanner(System.in);
        Customer c = new Customer();
        Product p = new Pants();
        Product s = new Shoe();
        Product sh = new Shirt();
        Order o = new Order();
        // do {
        // //Nhân viên có thể thêm hàng hóa/giá cả/tồn kho vào danh sách hàng hóa đang
        // bán
        // //Menu phía nhân viên
        // System.out.print("1. Adding Items:\n2. Get List of Item\nchoose: ");
        // choose = input.nextInt();
        // input.nextLine();
        // switch (choose) {
        // case 1:
        // System.out.print("Input Item Id: ");
        // String id = input.nextLine();
        // System.out.print("Input Item Name: ");
        // String name = input.nextLine();
        // System.out.print("Input Item price: ");
        // double price = input.nextDouble();
        // System.out.print("Input Number of Item in Stored: ");
        // int q = input.nextInt();
        // i.setItems(id, name, q, price);
        // i.addItems();
        // break;
        // case 2:
        // i.getStoredList();
        // break;
        // default:
        // break;
        // }
        // } while (choose != 0);
        do {
            // Menu phía khách hàng
            System.out.print("0. Back:\n1. Sign In:\nchoose: ");
            choose = input.nextInt();
            input.nextLine();
            switch (choose) {
                case 1:
                    System.out.print("Input First Name: ");
                    String first = input.nextLine();
                    System.out.print("Input Last Name: ");
                    String last = input.nextLine();
                    System.out.print("Input Email: ");
                    String email = input.nextLine();
                    System.out.print("Input Phone Number: ");
                    String phone = input.nextLine();
                    System.out.print("Input Adress: ");
                    String adress = input.nextLine();
                    c.setCustomer(first, last, email, phone, adress);
                    if (pr.customerVerify(c)) {
                        do {
                            System.out.print("0. Back:\n1. Create Order:\n2. Cancel Order:\nchoose: ");
                            cho = input.nextInt();
                            input.nextLine();
                            switch (cho) {
                                case 1: {
                                    int run = -1;
                                    int ch = -1;
                                    do {
                                        do {
                                            p.getStoredList();
                                            System.out.print("Choose product name to add in order: ");
                                            String id = input.nextLine();
                                            System.out.print("Input number of item you want: ");
                                            int q = input.nextInt();
                                            input.nextLine();
                                            if (id.toLowerCase().equals("shoe")) {
                                                s.setCustomerProduct(id, q);
                                                o.addItemInOrder(s);
                                            } else if (id.toLowerCase().equals("shirt")) {
                                                sh.setCustomerProduct(id, q);
                                                o.addItemInOrder(sh);
                                            } else if (id.toLowerCase().equals("pant")) {
                                                p.setCustomerProduct(id, q);
                                                o.addItemInOrder(p);
                                            } else {
                                                System.out.println("Please input the right product name...");
                                            }
                                            
                                            System.out.print("If you are done type '0' to finish else type any number to continue: ");
                                            run = input.nextInt();
                                            input.nextLine();                                           
                                            System.out.printf("1. %s:\n2. %s\n3. %s:\n", eco.getMethodName(), sta.getMethodName(), exp.getMethodName());
                                            System.out.print("Please Choose the Shipping Strategy: ");
                                            transport = input.nextInt();
                                            input.nextLine();
                                        } while (run != 0);
                                        do {
                                            System.out.print("1. List of Item you have bought.\n2. Confirm\n3. Back:\nchoose: ");
                                            ch = input.nextInt();
                                            input.nextLine();
                                            switch (ch) {
                                                case 1:
                                                    o.printBuyedList();
                                                    break;
                                                case 2:
                                                    System.out.print("Are you sure to confirm the order: ");
                                                    String con = input.nextLine();
                                                    if (con.toLowerCase().equals("yes")) {
                                                        if(transport == 1){
                                                            pr.Processing(o, eco);
                                                        }
                                                        else if(transport == 2){
                                                            pr.Processing(o, sta);
                                                        }
                                                        else if(transport == 3){
                                                            pr.Processing(o, exp);
                                                        }
                                                    }
                                                    break;
                                                case 3:
                                                    cho = 0;
                                                    ch = 0;
                                                    break;
                                            }
                                        } while (ch != 0);
                                    } while (cho != 0);
                                    break;
                                }
                                case 2:{
                                    o.getBuyedList().clear();
                                    if(o.getBuyedList().isEmpty()){
                                        System.out.println("Your Order have been deleted!");
                                    }
                                    break;
                                }
                            }
                        } while (cho != 0);
                    } else {
                        System.out.println("Your account have not been verify");
                    }
            }

        } while (choose != 0);
        input.close();
    }
}

