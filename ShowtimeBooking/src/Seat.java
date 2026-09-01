public class Seat {
    private String seatNumber;
    private SeatType Type;

    public Seat(String seatNumber, SeatType type) {
        this.seatNumber = seatNumber;
        this.Type = type;
    }
    public String getSeatNumber() {
        return seatNumber;
    }
    public SeatType getType() {
        return Type;
    }
}
