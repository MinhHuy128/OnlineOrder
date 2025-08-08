package Payment_Method;

public class Tranfer_Pay implements Payment_Strategy{
    public int execute(){
        return 2;
    }
    public String getPName(){
        return "Tranfer";
    }
}
