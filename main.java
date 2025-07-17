import java.util.Scanner;
import model.*;
import Order.Order;
import Processing.OnlineProcessingOrder;
import Processing.ProcessingOrder;
import Product.Pants;
import Product.Product;
import Product.Shirt;
import Product.Shoe;

public class Main {
    public static void main(String[] args) {
        int choose = -1;
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
            System.out.print("1. Sign In:\n2. Create Order:\n2. Delete Account:\nchoose: ");
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
                            System.out.print("1. Create Order:\n2. Cancel Order:\nchoose: ");
                            choose = input.nextInt();
                            input.nextLine();
                            switch (choose) {
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
                                            System.out.print(
                                                    "If you are done type '0' to finish else type any key to continue: ");
                                            run = input.nextInt();
                                            input.nextLine();
                                        } while (run != 0);
                                        do {
                                            System.out.println(
                                                    "1. List of Item you have bought.\n2. Confirm\n3. Back:\nchoose: ");
                                            ch = input.nextInt();
                                            input.nextLine();
                                            switch (ch) {
                                                case 1:
                                                    o.printBuyedList();
                                                    break;
                                                case 2:
                                                    System.out.println("Are you sure to confirm the order: ");
                                                    String con = input.nextLine();
                                                    if (con.toLowerCase().equals("yes")) {
                                                        if (pr.Processing(o)) {
                                                            System.out.println("Your Order have been verify");
                                                        }
                                                    }
                                                    break;
                                                case 3:
                                                    choose = 0;
                                                    ch = 0;
                                                    break;
                                            }
                                        } while (ch != 0);
                                    } while (choose != 0);
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
                        } while (choose != 0);
                    } else {
                        System.out.println("Your account have not been verify");
                    }
            }
        } while (choose != 0);
        input.close();
    }
}

