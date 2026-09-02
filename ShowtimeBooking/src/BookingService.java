import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private PriceCalculator priceCalculator;
    private TicketPrinter ticketPrinter;

    public BookingService(PriceCalculator priceCalculator, TicketPrinter ticketPrinter) {
        this.priceCalculator = new PriceCalculator();
        this.ticketPrinter = new TicketPrinter();
    }

    public Booking bookTickets(Show show , Customer customer , List<String>seatNumbers,Payment payment)
    {
        //validate the seat
        List<ShowSeat> selectedShowSeats = new ArrayList<>();
        for (String seatNumber : seatNumbers) {
            ShowSeat showSeat = show.findShowSeat(seatNumber);
            if(showSeat == null)
            {
                System.out.println("Invalid seat number: " + seatNumber);
                return null;
            }
            if(!showSeat.isAvailable())
            {
                System.out.println("Seat " + seatNumber + " is already booked. Booking rejected.");
                return null;
            }
            selectedShowSeats.add(showSeat);
        }

        //calculate total unsing calculator
        List<Seat> seats = new ArrayList<>();
        for (ShowSeat showSeat : selectedShowSeats) {
            seats.add(showSeat.getSeat());
        }
        double total = priceCalculator.calculateTotal(seats);
        System.out.println("Total amount: Rs." + total);

        Booking booking = new Booking(customer,show,selectedShowSeats,total);

        //Process payment
        boolean paid = payment.pay(total);
        if(!paid)
        {
            booking.fail();
            System.out.println("Payment failed. Booking rejected.");
            return booking;
        }
        // confirm booking
        for (ShowSeat showSeat : selectedShowSeats) {
            showSeat.book();
        }
        booking.confirm();
        ticketPrinter.printTicket(booking);
        return booking;
    }
    public void cancelBooking(Booking booking)
    {
        if(booking.getStatus() != BookingStatus.Confirmed)
        {
            System.out.println("Booking is not confirmed. Cancellation rejected.");
            return;
        }
        for (ShowSeat showSeat : booking.getShowSeats())
        {
            showSeat.cancel();
        }
        booking.cancel();
        System.out.println("Booking " + booking.getBookingID() + " cancelled successfully.");
    }
}
