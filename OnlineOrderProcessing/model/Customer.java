package model;

import database.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static int count = 0;
    private String cusId;
    private String firstName;
    private String lastName;
    private String cusPhone;
    private String cusEmail;
    private String cusAdress;
    private String file = "csvfile/customer.csv";

    public void addCustomer() {
        File_Writer fw = new File_Writer();
        BufferedWriter writer = fw.Writer(this.file);
        try {
            writer.write(cusId + ", ");
            writer.write(lastName + ", ");
            writer.write(firstName + ", ");
            writer.write(cusEmail + ", ");
            writer.write(cusPhone + ", ");
            writer.write(cusAdress + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("Customer Insert Error!");
            e.printStackTrace();
        }
    }

    public void remCustomer(String target) {
        String line;
        List<String[]> lines = new ArrayList<>();
        try {
            BufferedReader b = new BufferedReader(new FileReader(this.file));
            String r = "";
            while ((line = b.readLine()) != null) {
                String[] data = line.split(",");
                lines.add(data);
            }
            b.close();
            lines.removeIf(l -> l[0].equals(target));
            BufferedWriter w = new BufferedWriter(new FileWriter(this.file));
            for (String[] i : lines) {
                r = String.join(",", i);
                w.write(r + "\n");
            }
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCustomer(String firstName, String lastName, String cusEmail, String cusPhone, String cusAdress) {
        this.cusId = "C" + count++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cusEmail = cusEmail;
        this.cusPhone = cusPhone;
        this.cusAdress = cusAdress;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.cusEmail;
    }

    public String getPhone() {
        return this.cusPhone;
    }

    public String getAdress() {
        return this.cusAdress;
    }

    public String getCusId() {
        return this.cusId;
    }
}
