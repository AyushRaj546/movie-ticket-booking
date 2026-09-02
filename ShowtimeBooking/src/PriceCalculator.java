import java.util.List;

public class    PriceCalculator {
    public static final int SILVER_PRICE = 150;
    public static final int GOLD_PRICE = 250;
    public static final int PLATINUM_PRICE = 400;
    public  double calculateTotal(List<Seat>seats)
    {
        int totalPrice = 0;
        for(Seat seat : seats)
        {
            switch (seat.getType())
            {
                case Silver:
                    totalPrice += SILVER_PRICE;
                    break;
                case Gold:
                    totalPrice += GOLD_PRICE;
                    break;
                case Platinum:
                    totalPrice += PLATINUM_PRICE;
                    break;
            }
        }
        return totalPrice;
    }
}
