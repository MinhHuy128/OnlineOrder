package Payment_Method;

public class Tranfer_Pay implements Payment_Strategy{
    private String bankNumber, bankName;
    public Tranfer_Pay(String bankNumber, String bankName){
        this.bankNumber = bankNumber;
        this.bankName = bankName;
    }
    public String getPName(){
        return "Tranfer";
    }
    public String getBankName(){
        return this.bankName;
    }
    public String getBankNumber(){
        return this.bankNumber;
    }
}
