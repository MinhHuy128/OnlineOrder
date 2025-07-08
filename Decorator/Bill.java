package Decorator;

interface Bill {
    public double export();
}

class ExportBill implements Bill  {
    public double export() {
        return 1000000;
    }
}