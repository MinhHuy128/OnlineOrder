import java.util.Scanner;
import model.*;
import Order.Order;
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
                        int choo = -1;
                        do {
                            System.out.print("1. Create Order:\nchoose: ");
                            choo = input.nextInt();
                            input.nextLine();
                            switch (choo) {
                                case 1: {
                                    int ch = -1;
                                    int run = -1;
                                    int chs = -1;
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
                                            chs = input.nextInt();
                                            input.nextLine();
                                            switch (chs) {
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
                                                    run = -1;
                                                    ch = -1;
                                                    break;
                                            }
                                        } while (chs != 0);
                                    } while (ch != 0);
                                    break;
                                }
                            }
                        } while (choo != 0);
                    } else {
                        System.out.println("Your account have not been verify");
                    }
            }
        } while (choose != 0);
    }
}
/*
 * // Order o = new Order("1");
 * ProcessingOrder p = new OnlineProcessingOrder();
 * Order order = new Order("13");
 * 
 * // order.ship();
 * // System.out.println("Status: " + order.getStatusName());
 * 
 * // order.deliver();
 * // System.out.println("Status : " + order.getStatusName());
 * 
 * // order.cancel();
 * 
 * // Order cancelledOrder = new Order("5");
 * // cancelledOrder.cancel();
 * // System.out.println("status: " + cancelledOrder.getStatusName());
 * 
 * }
 */
