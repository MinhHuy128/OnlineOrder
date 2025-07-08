package Decorator;

abstract class Wrapper implements Bill {
    protected Bill bill;
    public Wrapper(Bill bill) {
        this.bill = bill;
    }
}

class GiftWrapper extends Wrapper {
    public GiftWrapper(Bill bill) {
        super(bill);
    }

    @Override
    public double export() {
        System.out.println("Searching for gifts");
        double total = this.bill.export();
        System.out.println("Total bill: $" + total);
        if (total >= 500000) {
            System.out.println("Found 50 Gifts");
        }
        else {
            System.out.println("No Gift");
        }
        return total;
    }
}

class InsuranceWrapper extends Wrapper {
    public InsuranceWrapper(Bill bill) {
        super(bill);
    }

    @Override
    public double export() {
        double total = this.bill.export();
        double insuranceAmount = total * 0.02;
        double finalTotal = total + insuranceAmount;

        System.out.println(
            String.format("Applying insurance of: $%.2f\n", insuranceAmount) +
            String.format("Bill total after insurance: $%.2f", finalTotal)
        );
        return finalTotal;
    }
}

class DiscountWrapper extends Wrapper {
    final double percent;
    
    public DiscountWrapper(Bill bill, double percent) {
        super(bill);
        this.percent = percent / 100;
    }

    @Override
    public double export() {
        double total = this.bill.export();
        if (total < 800000) {
            System.out.println("Counn't find any discount for this bill");
            return total;
        }
        else {
            double discountAmount = this.percent * total;
            double finalTotal = total - discountAmount; 
            System.out.println(
                String.format("Applying discount of: $%.2f\n", discountAmount) +
                String.format("Bill after discount: $%.2f", finalTotal)
            );
            return finalTotal;
        }
    }
}