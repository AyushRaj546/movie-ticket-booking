import java.util.List;

public class TicketPrinter {
    public void printTicket(Booking booking)
    {
        System.out.println("============== TICKET ===============");
        System.out.println("Booking ID   : " + booking.getBookingID());
        System.out.println("Movie        : " + booking.getShow().getMovie().getTitle());
        System.out.println("Screen       : " + booking.getShow().getScreen().getScreenNumber());
        System.out.println("Time         : " + booking.getShow().getStartTime());
        System.out.print("Seats        : ");
        List<ShowSeat> ssList = booking.getShowSeats();
        for (int i = 0; i < ssList.size(); i++) {
            System.out.print(ssList.get(i).getSeat().getSeatNumber());
            if (i < ssList.size() - 1) System.out.print(", ");
        }
        System.out.println();
        System.out.println("Amount       : Rs." + booking.getTotalAmount());
        System.out.println("Status       : " + booking.getStatus());
        System.out.println("======================================");
    }
}
