package model;

public class Customer {
    private String cusId;
    private String cusName;
    private String cusPhone;
    private String password;
    private String cusEmail;
    private String cusAdress;

    public Customer(String cusId, String cusName, String cusEmail, String cusPhone, String password, String cusAdress){
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusEmail = cusEmail;
        this.cusAdress = cusAdress;
        this.cusPhone = cusPhone;
        this.password = password;
    }

    public String getName(){
        return this.cusName;
    }
    public String getEmail(){
        return this.cusEmail;
    }
    public String getPhone(){
        return this.cusPhone;
    }
    public String getAdress(){
        return this.cusAdress;
    }
    public String getPassword(){
        return this.password;
    }
    public String getCusId(){
        return this.cusId;
    }
}
