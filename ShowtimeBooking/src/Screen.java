import java.util.ArrayList;
import java.util.List;

public class Screen {
    private int screenNumber;
    private List<Seat> seats;

    public Screen(int screenNumber, int rows, int columns){
        this.screenNumber = screenNumber;
        this.seats = new ArrayList<>();
    }
    public int getScreenNumber() {
        return screenNumber;
    }
    public List<Seat> getSeats() {
        return seats;
    }
}
