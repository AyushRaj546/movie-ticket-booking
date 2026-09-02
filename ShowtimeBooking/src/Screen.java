import java.util.ArrayList;
import java.util.List;

public class Screen {
    private int screenNumber;
    private List<Seat> seats;


    public Screen(int screenNumber, int rows, int columns){
        this.screenNumber = screenNumber;
        this.seats = new ArrayList<>();
        for(int row = 1; row <= rows; row++)
        {
            char rowLetter = (char) ('A' +  row);
            for (int col = 1; col <= columns; col++)
            {
                String seatNum = "" + rowLetter +col;
                SeatType type;
                if(row<2)
                {
                    type = SeatType.Silver;
                } else if (row<4) {
                    type = SeatType.Gold;
                }
                else {
                    type = SeatType.Platinum;
                }
                seats.add(new Seat(seatNum, type));
            }
        }
    }
    public int getScreenNumber() {
        return screenNumber;
    }
    public List<Seat> getSeats() {
        return seats;
    }
}
