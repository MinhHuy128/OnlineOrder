package Payment_Method;

public class Directly_Pay implements Payment_Strategy{
    public String getBankNumber(){
        return null;
    }
    public String getBankName(){
        return null;
    }
    public String getPName(){
        return "Directly";
    }
}