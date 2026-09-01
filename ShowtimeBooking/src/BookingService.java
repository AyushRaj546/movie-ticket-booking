import java.util.List;

public class BookingService {
    private PriceCalculator priceCalculator;
    private TicketPrinter ticketPrinter;

    public BookingService() {
        this.priceCalculator = new PriceCalculator();
        this.ticketPrinter = new TicketPrinter();
    }

    public Booking bookTickets(Show show , Customer customer , List<String>seatNumbers,Payment payment)
    {
        return null;
    }
    public void cancelBooking(Booking booking)
    {

    }
}
