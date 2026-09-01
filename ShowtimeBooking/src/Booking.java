import java.util.List;

public class Booking {
    private static int nextID = 1001;
    private String bookingID;
    private Customer customer;
    private Show show;
    private List<ShowSeat> showSeats;
    private double totalAmount;
    private BookingStatus status;

    public Booking(Customer customer, Show show, List<ShowSeat> showSeats ,double totalAmount) {
        this.bookingID = "BK"+nextID++;
        this.customer = customer;
        this.show = show;
        this.showSeats = showSeats;
        this.totalAmount = totalAmount;
        this.status = BookingStatus.Pending;
    }
    public String getBookingID() {
        return bookingID;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Show getShow() {
        return show;
    }
    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public BookingStatus getStatus() {
        return status;
    }
    public void cancel(){
        this.status = BookingStatus.Cancelled;
    }
    public void confirm(){
        this.status = BookingStatus.Confirmed;
    }
    public void fail(){
        this.status = BookingStatus.Failed;
    }
}
