package Payment_Method;

public class Directly_Pay implements Payment_Strategy{
    public int execute(){
        return 1;
    }
    public String getPName(){
        return "Directly";
    }
}