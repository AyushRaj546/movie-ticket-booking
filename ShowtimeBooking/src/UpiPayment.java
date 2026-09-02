public class UpiPayment extends Payment{
    @Override
    public boolean pay(double amount) {
        System.out.println("[UPI] Paid Rs. " + amount + " successfully");
        return true;
    }
}

