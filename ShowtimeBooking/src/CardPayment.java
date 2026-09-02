public class CardPayment extends Payment{
    @Override
    public boolean pay(double amount) {
        System.out.println("[Card] Paid Rs. " + amount + " successfully");
        return true;
    }
}

