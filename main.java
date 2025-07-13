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
        Scanner input = new Scanner(System.in);
        Customer c = new Customer();
        Product p = new Pants();
        Product s = new Shoe();
        Product sh = new Shirt();
        Order o = new Order();
        p.setProduct("pant", "red", 100000, 200, 100, "L", 1000);

        s.setProduct("shoe", "red", 100000, 200, 100, "L", 1000);

        sh.setProduct("shirt", "red", 100000, 200, 100, "L", 1000);
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
            System.out.print("1. Sign In:\n2. Create Order:\n3. Delete Account:\nchoose: ");
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
                    c.addCustomer();
                    break;
                case 2:
                    int ch = -1;
                    int run = -1;
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
                            System.out.print("If you are done type '0' to finish else type any key to continue: ");
                            run = input.nextInt();
                            input.nextLine();
                        } while (run != 0);
                        System.out.println("1. List of Item you have bought.\n2. Confirm\n3. Back:\nchoose: ");
                        ch = input.nextInt();
                        input.nextLine();
                        switch (ch) {
                            case 1:
                                o.printBuyedList();
                                break;
                            default:
                                break;
                        }
                    } while (ch != 0);
                    break;
                case 3:
                    System.out.print("Input Email of Account you want to delete: ");
                    String e = input.nextLine();
                    c.remCustomer(e);
                    break;
                default:
                    break;
            }
        } while (choose != 0);
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

    }
}
