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
    public void book(){}
    public void cancel(){}
}
