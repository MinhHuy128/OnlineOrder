import java.util.Scanner;

import database.*;
import model.*;


public class Main {
    public static void main(String[] args) {
        int choose = -1;
        Scanner input = new Scanner(System.in);
        Customer c = new Customer();
        Items i = new Items();
        // do {
        //     //Nhân viên có thể thêm hàng hóa/giá cả/tồn kho vào danh sách hàng hóa đang bán 
        //     //Menu phía nhân viên
        //     System.out.print("1. Adding Items:\n2. Get List of Item\nchoose: ");
        //     choose = input.nextInt();
        //     input.nextLine();
        //     switch (choose) {
        //         case 1:
        //             System.out.print("Input Item Id: ");
        //             String id = input.nextLine();
        //             System.out.print("Input Item Name: ");
        //             String name = input.nextLine();
        //             System.out.print("Input Item price: ");
        //             double price = input.nextDouble();
        //             System.out.print("Input Number of Item in Stored: ");
        //             int q = input.nextInt();
        //             i.setItems(id, name, q, price);
        //             i.addItems();
        //             break;
        //         case 2:
        //             i.getStoredList();
        //             break;
        //         default:
        //             break;
        //     }
        // } while (choose != 0);
        do {
            //Menu phía khách hàng
            System.out.print("1. Sign In:\n2. Get List of Item\n3. Add Item in Order:\n4. Delete Account:\nchoose: ");
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
                    i.getStoredList();
                    break;
                case 4:
                    System.out.print("Input Email of Account you want to delete: ");
                    String e = input.nextLine();
                    c.remCustomer(e);
                    break;
                default:
                    break;
            }
        } while (choose != 0);
        // order.process();
        // System.out.println("Status: " + order.getStatusName());
        
        // order.ship();
        // System.out.println("Status: " + order.getStatusName());
        
        // order.deliver();
        // System.out.println("Status : " + order.getStatusName());
        
        // order.cancel();
        
        // Order cancelledOrder = new Order("5");
        // cancelledOrder.cancel();
        // System.out.println("status: " + cancelledOrder.getStatusName());
    
    }
  }
