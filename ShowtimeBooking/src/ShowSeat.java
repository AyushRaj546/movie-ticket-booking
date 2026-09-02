public class ShowSeat {
    private Seat seat;
    private ShowSeatStatus status;

    public ShowSeat(Seat seat)
    {
        this.seat = seat;
        this.status = ShowSeatStatus.Available;
    }
    public Seat getSeat() {
        return seat;
    }
    public ShowSeatStatus getStatus() {
        return status;
    }
    public boolean isAvailable() {
        return status == ShowSeatStatus.Available;
    }
    public void book(){
        if (!isAvailable())
        {
            throw new IllegalStateException("Seat" + seat.getSeatNumber() + "is already booked");
        }
        status = ShowSeatStatus.Booked;
    }
    public void cancel(){
        status = ShowSeatStatus.Available;
    }
}
