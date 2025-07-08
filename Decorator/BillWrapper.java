package Decorator;

class BillWrapper {
    public static void export(Bill bill, double discountPercent) {
        bill = new GiftWrapper(bill);
        bill = new DiscountWrapper(bill, discountPercent);
        bill = new InsuranceWrapper(bill);
        double finalTotal = bill.export();
        System.out.println(
            String.format("Final Total is: $%.2f", finalTotal)
        );
    }
}