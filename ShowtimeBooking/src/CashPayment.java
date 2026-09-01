public class CashPayment extends Payment{
    @Override
    public boolean pay(double amount) {
        return false;
    }
}
