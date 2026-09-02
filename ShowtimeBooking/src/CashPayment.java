public class CashPayment extends Payment{
    @Override
    public boolean pay(double amount) {
        System.out.println("[Cash] Paid Rs. " + amount + " successfully");
        return true;
    }
}
